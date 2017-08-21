package OpenCV_cam.Panels.Settings;

import OpenCV_cam.Panels.Components.MyTextF;
import OpenCV_cam.FileName;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import OpenCV_cam.CmdCode;
import OpenCV_cam.Manager;
import OpenCV_cam.Panels.Components.MyCheckBox;
import OpenCV_cam.Panels.MyUIComponent;
import OpenCV_cam.Panels.SettingsPanel;
import OpenCV_cam.Program;
import OpenCV_cam.RedisHealper;
import SqlLite.Config;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import org.apache.logging.log4j.LogManager;

public class Panel_Arch extends SettingsPanel {

    @Override
    public MyUIComponent[] GetDataContainers() {
        return new MyUIComponent[] {
            cmb_device,
            cmb_camera,
            input_start_data,
            input_start_time,
            input_end_data,
            input_end_time,
            checkBoxVideo,
            checkBoxData,
            btn_write
        };
    }

    class BrigadeInfo{
        public String Cluster;
        public String Field;
        public String Rig;
        public String Well;
        public String Work;
    }
    
    private void updateCmbMedia(){
         // Формируем список флешек
        for (File f : new File(Program.MEDIA_PATH).listFiles()) 
        {
            if(f.canWrite() && !f.isFile())
                cmb_device.addItem(f.getName());
        }  
    }
    
    
   protected org.apache.logging.log4j.Logger logger;
    
