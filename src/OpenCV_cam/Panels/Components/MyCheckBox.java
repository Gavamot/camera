package OpenCV_cam.Panels.Components;

import OpenCV_cam.Panels.IAction;
import OpenCV_cam.Panels.MyUIComponent;
import OpenCV_cam.Panels.StyleManager;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.border.Border;

public class MyCheckBox extends JCheckBox implements MyUIComponent{
    
    public final ArrayList<IAction> onActiveAction = new ArrayList<>();
    public final ArrayList<IAction> onDisActiveAction = new ArrayList<>();
    
    public MyCheckBox(){
        //setFont(StyleManager.getMiddleFont());
        defBorder = getBorder();
    }

    @Override
    public void exec(int cmd) {
        switch(cmd){
            case OpenCV_cam.CmdCode.CMD_ENTER : setSelected(!this.isSelected()); break;
        }
    }

    private Border defBorder;
    @Override
    public void active() {
        defBorder = this.getBorder();
        this.setBorder(StyleManager.getBorderBlue());
        for(IAction action : onActiveAction){
            action.execute();
        }
    }

    @Override
    public void disActive() {
        this.setBorder(defBorder);
        for(IAction action : onDisActiveAction){
            action.execute();
        }
    }
    
    @Override
    public void setFocus() {
        requestFocus();
    }
}
