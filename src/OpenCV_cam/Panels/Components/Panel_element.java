package OpenCV_cam.Panels.Components;

import SqlLite.CameraSettings;
import OpenCV_cam.Channel;
import OpenCV_cam.Panels.MyUIComponent;
import OpenCV_cam.Panels.StyleManager;
import SqlLite.Content;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.border.Border;


public class Panel_element extends javax.swing.JPanel implements MyUIComponent {
    
    public DefaultComboBoxModel<Icon> loadIcon()
    {
        DefaultComboBoxModel<Icon> dm = new DefaultComboBoxModel<Icon>();
        dm.addElement(new ImageIcon(getClass().getResource("/pictures/icon_cam.png")));
        dm.addElement(new ImageIcon(getClass().getResource("/pictures/icon_channel.png"))); 
        return dm;
    }
    
    private void setCollectionsItems(){ 
        try{
            this.camers = CameraSettings.getAll();
            this.channels = Channel.getAll();
        }catch(Exception e){
        }
    }

    public Panel_element(int pos, int cw, Content content) {
        setCollectionsItems();
        
        this.pos = pos;
        this.cw = cw;
        initComponents();
        
        cmbType.setModel(loadIcon());
        init(content);
       
    }
    
    private ArrayList<CameraSettings> camers;
    private ArrayList<Channel>  channels;
    
    private void init(Content content){
        cmbTypeItemStateChanged(null);
        if(content.camera_id > 0){
            cmbType.setSelectedIndex(0); 
            cmbValue.setSelectedIndex(0);
            try{
                CameraSettings camSetting = camers.stream().filter(x->x.id == content.camera_id).findFirst().get();
                cmbValue.setSelectedItem(camSetting);
            }catch(Exception e){
                
            }
        }else{
            cmbType.setSelectedIndex(1);
            cmbValue.setSelectedIndex(0);
            try{
                Channel channel =  channels.stream().filter(x->x.id == content.channel_id).findFirst().get();
                cmbValue.setSelectedItem(channel);
            }catch(Exception e){}
        }
    } 
    
    int pos = -1;
    int cw = -1;
    
    public Content getContent(int panel_id)
    {
        Content res = new Content();
        res.panel_id = panel_id;
        res.position = pos;
        res.cof_width = cw;
        res.cof_height = 1;
        if(cmbType.getSelectedIndex() == 0)
            res.camera_id = ((CameraSettings)cmbValue.getSelectedItem()).id;
        
        if(cmbType.getSelectedIndex() == 1)
            res.channel_id = ((Channel)cmbValue.getSelectedItem()).id;
        return res;
    }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbType = new OpenCV_cam.Panels.Components.MyCmb();
        cmbValue = new OpenCV_cam.Panels.Components.MyCmb();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new java.awt.GridLayout(1, 0, 5, 5));

        cmbType.setToolTipText("");
        cmbType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTypeItemStateChanged(evt);
            }
        });
        add(cmbType);
        add(cmbValue);
    }// </editor-fold>//GEN-END:initComponents

   
    
    private void cmbTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTypeItemStateChanged

        try
        {
            if(cmbType.getSelectedIndex() == 0)
                cmbValue.setModel(new DefaultComboBoxModel(camers.toArray()));
            else
                cmbValue.setModel(new DefaultComboBoxModel(channels.toArray()));    
        }catch(Exception e){ 
        
        }
    }//GEN-LAST:event_cmbTypeItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public OpenCV_cam.Panels.Components.MyCmb cmbType;
    public OpenCV_cam.Panels.Components.MyCmb cmbValue;
    // End of variables declaration//GEN-END:variables

    @Override
    public void exec(int cmd) {
        
        switch(cmd){
            case OpenCV_cam.CmdCode.CMD_DOWN : down(); break;
            case OpenCV_cam.CmdCode.CMD_UP : up(); break;
            case OpenCV_cam.CmdCode.CMD_LEFT : left(); break;
            case OpenCV_cam.CmdCode.CMD_RIGHT : right(); break;
        }
    }
    
    private void down(){
        int index = cmbType.getSelectedIndex();
        if(++index >= cmbType.getItemCount())
            index = 0;
        cmbType.setSelectedIndex(index);
        cmbType.showPopup();
        cmbValue.hidePopup();
    }
    
    private void hideCmb(){
        cmbType.hidePopup();
        cmbValue.hidePopup();
     }
     
    private void up(){
        int index = cmbType.getSelectedIndex();
        if(--index < 0 )
            index = cmbType.getItemCount() - 1;
        cmbType.setSelectedIndex(index);
        cmbType.showPopup();
        cmbValue.hidePopup(); 
    }
              
    private void left(){
        int index = cmbValue.getSelectedIndex();
        if(++index >= cmbValue.getItemCount())
            index = 0;
        cmbValue.setSelectedIndex(index);
        cmbType.hidePopup();
        cmbValue.showPopup();
    }
    
     private void right(){
        int index = cmbValue.getSelectedIndex();
        if(--index < 0 )
            index = cmbValue.getItemCount() - 1;
        cmbValue.setSelectedIndex(index);
        cmbType.hidePopup();
        cmbValue.showPopup();
    }

    Border defBorder;
    @Override
    public void active() {
        defBorder = this.getBorder();
        this.setBorder(StyleManager.getBorderBlue());
    }

    @Override
    public void disActive() {
        hideCmb();
        this.setBorder(defBorder);
    }
    
    @Override
    public void setFocus() {
        requestFocus();
    }
}
