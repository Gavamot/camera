package OpenCV_cam.Panels;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import OpenCV_cam.CmdCode;
import OpenCV_cam.Manager;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

public abstract class SettingsPanel extends AbstractPanel{

    public IAction onActive = null;
    public IAction onDisactice = null;
    
    public abstract MyUIComponent[] GetDataContainers();
    
    private int selected = 0;
    public void disactiveAllUIComponent(){
        MyUIComponent[] items = GetDataContainers();
        for(MyUIComponent item : items){
            item.disActive();
        }
    }
    
    public void setFocusElement(){
        setFocusElement(0);
    }
    
    public void setFocusElement(int index){
        MyUIComponent[] items = GetDataContainers();
        if(index < 0 || index > items.length - 1) return;
         
        new Thread(() -> {
            try {
                Thread.sleep(150); 
                items[index].setFocus();
             } catch (Exception e) {

             }
         }, "SetFocus").start();
    }

    public void setSelectedValue(int value)
    {
        MyUIComponent[] items = GetDataContainers();
        int max = items.length - 1;
        if(max < 0) return;
        if(value < 0) value = 0;
        if(value > max) value = max;
        
        disactiveAllUIComponent();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(SettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        selected = value;
       
        if(items[value] instanceof JComponent){
            JComponent component = (JComponent)items[value];
            component.requestFocus();
        }
        
    }
    
    public MyDialog dialog = null;
    
    public void dialogYesNo(ActionListener yesAction, ActionListener noAction){
        dialog = new MyDialog(null, "Вы уверены?", this);
        dialog.addYesListener(yesAction);
        dialog.addNoListener(noAction);
        dialog.show();  
    }
    
    @Override
    public void KeybordEvent(int cmd) {
        if(dialog != null){
            if(cmd == CmdCode.CMD_ENTER)
                dialog.enter();
            if(cmd == CmdCode.CMD_ESC)
                dialog.esc();
        }
        else if(activeUI == null)
        {      
            
            if(cmd == CmdCode.CMD_1){
                setSelectedValue(0);
            }
            
            if(cmd == CmdCode.CMD_2){
                setSelectedValue(1);
            }
            
            if(cmd == CmdCode.CMD_3){
                setSelectedValue(2);
            }
            
            if(cmd == CmdCode.CMD_4){
                setSelectedValue(3);
            }
            
            if(cmd == CmdCode.CMD_5){
                setSelectedValue(4);
            }
            
            if(cmd == CmdCode.CMD_6){
                setSelectedValue(5);
            }
            
            if(cmd == CmdCode.CMD_7){
                setSelectedValue(6);
            }
            
            if(cmd == CmdCode.CMD_8){
                setSelectedValue(7);
            }
            
            if(cmd == CmdCode.CMD_9){
               setSelectedValue(8); 
            }
            

            if(cmd == CmdCode.CMD_UP){ 
    
                setSelectedValue(--selected);
            }
            if(cmd == CmdCode.CMD_DOWN){
        
                setSelectedValue(++selected);
            }
            if(cmd == CmdCode.CMD_ENTER){
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);    
                
                if(frame == null) 
                    return;
                
                Component ui = frame.getFocusOwner();
                
                if(ui == null) return;
                MyUIComponent comp = (MyUIComponent)ui;
                
                if(comp!= null){
                    comp.active();
                    activeUI = comp;
                } 
            }
        
            if(cmd == CmdCode.CMD_ESC)
               Manager.exitMenu();
        }else
        {
            if(cmd == CmdCode.CMD_ESC || cmd == CmdCode.CMD_ENTER){
                activeUI.disActive();
                activeUI = null;
            }else{
                activeUI.exec(cmd);
            }
        }
    }
    
    public MyUIComponent activeUI;
}
