package OpenCV_cam.Panels;

import SqlLite.Config;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

public class StyleManager {

    static int SCREEN_WIDTH = -1;
    static int SCREEN_HEIGHT = -1;
    
    
    public static Border getBorderBlack(){
        return BorderFactory.createLineBorder(Color.BLACK, 3, false);
    }
    
    public static Border getBorderBlue(){
        return BorderFactory.createLineBorder(Color.BLUE, 3);
    }
    
     public static Border getBorderRed(){
        return BorderFactory.createLineBorder(Color.RED, 3);
    }
    
    public static Border getBorderBtnSelected(){
        return BorderFactory.createLineBorder(Color.orange, 7);
    }
    
    static void setScreenSize(){
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        SCREEN_WIDTH = gd.getDisplayMode().getWidth();
        SCREEN_HEIGHT = gd.getDisplayMode().getHeight();
    } 
    
    public static int getScreenWidth(){
        if(SCREEN_WIDTH > 0) return SCREEN_WIDTH;
        else setScreenSize();
        return SCREEN_WIDTH;
    }
    
     public static int getScreenHeight(){
        if(SCREEN_HEIGHT > 0) return SCREEN_HEIGHT;
        else setScreenSize();
        return SCREEN_HEIGHT;
    }
    
    private static Config getFakeDevice(){
        Config res = new Config();
        res.small_text_size = 20;
        res.middle_text_size = 30;
        res.big_text_size = 40;
        return res;
    }
    
    
    
    public static Config getDevice(){
       // return getFakeDevice();
       return Config.get();
    }
    
//    public static Font getSmallFont() {
//        Config dev = getDevice();
//        return new Font(dev.font, dev.small_text_size, dev.small_text_size);
//    }
//    
//    public static Font getMiddleFont(){
//        Config dev = getDevice();
//        return new Font(dev.font, dev.middle_text_size, dev.middle_text_size);          
//    }
//    
//    public static Font getBigFont(){
//        Config dev = getDevice();
//        return new Font(dev.font, dev.big_text_size, dev.big_text_size);             
//    }
//    
    public static ImageIcon getEmptyPicture(Object self){
         return new ImageIcon(self.getClass().getResource("/pictures/nocam.png"));
    }
}
