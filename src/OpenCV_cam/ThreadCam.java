package OpenCV_cam;

import OpenCV_cam.Panels.StyleManager;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;

public class ThreadCam extends Thread{
    private static ExecutorService service = Executors.newCachedThreadPool();
    //************************************************//
    //************* Отбражение ***********************//
    //************************************************//
   
    private volatile JLabel lbl;
    
    volatile Dimension size;
    public JLabel getLable(){
        return lbl;
    }
    
    public void setLable(JLabel lable){
        this.lbl = lable;
        //size = lable.getSize();
    }
      
    private void setEmptyIcon(){
       
        try{
            if(lbl != null){
                lbl.setIcon(StyleManager.getEmptyPicture(this));
                lbl.revalidate();
            }
        }catch(Exception e){
            //logger.warn("Иконка пустоты не обнарущенна");
        }
    }

  
    private BufferedImage getBufImg(Mat mat){
        if(mat == null) return null;
        int cols = mat.cols(), rows = mat.rows();
        int bufferSize = mat.channels() * cols * rows;
        if(bufferSize == 0) return null;
        byte[] b = new byte[bufferSize];
        mat.get(0, 0, b);
        BufferedImage buffImg = new BufferedImage(cols, rows, BufferedImage.TYPE_3BYTE_BGR);
        final byte[] targetPixels = ((DataBufferByte) buffImg.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return buffImg; 
    }
    
    private void show(){
        if( lbl != null)
        {
           try{
                size = lbl.getSize();
                Image img = getBufImg(curFrame);
                img = img.getScaledInstance(size.width, size.height, Image.SCALE_FAST);
                lbl.setIcon(new ImageIcon(img));
           }catch(Exception e){
                //setEmptyIcon();
            }
        }
        else
        {
            setEmptyIcon();
        }
    }
    
    //************************************************//
    
    //************************************************//
    //************* Чтение ***************************//
    //************************************************//
    
    private static final int FRAME_SLEEP_INTERVAL_MILISECOND = 20;
    private static final int LIFE_TIME_MILISECOND = 15000;
    private static final int BREAK_TIME_MILISECOND = 2000;
   
    //('X','V','I','D') - 42% CPU
    //('A', 'V', 'C', '1') - 85% CPU - PERFECT
    
    
    private final int codec = VideoWriter.fourcc('A', 'V', 'C', '1');
    private static final int TIME_RECORD_SECOND = 60; 
    private static final int TIME_RECORD_MILISECOND = TIME_RECORD_SECOND * 1000; 
    
    private final FileName file;
    private volatile long timestamp;
    private volatile Date deadTimeStamp = new Date();
    private volatile boolean isWork = false;
    public VideoCapture cap;
    public double fps;
    
    public final SqlLite.CameraSettings settings;
    private Mat curFrame;
    
    int framesCount = 0;
    int showCounter = 0;
    static final int SHOW_EVERY_FRAMES = 3;
    String fileName;
    
    //Logger logger = LogManager.getLogger(getClass().getName());
    
    public ThreadCam(SqlLite.CameraSettings sqlCamera){
       super("ThreadCam " + sqlCamera.toString());
       this.setPriority(MAX_PRIORITY);
       this.file = new FileName(Program.VIDEO_PATH, sqlCamera.name, FileName.EXT_MP4);
       
       this.settings = sqlCamera;
    }
    
    public boolean isCameraAlive(){
        long def = new Date().getTime() - deadTimeStamp.getTime();  
        return def <= LIFE_TIME_MILISECOND;
    }
    
    public boolean isWork(){
        return isWork;
    }
    
    private static final int VIDEO_PROP_FPS = 5;

    private void getFrame()
    { 
       try{
            if(cap.isOpened()){
                if(cap.grab()){
                    cap.retrieve(curFrame);
                    isWork = true;
                    deadTimeStamp = new Date();
                    //cap.grab();
                    //sleep(20);
                }
            }else{
                
            }
            //try{ this.sleep(FRAME_SLEEP_INTERVAL_MILISECOND); } catch(Exception e){};
        }catch(Exception e){
           //logger.error("камера " + getFullName() + " отвалилась.");
            e.printStackTrace();
           //this.interrupt();
        }
    }
    
    public String getFullName(){
        return settings.name; //"cam" + settings.name;
    }
    
    public void reliaseVideoWriter(){
        try{
            if(videoWriter != null){
                videoWriter.release();
            }
        }catch(Throwable e){
            //logger.error("Ошибка при закрытии камеры " + getFullName() + "("+ e.getMessage() +")");
        }
    }
    
    public SqlLite.CameraSettings getCameraSettings(){
        return settings;
    }
    
    public Mat getCurrentFrame(){
        return curFrame;
    }
    
    private VideoWriter videoWriter;
    
    private void createVideoWriter(){
        fileName = file.getFileName();
        fps = cap.get(VIDEO_PROP_FPS);
        videoWriter = new VideoWriter(fileName, codec, fps, curFrame.size(), true);
        //logger.info(getFullName() + " запись файла началась " + fileName);
    }
    
    public void setStartSettings(){
        try{
            String rule = settings.get_con_str_rule();   
            this.cap = new VideoCapture(rule);
            fps = cap.get(VIDEO_PROP_FPS);
            curFrame = new Mat();
        }catch(Exception e){
            //logger.error("Поток " + getName() + " не смог установить настройки.(" + e.getMessage()+")");
            this.interrupt();
        }
    }
    
   
    
    private void writeVideoWriter(){
        try {
            videoWriter.write(curFrame);  
            framesCount++;
        }catch(Throwable e){
           //logger.error(file.getFileName() + "Ошибка во время записи (" + e.getMessage() + ")");
       }
    }
    
    private void resetVideoWriter(){
       try {       
           videoWriter.release();
           long time = Math.round(framesCount / fps);
           File oldfile = new File(fileName);
           File newfile = new File(fileName.replaceAll(FileName.EXT_MP4, "_" + time + FileName.EXT_MP4));
           oldfile.renameTo(newfile);
           framesCount = 0;
           //logger.info(settings.toString() + " запись файла завершилась (FRAMES=" + framesCount + "," + ",FPS="+ fps + ", OTKLONENIE_FRAMES=" + (fps * TIME_RECORD_SECOND - framesCount)  + ") " + file.getFileName());
         //  framesCount = 0;
        }catch(Throwable e){
           //logger.error(file.getFileName() + "Ошибка во время окончания записи (" + e.getMessage() + ")");
        }
        videoWriter = null;
    }

 
    @Override
    public void run()
    {   
        timestamp = new Date().getTime();
        setStartSettings();
        
        while(!this.isInterrupted()) 
        {    
            try{
                getFrame();
                
                long milisecondFromPrevWrite = new Date().getTime() - timestamp;
                if(milisecondFromPrevWrite >= TIME_RECORD_MILISECOND){
                    timestamp = new Date().getTime();
                    resetVideoWriter();
                }
                
                if(videoWriter == null){
                    createVideoWriter();
                }
                writeVideoWriter();
                
                
                if(showCounter++ > SHOW_EVERY_FRAMES){
                    service.submit(new Runnable() {
                        public void run() {
                            show();
                        }
                    });
                    showCounter = 0;
                }
                
                
            }catch(Exception e){
                 e.printStackTrace();
                //logger.error("Поток " + getName() + " ошибка в цикле.(" + e.getMessage()+")");
            }
        }
        
        resetVideoWriter();
        //logger.info("камера " + getFullName() + " завершила работу");
    } 
}
