package OpenCV_cam.Panels.Components;

import OpenCV_cam.CmdCode;
import OpenCV_cam.Panels.IAction;
import OpenCV_cam.Panels.MyUIComponent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;


public class MyList<T> extends javax.swing.JList<T> implements MyUIComponent{
    
    public MyList()
    {
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultListCellRenderer renderer =(DefaultListCellRenderer)this.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
    }
    
    private void setIndex(int delta){
        int index = getSelectedIndex() + delta;
        if(index < 0) index = 0;
        int count = getComponentCount();
        if(index >= count)
            index -= count - 1;
        setSelectedIndex(index);
    }
    
    @Override
    public void exec(int cmd) {
        if(cmd == CmdCode.CMD_UP)   setIndex(-1);
        if(cmd == CmdCode.CMD_DOWN) setIndex(1);
    }
    
    @Override
    public void active() {
        //lastBorder = getBorder();
    }

    @Override
    public void disActive() {
       // lastBorder = getBorder();
       // this.setBorder(defBorder);
    }

    @Override
    public void setFocus() {
        requestFocus();
    }
    
}
