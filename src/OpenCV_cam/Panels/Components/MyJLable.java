package OpenCV_cam.Panels.Components;

import OpenCV_cam.CmdCode;
import OpenCV_cam.Panels.IAction;
import OpenCV_cam.Panels.MyUIComponent;
import OpenCV_cam.Panels.StyleManager;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class MyJLable extends JLabel implements MyUIComponent{
    public MyJLable(){
       // setFont(StyleManager.getMiddleFont());
    }
    
    @Override
    public void exec(int cmd) {
    
    }

    
     @Override
    public void active() {
        defBorder = this.getBorder();
        this.setBorder(StyleManager.getBorderBlue());
    }

    private Border defBorder;
    @Override
    public void disActive() {
        this.setBorder(defBorder);
    } 
    
    @Override
    public void setFocus() {
        requestFocus();
    }
}
