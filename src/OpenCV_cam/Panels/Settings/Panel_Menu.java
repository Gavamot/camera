/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OpenCV_cam.Panels.Settings;

import OpenCV_cam.CmdCode;
import OpenCV_cam.Manager;
import OpenCV_cam.Panels.MyUIComponent;
import OpenCV_cam.Panels.SettingsPanel;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author root
 */
public class Panel_Menu extends SettingsPanel {
    
    protected org.apache.logging.log4j.Logger logger;
    
    public Panel_Menu() {
        logger = LogManager.getLogger(getClass().getName());
        initComponents();
    }

    @Override
    public void KeybordEvent(int cmd) {    
        
        if(this.dialog == null){
            if(cmd == CmdCode.CMD_1)
                Manager.SetCurPanel(new Panel_Set_ModesList());
            if(cmd == CmdCode.CMD_2)
                Manager.SetCurPanel(new Panel_CamList());
            if(cmd == CmdCode.CMD_3)
                Manager.SetCurPanel(new Panel_Set_Brigade());
            if(cmd == CmdCode.CMD_4)
                Manager.SetCurPanel(new Panel_Arch());
            if(cmd == CmdCode.CMD_5){ 
               this.dialogYesNo((e) -> {
                    Manager.restartProgramm();
               }, null);
            }
            if(cmd == CmdCode.CMD_ESC){
                Manager.exitSettings();
                return;
            }
        }
   
        
        super.KeybordEvent(cmd);
       
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButtonSetModes = new javax.swing.JButton();
        jButtonSetCam = new javax.swing.JButton();
        jButtonSetKeyboard = new javax.swing.JButton();
        jButtonArchive = new javax.swing.JButton();
        jButtonSetOther = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabelHeadMenu = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        jPanel2.setLayout(new java.awt.GridLayout(2, 3, 5, 5));

        jButtonSetModes.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        jButtonSetModes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/menu1_modes.png"))); // NOI18N
        jButtonSetModes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSetModes.setIconTextGap(3);
        jButtonSetModes.setMargin(new java.awt.Insets(70, 0, 0, 0));
        jButtonSetModes.setSelected(true);
        jButtonSetModes.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButtonSetModes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSetModes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSetModesActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonSetModes);

        jButtonSetCam.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        jButtonSetCam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/menu2_cam.png"))); // NOI18N
        jButtonSetCam.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSetCam.setMargin(new java.awt.Insets(70, 0, 0, 0));
        jButtonSetCam.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButtonSetCam.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSetCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSetCamActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonSetCam);

        jButtonSetKeyboard.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        jButtonSetKeyboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/menu3_brigade.png"))); // NOI18N
        jButtonSetKeyboard.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSetKeyboard.setMargin(new java.awt.Insets(70, 0, 0, 0));
        jButtonSetKeyboard.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButtonSetKeyboard.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(jButtonSetKeyboard);

        jButtonArchive.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        jButtonArchive.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/menu4_arch.png"))); // NOI18N
        jButtonArchive.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonArchive.setMargin(new java.awt.Insets(70, 0, 0, 0));
        jButtonArchive.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButtonArchive.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(jButtonArchive);

        jButtonSetOther.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        jButtonSetOther.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/menu5_restart.png"))); // NOI18N
        jButtonSetOther.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSetOther.setMargin(new java.awt.Insets(70, 0, 0, 0));
        jButtonSetOther.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButtonSetOther.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(jButtonSetOther);

        jPanel3.setLayout(new java.awt.GridLayout(1, 3));

        jLabelHeadMenu.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        jLabelHeadMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelHeadMenu.setText("Меню настроек");
        jPanel3.add(jLabelHeadMenu);

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("(1-6) Выбор настройки");
        jPanel4.add(jLabel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 969, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 959, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 959, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 959, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 804, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSetModesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSetModesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSetModesActionPerformed

    private void jButtonSetCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSetCamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSetCamActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonArchive;
    private javax.swing.JButton jButtonSetCam;
    private javax.swing.JButton jButtonSetKeyboard;
    private javax.swing.JButton jButtonSetModes;
    private javax.swing.JButton jButtonSetOther;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelHeadMenu;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables

    @Override
    public MyUIComponent[] GetDataContainers() {
        return new MyUIComponent[0];
    }
}