   public Panel_Arch() {
       
        logger = LogManager.getLogger(getClass().getName());
        initComponents(); 
        updateCmbMedia();
        
        // Формируем список камер
        cmb_camera.addItem("Все камеры");
        File path = new File(Program.VIDEO_PATH);
        if(path.exists()){
            for (File f : path.listFiles()) {
                cmb_camera.addItem(f.getName());
            }
        }
        // Задаем даты по умолчанию
        input_start_data.setValue("01/01/2016");
        input_start_time.setValue("00:00");
        ZonedDateTime now = ZonedDateTime.now();

        String date = String.format("%02d/%02d/%04d", now.getDayOfMonth(), now.getMonthValue(), now.getYear());
        input_end_data.setValue(date);  
        String time = String.format("%02d:%02d", now.getHour(), now.getMinute());
        input_end_time.setValue(time);
     
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        myJLable1 = new OpenCV_cam.Panels.Components.MyJLable();
        cmb_device = new OpenCV_cam.Panels.Components.MyCmb();
        myJLable5 = new OpenCV_cam.Panels.Components.MyJLable();
        cmb_camera = new OpenCV_cam.Panels.Components.MyCmb();
        myJLable2 = new OpenCV_cam.Panels.Components.MyJLable();
        input_start_data = MyTextF.getFild(MyTextF.FORMAT_DATE, this, 1);
        myJLable3 = new OpenCV_cam.Panels.Components.MyJLable();
        input_start_time = MyTextF.getFild(MyTextF.FORMAT_TIME, this, 1);
        myJLable6 = new OpenCV_cam.Panels.Components.MyJLable();
        input_end_data = MyTextF.getFild(MyTextF.FORMAT_DATE, this, 1);
        myJLable7 = new OpenCV_cam.Panels.Components.MyJLable();
        input_end_time = MyTextF.getFild(MyTextF.FORMAT_TIME, this, 1);
        checkBoxVideo = new OpenCV_cam.Panels.Components.MyCheckBox();
        checkBoxData = new OpenCV_cam.Panels.Components.MyCheckBox();
        btn_write = new OpenCV_cam.Panels.Components.MyButton();
        jPanel1 = new javax.swing.JPanel();
        myJLableBig1 = new OpenCV_cam.Panels.Components.MyJLableBig();
        jPanel3 = new javax.swing.JPanel();
        myLableSmall1 = new OpenCV_cam.Panels.Components.MyLableSmall();
        lblStatus = new OpenCV_cam.Panels.Components.MyJLable();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel2.setLayout(new java.awt.GridLayout(8, 2, 10, 20));

        myJLable1.setText(" 1. Выбор носителя");
        myJLable1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPanel2.add(myJLable1);

        cmb_device.setToolTipText("");
        cmb_device.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPanel2.add(cmb_device);

        myJLable5.setText(" 2. Выбор камеры");
        myJLable5.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPanel2.add(myJLable5);

        cmb_camera.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPanel2.add(cmb_camera);

        myJLable2.setText(" 3. Дата начала записи");
        myJLable2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPanel2.add(myJLable2);

        input_start_data.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPanel2.add(input_start_data);

        myJLable3.setText(" 4. Время начала записи");
        myJLable3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPanel2.add(myJLable3);

        input_start_time.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPanel2.add(input_start_time);

        myJLable6.setText(" 5. Дата окончания записи");
        myJLable6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPanel2.add(myJLable6);

        input_end_data.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPanel2.add(input_end_data);

        myJLable7.setText(" 6. Время окончания записи");
        myJLable7.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPanel2.add(myJLable7);

        input_end_time.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPanel2.add(input_end_time);

        checkBoxVideo.setSelected(true);
        checkBoxVideo.setText("7. Запись видео");
        checkBoxVideo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPanel2.add(checkBoxVideo);

        checkBoxData.setSelected(true);
        checkBoxData.setText("8. Запись данных");
        checkBoxData.setToolTipText("");
        checkBoxData.setAlignmentX(1.0F);
        checkBoxData.setAlignmentY(1.0F);
        checkBoxData.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        checkBoxData.setMaximumSize(new java.awt.Dimension(194, 50));
        jPanel2.add(checkBoxData);

        btn_write.setText("9. Записать");
        btn_write.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPanel2.add(btn_write);

        add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 10, 0));
        jPanel1.setLayout(new java.awt.GridLayout(1, 3));

        myJLableBig1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 30, 1));
        myJLableBig1.setText("Настройка архивирования");
        myJLableBig1.setFont(new java.awt.Font("Dialog", 0, 26)); // NOI18N
        jPanel1.add(myJLableBig1);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        myLableSmall1.setText("(1-9) Выбор настройки");
        myLableSmall1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jPanel3.add(myLableSmall1);

        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jPanel3.add(lblStatus);

        add(jPanel3, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    @Override
   public void KeybordEvent(int cmd){
        
        if(activeUI == null)
        {   
            if(cmd == CmdCode.CMD_ESC)
               Manager.exitMenu();
        }
        
        if(btn_write.isFocusOwner() && cmd == CmdCode.CMD_ENTER){ 
            writeFile();
        }
        
        if(activeUI == checkBoxData || activeUI == checkBoxVideo){
            ((MyCheckBox)activeUI).exec(cmd);
            if(cmd == CmdCode.CMD_ENTER)
                return;
        }
        super.KeybordEvent(cmd); 
    }

   private boolean isCopyNow = false;
       
   private void setStatus(String text, Color color){
        lblStatus.setText(text);
        lblStatus.setForeground(color);
    }
    
   private void startWriteFile(){
        isCopyNow = true;
        fileWriteCount = 0;
        setStatus("", Color.BLACK);
    }
    
   private void endWriteFile(){
        isCopyNow = false;
        setStatus("Копирование завершено.",  new Color(0x21, 0x60, 0x01));
    }
    
   
   private String getBrigadeName(){
       Config dev = Config.get();
       try{
            RedisHealper redis = new RedisHealper(dev.redis_ip, dev.redis_port);
            String brigadeInfoStr = redis.getRedisDataByKey(redis.GET_BRIGADE_INFO);
            JsonParser parser = new JsonParser();
            JsonObject obj = parser.parse(brigadeInfoStr).getAsJsonObject();
            Type type = new TypeToken<BrigadeInfo>(){}.getType();
            BrigadeInfo brigadeInfo = new Gson().fromJson(obj, type);
            if(brigadeInfo.Rig != null || !brigadeInfo.Rig.equals("")){
                return brigadeInfo.Rig;
       }
       }catch(Throwable e){}
       return dev.brigade_code;
   }
   
   private void writeFile(){
        if(isCopyNow) return;
        startWriteFile();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String strartDateStr = input_start_data.getText() + " " + input_start_time.getText();
        String endDateStr    = input_end_data.getText()   + " " + input_end_time.getText();

        Date start = null, end = null;
        try {
            start = format.parse(strartDateStr);
            end   = format.parse(endDateStr);

        } catch (ParseException e) {
            setStatus(e.getMessage(), Color.RED);
            return;
        }

        if(start.compareTo(end) >= 0)
        {
            setStatus("Неверный интервал дат.", Color.RED);
            return;
        }

        if(cmb_device.getSelectedIndex() < 0)
        {
            setStatus("Носитель не выбран", Color.RED);
            return;
        }

        String cameraStr = (String) cmb_camera.getSelectedItem();
        String deviceStr = (String) cmb_device.getSelectedItem();
        
        File mediaDevice = new File(Program.MEDIA_PATH   + "/" + deviceStr);

        if(!mediaDevice.exists()){
            isCopyNow = false;
            return;
        }

        if(checkBoxVideo.isSelected())
        {
            int selectedIndex = cmb_camera.getSelectedIndex();
            if(selectedIndex < 0)
            {
                setStatus("Камера не выбрана", Color.RED);
                return;
            }
            
            String fromPath = Program.VIDEO_PATH;
            if(selectedIndex != 0){
                fromPath += "/" + cameraStr;
            }
            File fromFolder = new File(fromPath);
            
            devFile = new File(Program.MEDIA_PATH   + "/" + deviceStr + "/video/" + getBrigadeName());
            devFile.mkdirs();
            try{
                copyFiles(fromFolder, devFile, start, end, deviceStr, FileName.EXT_MP4, Program.VIDEO_PATH);         
                endWriteFile();
            }catch(IOException e){
                setStatus(e.getMessage(), Color.RED);
                try {
                    Thread.currentThread().sleep(3000L);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Panel_Arch.class.getName()).log(Level.SEVERE, null, ex);
                }
                updateCmbMedia();
            } 
        }
        
        if(checkBoxData.isSelected()){
            File channelFromFolder = new File(Program.CHANNEL_PATH);
            devFile = new File(Program.MEDIA_PATH   + "/" + deviceStr + "/channel/" + getBrigadeName());
            devFile.mkdirs();
            try{
                copyFiles(channelFromFolder, devFile, start, end, deviceStr, FileName.EXT_JSON, Program.CHANNEL_PATH);         
                endWriteFile();
            }catch(IOException e){
                setStatus(e.getMessage(), Color.RED);
                try {
                    Thread.currentThread().sleep(3000L);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Panel_Arch.class.getName()).log(Level.SEVERE, null, ex);
                }
                updateCmbMedia();
            } 
        }
        
        isCopyNow = false;
       
    }
   
   private File devFile; 
   private int fileWriteCount = 0;  // Gavnokod 
   
   private void copyFiles(File source, File dest, Date start, Date end, String deviveStr, String ext, String fromPath) throws IOException{
       for(File file : source.listFiles()){
           if(file.isDirectory()){
               copyFiles(file, dest, start, end, deviveStr, ext, fromPath);
           }else{
               if(!devFile.exists()) throw new IOException("Носитель размонтирован");
               try {
                    Date createTime = FileName.parse(file.getName(), ext);
                    if(equelsDate(createTime, start) >= 0 && equelsDate(createTime, end) <= 0){
                        String path = file.getAbsolutePath().replaceAll(new File(fromPath).getAbsolutePath(), dest.getAbsolutePath());
                        File f = new File(path);
                        boolean copySuccess = CopyFile(file, f);
                        if(copySuccess){
                            setStatus("Скопировано " + ++fileWriteCount + " файлов.(" +  f.getName() + ")", Color.BLACK);
                        }else{
                            setStatus("Ошибка копирования файла " + f.getName(), Color.RED);
                        }
                    }                  
                } catch (Exception ex) {
                   Logger.getLogger(Panel_Arch.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
       }
   }
    
   private long equelsDate(Date d1, Date d2){
        return d1.getTime() - d2.getTime();  
   }
   
   private boolean CopyFile(File source, File dest){
       if(!devFile.exists()) return false;
       try {
           File folder = new File(dest.getParent());
           
           if(!folder.exists()) folder.mkdirs();
           
           Files.copy(source.toPath(), dest.toPath());
           while(!dest.exists()){}
           return true;
       }catch (IOException e) {
           logger.error("Ошибка копирования файла " + source.getName());
           return false;
       }
   }
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private OpenCV_cam.Panels.Components.MyButton btn_write;
    private OpenCV_cam.Panels.Components.MyCheckBox checkBoxData;
    private OpenCV_cam.Panels.Components.MyCheckBox checkBoxVideo;
    private OpenCV_cam.Panels.Components.MyCmb cmb_camera;
    private OpenCV_cam.Panels.Components.MyCmb cmb_device;
    private OpenCV_cam.Panels.Components.MyTextF input_end_data;
    private OpenCV_cam.Panels.Components.MyTextF input_end_time;
    private OpenCV_cam.Panels.Components.MyTextF input_start_data;
    private OpenCV_cam.Panels.Components.MyTextF input_start_time;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private OpenCV_cam.Panels.Components.MyJLable lblStatus;
    private OpenCV_cam.Panels.Components.MyJLable myJLable1;
    private OpenCV_cam.Panels.Components.MyJLable myJLable2;
    private OpenCV_cam.Panels.Components.MyJLable myJLable3;
    private OpenCV_cam.Panels.Components.MyJLable myJLable5;
    private OpenCV_cam.Panels.Components.MyJLable myJLable6;
    private OpenCV_cam.Panels.Components.MyJLable myJLable7;
    private OpenCV_cam.Panels.Components.MyJLableBig myJLableBig1;
    private OpenCV_cam.Panels.Components.MyLableSmall myLableSmall1;
    // End of variables declaration//GEN-END:variables
}


