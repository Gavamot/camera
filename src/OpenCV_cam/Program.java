package OpenCV_cam;

import OpenCV_cam.Panels.ViewPanel;
import SqlLite.CameraSettings;
import SqlLite.Content;
import SqlLite.Config;
import SqlLite.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.Thread.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.UIManager;
import static OpenCV_cam.Manager.*;
import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.opencv.core.Core;

public class Program {
    
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    
    public static void main(String[] args) throws Exception
    {    
        if(args.length > 0){
            screen = ScreenMode.getFromString(args[0]);
        }
        
      //  logger.info("Запуск программы в режиме " + screen.name());
        
        String style = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
        try {
            UIManager.setLookAndFeel(style);
       
        } catch (Exception e) {
         //   logger.info("Запуск программы ошибка при устанвке стиля " + style + "(" + e.getMessage() +")");
        } 
        
        Program prog = new Program();
        OpenCV_cam.Manager.Program = prog;
        
        try {
            prog.startKeybord();
        } catch (Exception e) {
           // logger.info("Запуск программы ошибка при запуске клавиатуры " + "(" + e.getMessage() +")");
        } 
        
        try {
           prog.startProgramm();
        } catch (Exception e) {
          //  logger.info("Запуск при запуске программы " + "(" + e.getMessage() +")");
            throw e;
        } 
        
        
    }
    
    
    private enum ScreenMode{
        full,
        window;
        public static ScreenMode getFromString(String str){
            switch(str){
                case "full": return full;
                case "window": return window;
                default: return full;
            }
        }
    }
    
    private static ScreenMode screen = ScreenMode.full;
    
    //static final Logger logger = LogManager.getLogger(Program.class.getName());
    
    private Thread updateFrame;
    private Thread checkCam;
    public ChannelWriter channelWriter;
    
    public Keybord keybord;
    public final ArrayList<ThreadCam> camersPool = new ArrayList<>();
    public final ArrayList<ThreadChannel> channelsPool = new ArrayList<>();
    
    public static final String VIDEO_PATH = "./video";
    public static final String CHANNEL_PATH = "./channels";
    public static final String MEDIA_PATH = new File("/media").listFiles()[0].getAbsolutePath();
    
    private void initThreads(Panel pan) throws Exception{
        
        ViewPanel p = pan.getPanelObj();
        viewsPanels.add(p);
        
        for(int i = 0; i < p.content.size(); i++) {
           Content ctx = p.content.get(i);
           
           if(ctx.channel == null){
               if(ctx.camera != null)
               {
                    ThreadCam thcam = getCameraThread(ctx.camera.id);
                    if(thcam != null)
                    {
                        p.threadCams.add(thcam);
                    }
                    else
                    {
                     //   logger.error("камера " + ctx.camera.toString() + " c ID=" + ctx.camera.id + " не найдена в пуле потоков камер");
                    }
               }
           }else{
               if(ctx.channel != null){
                    ThreadChannel tc = new ThreadChannel(ctx.channel);
                    if(tc != null)
                    {
                        channelsPool.add(tc);   
                        p.threadChannels.add(tc);
                    }
                    else{
                      //  logger.error("канал " + ctx.channel.toString() + " не найдена в пуле потоков каналов");
                    }
                   // tc.start();
               }
           }
        }
    }
    
    private void timeOut(int milisecond){
    //    try{
     //       Thread.sleep(milisecond);
      //  } catch (InterruptedException ex) {
            //logger.error("Ошибка во время сна. (" + ex.getMessage() + ")");
      //  }
    }
    
    public void exit(){
        stopProgram();
        try {
            Thread.sleep(3000);
            //Process p = Runtime.getRuntime().exec("sudo shutdown now");
            //BufferedReader stdInput = new BufferedReader(new 
            //InputStreamReader(p.getInputStream()));
            //BufferedReader stdError = new BufferedReader(new 
            //InputStreamReader(p.getErrorStream()));
            
        } catch (Exception ex) {
            
        }
        System.exit(0);
    }
    
    private void stopProgram(){
        

        keybord.interrupt();
        
        checkCam.interrupt();
        channelWriter.interrupt();
        updateFrame.interrupt();
        
        for(ThreadCam cam : camersPool)
            cam.interrupt();
        for(ThreadChannel ch : channelsPool)
            ch.interrupt();
        
        timeOut(1500);
        
        if(subFrame != null)
            subFrame.dispose();
        if(frame != null)
            frame.dispose();
        
        viewsPanels.clear();
        camersPool.clear();
        channelsPool.clear();
        
    }
    
    private void settingMainFrame(){
        frame = new JFrame();
        
        if(screen == ScreenMode.full){
            frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        }else{         
            frame.setSize(1024, 768);
        }
        
        frame.addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent we)
            {
                stopProgram();
                if(keybord != null) keybord.init();
               // logger.info("Программа закрыта");
                System.exit(0);
            }
        });
    }
    
    private void startProgramm() throws Exception {
        
        settingMainFrame();

        channelWriter = new ChannelWriter();
        channelWriter.getChannels();
        channelWriter.start();
        
        //Создаем камеры 
        for(CameraSettings settings : CameraSettings.getAll())
        {
            if(settings.id == 1) continue; // Пустая камера
            ThreadCam cam = new ThreadCam(settings);
            camersPool.add(cam);
            cam.start();
        }
        
        //Создаем панели
        for(Panel pan : Panel.getAll())
            initThreads(pan);
        
        Config dev = Config.get();
        final int update_time =  dev.redraw_timer; 

        updateFrame = new Thread(()-> {
            while(true)
            {
                try {
                    Manager.redraw();
                } catch (Throwable ex) {
                   // logger.error("Ошибка перерисовки (" + ex.getMessage() + ")");
                }
                timeOut(update_time);
            }
        }, "updateFrame");
        updateFrame.start();
        
        checkCam = new Thread(()-> {
            try{ Thread.sleep(30000); } catch(Exception e){   }
            while(true)
            {
                for(int i = 0; i < camersPool.size(); i++){
                    try{
                        ThreadCam cam = camersPool.get(i);
                       
                        
                        if(!cam.isCameraAlive())
                        {
                           cam.interrupt();
                           cam.stop();
                          
                           ThreadCam newCam = new ThreadCam(cam.settings);
                           newCam.setLable(cam.getLable());
                           camersPool.set(i, newCam);
                           newCam.start();
                        }
                    }catch(Exception e){ e.printStackTrace(); }
                }
                try{ Thread.sleep(20000); } catch(Exception e){ }
            }
        }, "checkCam");

        Manager.setViewPanel(0); //Стартовая панель
        frame.show();

       checkCam.start();
    }
    
    private void startKeybord() throws Exception{
        keybord = new Keybord();
        keybord.start();
    }
    
    public boolean restartProgramm(){
        //logger.info("Перезагрузка программы.");
        try
        {
            stopProgram();
            Thread.sleep(3000);
            Process p = Runtime.getRuntime().exec("sudo ./restart.sh");
            System.exit(0);
        }
        catch(Exception e){
            //logger.error("Ошибка во время перезапуска программы");
            e.printStackTrace();
            System.exit(0);
            return false;
        }
        
        return true;
    }
    
}
