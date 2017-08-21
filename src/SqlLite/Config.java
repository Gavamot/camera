package SqlLite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Config {

    static final Logger logger = LogManager.getLogger(Logger.class.getName());
    
    public String redis_ip = "127.0.0.1"; //"192.168.88.11";
    public int redis_port = 6379;
    public int redraw_timer = 200;
    public int small_text_size = 10;
    public int middle_text_size = 15;
    public int big_text_size = 20;
    public String brigade_code = "0";
    public String font = "Arial Black";
    
    public static final String SETTINGS_FILE = "./config.properties"; 
    
    public Config(){
  
    }
    public Config(HashMap<String, String> obj){
        redis_ip = obj.get("redis_ip");
        redis_port = Integer.parseInt(obj.get("redis_port"));
        redraw_timer = Integer.parseInt(obj.get("redraw_timer"));
        small_text_size = Integer.parseInt(obj.get("small_text_size"));
        middle_text_size = Integer.parseInt(obj.get("middle_text_size"));
        big_text_size = Integer.parseInt(obj.get("big_text_size"));
        
        font = obj.get("font");
    }

   
    public static Config get() {
        
        Config res = new Config();
        Properties prop = new Properties();
        
        File file = new File(SETTINGS_FILE);
        if(file.exists()){
            try(InputStream fstrem = new FileInputStream(file)){
                prop.load(fstrem);
                if(prop.containsKey("redis_ip")) res.redis_ip = prop.getProperty("redis_ip");
                if(prop.containsKey("redis_port")) res.redis_port = Integer.parseInt(prop.getProperty("redis_port"));
                if(prop.containsKey("redraw_timer")) res.redraw_timer = Integer.parseInt(prop.getProperty("redraw_timer"));
                if(prop.containsKey("small_text_size")) res.small_text_size = Integer.parseInt(prop.getProperty("small_text_size"));
                if(prop.containsKey("middle_text_size")) res.middle_text_size = Integer.parseInt(prop.getProperty("middle_text_size"));
                if(prop.containsKey("big_text_size")) res.big_text_size = Integer.parseInt(prop.getProperty("big_text_size"));
                if(prop.containsKey("font")) res.font = prop.getProperty("font");
                if(prop.containsKey("brigade_code")) res.brigade_code = prop.getProperty("brigade_code");
            }catch(Exception e){
                logger.error("Не удалось прочитать файл настроек.");
            }
        }else{
            logger.error("Не удалось найти файл настроек " + SETTINGS_FILE);
        }
        
        return res;
        
     
    }

    public void update() {
        File file = new File(SETTINGS_FILE);
        if(!file.exists())
        {
            try{
                file.createNewFile();
            }catch(IOException e){
                logger.error("Не удалось создать файл настроек");
            }
        }
        
       Properties prop = new Properties();
       OutputStream output = null;
       
       try 
       {
            output = new FileOutputStream(file);
            prop.setProperty("redis_ip", redis_ip);
            prop.setProperty("redis_port", Integer.toString(redis_port));
            prop.setProperty("redraw_timer", Integer.toString(redraw_timer));
            prop.setProperty("small_text_size", Integer.toString(small_text_size));
            prop.setProperty("middle_text_size", Integer.toString(middle_text_size));
            prop.setProperty("big_text_size", Integer.toString(big_text_size));
            prop.setProperty("font", font);
            prop.setProperty("brigade_code", brigade_code);
            prop.store(output, null);

	} catch (IOException io) {
             logger.error("Не удалось записать в файл настроек.");
	} finally {
            if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {

                    }
            }
	}
    }

}
