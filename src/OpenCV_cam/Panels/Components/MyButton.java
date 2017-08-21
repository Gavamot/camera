package OpenCV_cam.Panels.Components;

import OpenCV_cam.Panels.IAction;
import OpenCV_cam.Panels.MyUIComponent;
import OpenCV_cam.Panels.StyleManager;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.border.Border;

public class MyButton extends JButton implements MyUIComponent{

    public final ArrayList<IAction> onActiveAction = new ArrayList<>();
    public final ArrayList<IAction> onDisActiveAction = new ArrayList<>();
    
    public MyButton(){
        //setFont(StyleManager.getBigFont());
        defBorder = getBorder();
        lastBorder = getBorder();
    }
    
    Border defBorder;
    @Override
    public void exec(int cmd) {
        
    }
    
    private boolean isSelected = false;
    private Border lastBorder; 
    

    public boolean getIsSelected(){
        return isSelected;
    }
    
    @Override
    public void active() {
        defBorder = getBorder();
        isSelected = true;
        if(isSelected) setBorder(StyleManager.getBorderBtnSelected());
        else setBorder(lastBorder);
        for(IAction action : onActiveAction){
            action.execute();
        }
    }

    @Override
    public void disActive() {
        isSelected = false;
        lastBorder = getBorder();
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
