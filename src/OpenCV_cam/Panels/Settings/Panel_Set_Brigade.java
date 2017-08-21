package OpenCV_cam.Panels.Settings;

import OpenCV_cam.CmdCode;
import OpenCV_cam.Manager;
import OpenCV_cam.Panels.MyUIComponent;
import OpenCV_cam.Panels.SettingsPanel;
import SqlLite.Config;
import javax.swing.*;

public class Panel_Set_Brigade extends SettingsPanel {

    private String getCode(){
        Config config = Config.get();
        String code = config.brigade_code;
        return updateText(code);
    }
    
    private String updateText(String code){
        if(code == null || "".equals(code))
            code = "0";
        return code;
    }
    
    public Panel_Set_Brigade() {
        initComponents();
        //tbBrigadeCode.active();
        inputBrigadeCode.requestFocus();
        inputBrigadeCode.active();
        activeUI = inputBrigadeCode;
        inputBrigadeCode.setText(getCode());
    }

    @Override
    public void KeybordEvent(int cmd) {
        
        if(cmd == CmdCode.CMD_ENTER){
            String code = updateText(inputBrigadeCode.getText());
            Config config = Config.get();
            config.brigade_code = code;
            config.update();
            Manager.exitMenu();
        }else if(cmd == CmdCode.CMD_ESC){
            Manager.exitMenu();
        }else{
            super.KeybordEvent(cmd);
        }
      
        
    }    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        myJLable1 = new JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new JLabel();
        jPanel4 = new javax.swing.JPanel();
        inputBrigadeCode = new OpenCV_cam.Panels.Components.MyTextF();
        myJLable3 = new JLabel();
        jPanel3 = new javax.swing.JPanel();
        myJLable2 = new JLabel();

        setMinimumSize(new java.awt.Dimension(1024, 768));
        setPreferredSize(new java.awt.Dimension(1024, 768));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        myJLable1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        myJLable1.setText("Настройка бригады");
        myJLable1.setToolTipText("");
        myJLable1.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        jPanel1.add(myJLable1);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1040, 60));

        jPanel2.setLayout(new java.awt.GridLayout(1, 2));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/brig.png"))); // NOI18N
        jPanel2.add(jLabel2);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputBrigadeCode.setText("0");
        jPanel4.add(inputBrigadeCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 360, 60));

        myJLable3.setText("Код бригады");
        myJLable3.setFont(new java.awt.Font("Arial Black", 0, 16)); // NOI18N
        jPanel4.add(myJLable3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, -1, -1));

        jPanel2.add(jPanel4);

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 55, 960, 590));

        jPanel3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));
        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 582, 875, 40));

        myJLable2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        myJLable2.setText("ENTER - Сохранить      |         ESC - Отмена");
        myJLable2.setToolTipText("");
        myJLable2.setFont(new java.awt.Font("Arial Black", 0, 17)); // NOI18N
        add(myJLable2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 690, 1050, 40));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private OpenCV_cam.Panels.Components.MyTextF inputBrigadeCode;
    private JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private JLabel myJLable1;
    private JLabel myJLable2;
    private JLabel myJLable3;
    // End of variables declaration//GEN-END:variables

    @Override
    public MyUIComponent[] GetDataContainers() {
        return new MyUIComponent[]{
            
        };
    }

}
