package OpenCV_cam.Panels.Settings;
import OpenCV_cam.CmdCode;
import OpenCV_cam.Manager;
import OpenCV_cam.Panels.AbstractPanel;
import OpenCV_cam.Panels.Components.MyButton;
import OpenCV_cam.Panels.MyUIComponent;
import OpenCV_cam.Panels.SettingsPanel;
import SqlLite.Panel;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class Panel_Set_ModesList extends SettingsPanel {

    //private MyList<Panel> listPanel;

    public Panel_Set_ModesList(){
        initComponents();
        updateCollection();
        setFocusElement(0);
    }
    
    
    
    private void updateCollection(){
     
        try {
            DefaultListModel listModel = new DefaultListModel();
            ArrayList<Panel> panels = Panel.getAll();
            for(int i = 0; i < panels.size(); i++){
               listModel.add(i, panels.get(i).toString());
            }
            myList.setModel(listModel);
            if(panels.size() > 0){
                myList.setSelectedIndex(0);
                
            }
        } catch (Exception ex) { }
     
        
    }
    
    MyButton selectButton = null;
    
    @Override
    public void KeybordEvent(int cmd) {

        if(this.dialog == null){
            
            if(activeUI == null)
            {   
                if(cmd == CmdCode.CMD_ESC){
                   Manager.exitMenu();
                }

                if(cmd == CmdCode.CMD_ENTER){
                    
                    if(btn_add.isFocusOwner()){
                       Manager.SetCurPanel(new Panel_Set_Modes_add());
                    }else if(btn_edit.isFocusOwner()){
                        selectButton = btn_edit;
                        btn_edit.active();
                        activeUI = myList;
                    }else if(btn_delete.isFocusOwner()){
                        selectButton = btn_delete;
                        btn_delete.active();
                        activeUI = myList;                      
                    }
                    return;

                } 
                 super.KeybordEvent(cmd);
            }
            else if(activeUI != null){
                
                if(cmd == CmdCode.CMD_ESC){
                    selectButton.disActive();
                    selectButton = null;
                }
                
                if(cmd == CmdCode.CMD_ENTER){

                    if(selectButton == btn_delete)
                    {
                       this.dialogYesNo((e) -> {
                            Panel panel = Panel.getPanelByJListValue(myList.getSelectedValue());
                            if(panel != null){
                                panel.delete();
                                updateCollection(); 
                            }
                       }, null);
                      
                    }
                    else if(selectButton == btn_edit)
                    {
                        Panel panel = Panel.getPanelByJListValue(myList.getSelectedValue());
                        if(panel != null){
                            Manager.SetCurPanel(new Panel_edit(panel));
                        }                  
                    }
                }else{
                     super.KeybordEvent(cmd);
                }
            }
        }else{
             super.KeybordEvent(cmd);
        }

       
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        myJLableBig1 = new OpenCV_cam.Panels.Components.MyJLableBig();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btn_add = new OpenCV_cam.Panels.Components.MyButton();
        btn_delete = new OpenCV_cam.Panels.Components.MyButton();
        btn_edit = new OpenCV_cam.Panels.Components.MyButton();
        panelListPanal = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        myList = new OpenCV_cam.Panels.Components.MyList<>();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(1024, 768));
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setPreferredSize(new java.awt.Dimension(1024, 768));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        myJLableBig1.setText("Настройка режимов");
        myJLableBig1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        myJLableBig1.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        add(myJLableBig1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 707, 50));

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setMaximumSize(new java.awt.Dimension(100, 100));
        jPanel4.setMinimumSize(new java.awt.Dimension(100, 100));
        jPanel4.setLayout(new java.awt.GridLayout(3, 1, 20, 20));

        btn_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/add.png"))); // NOI18N
        btn_add.setToolTipText("");
        btn_add.setAlignmentX(0.5F);
        btn_add.setMargin(null);
        btn_add.setMaximumSize(new java.awt.Dimension(150, 150));
        btn_add.setMinimumSize(new java.awt.Dimension(150, 150));
        btn_add.setPreferredSize(new java.awt.Dimension(200, 200));
        btn_add.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                btn_addComponentAdded(evt);
            }
        });
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });
        jPanel4.add(btn_add);

        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/delete.png"))); // NOI18N
        btn_delete.setToolTipText("");
        btn_delete.setMargin(null);
        btn_delete.setMaximumSize(new java.awt.Dimension(150, 150));
        btn_delete.setMinimumSize(new java.awt.Dimension(150, 150));
        btn_delete.setPreferredSize(new java.awt.Dimension(150, 150));
        jPanel4.add(btn_delete);

        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/edit.png"))); // NOI18N
        btn_edit.setMargin(null);
        btn_edit.setMaximumSize(new java.awt.Dimension(150, 150));
        btn_edit.setMinimumSize(new java.awt.Dimension(150, 150));
        btn_edit.setPreferredSize(new java.awt.Dimension(150, 150));
        jPanel4.add(btn_edit);

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 230, 630));

        panelListPanal.setLayout(new java.awt.CardLayout());

        myList.setAutoscrolls(false);
        myList.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jScrollPane1.setViewportView(myList);

        panelListPanal.add(jScrollPane1, "card2");

        jPanel1.add(panelListPanal, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 30, 700, 610));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 72, 980, 660));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_addComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_btn_addComponentAdded
          
    }//GEN-LAST:event_btn_addComponentAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private OpenCV_cam.Panels.Components.MyButton btn_add;
    private OpenCV_cam.Panels.Components.MyButton btn_delete;
    private OpenCV_cam.Panels.Components.MyButton btn_edit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private OpenCV_cam.Panels.Components.MyJLableBig myJLableBig1;
    private OpenCV_cam.Panels.Components.MyList<String> myList;
    private javax.swing.JPanel panelListPanal;
    // End of variables declaration//GEN-END:variables

    @Override
    public MyUIComponent[] GetDataContainers() {
        return new MyUIComponent[]{
            btn_add,
            btn_delete,
            btn_edit
        };
    }


}
