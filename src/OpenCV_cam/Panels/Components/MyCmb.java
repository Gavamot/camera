package OpenCV_cam.Panels.Components;

import OpenCV_cam.Panels.IAction;
import OpenCV_cam.Panels.MyUIComponent;
import OpenCV_cam.Panels.StyleManager;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.border.Border;

public class MyCmb extends JComboBox implements MyUIComponent{

    public final ArrayList<IAction> onActiveAction = new ArrayList<>();
    public final ArrayList<IAction> onDisActiveAction = new ArrayList<>();
   
    public <T> void setModel(ArrayList<T> items){
        this.setModel(new DefaultComboBoxModel(items.toArray()));
        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        dlcr.setVerticalAlignment(DefaultListCellRenderer.CENTER);
        super.setRenderer(dlcr);
        defBorder = this.getBorder();
    }
    
    public MyCmb() {
        super();
        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        dlcr.setVerticalAlignment(DefaultListCellRenderer.CENTER);
        super.setRenderer(dlcr);
        defBorder = this.getBorder();
    }
    
    
    
    @Override
    public void exec(int cmd) {
        switch(cmd){
            case OpenCV_cam.CmdCode.CMD_DOWN : down(); break;
            case OpenCV_cam.CmdCode.CMD_UP : up(); break;
        }
    }
    
    private void down(){
        int index = this.getSelectedIndex();
        if(++index >= this.getItemCount())
            index = 0;
        this.setSelectedIndex(index);
    }
    
     private void up(){
        int index = this.getSelectedIndex();
        if(--index < 0 )
            index = this.getItemCount() - 1;
        this.setSelectedIndex(index);
    }

    @Override
    public void active() {
        defBorder = getBorder();
        this.setBorder(StyleManager.getBorderBlue());
        this.showPopup();
        for(IAction action : onActiveAction){
            action.execute();
        }
    }
    


    private Border defBorder;
    @Override
    public void disActive() {
        // this.setBorder(BorderFactory.createEmptyBorder());
        this.setBorder(defBorder);
        this.hidePopup();
        for(IAction action : onDisActiveAction){
            action.execute();
        }
         
    } 
    
    @Override
    public void setFocus() {
        requestFocus();
    }
}
