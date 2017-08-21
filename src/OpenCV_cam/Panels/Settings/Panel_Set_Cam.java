package OpenCV_cam.Panels.Settings;

import OpenCV_cam.Panels.Components.MyTextF;
import SqlLite.CameraSettings;
import SqlLite.Model;
import SqlLite.Vendor;
import java.net.URL;
import javax.swing.ImageIcon;
import OpenCV_cam.CmdCode;
import OpenCV_cam.Manager;
import OpenCV_cam.Panels.MyUIComponent;
import OpenCV_cam.Panels.SettingsPanel;
import OpenCV_cam.Panels.StyleManager;

public class Panel_Set_Cam extends SettingsPanel {

    CameraSettings cam;
    boolean isUpdate;
    
    private void initEvents(){
        btn_test.onActiveAction.add (() -> {
            try {
                setEmptyPictureCam();
                cam = createCam();
                URL url = new URL(cam.get_snap_str_rule() + ";killCache=" + Math.random());          
                ImageIcon icon = new ImageIcon(url);
                jLabel_viewTest.setIcon(icon);
            } catch (Exception e) { 
                 setEmptyPictureCam();
            }
        });
        
        btn_ok.onActiveAction.add(()-> {
            try
            {
                cam = createCam(); 
                if(isUpdate) 
                    cam.update();
                else
                    cam.insert();
                Manager.SetCurPanel(new Panel_CamList());
            }catch(Exception e){
                lbl_error.setText(e.getMessage());
            }
        }); 
    }
    
    public Panel_Set_Cam() {
        isUpdate = false;
        initComponents();
        input_vendor.setModel(Vendor.getAll());
        updateModels();
        initEvents(); 
    }
    
     public Panel_Set_Cam(CameraSettings camera) {
        cam = camera;
        initComponents();
        isUpdate = true;
        input_vendor.setModel(Vendor.getAll());
        updateModels();
        
        input_cam.setText(camera.name);
        input_ip.setText(camera.ip);
        input_thread.setText(camera.th);
        
        input_vendor.setSelectedItem(camera.model.vendor);
        input_model.setSelectedItem(camera.model);
        initEvents();
    }
 
    @Override
    public void KeybordEvent(int cmd) {
        
        if(activeUI != null)
        {   
            
            if(cmd == CmdCode.CMD_6)
            {   
               
            }
            
            if(cmd == CmdCode.CMD_7)
            {
                
            }

        }
        
        super.KeybordEvent(cmd);
        
        if(activeUI == btn_ok || activeUI ==  btn_test){
            activeUI.disActive();
            activeUI = null;
        }
            
        
    }
    
    void setEmptyPictureCam(){
         jLabel_viewTest.setIcon(StyleManager.getEmptyPicture(this));
    }
    
    CameraSettings createCam(){
        
        CameraSettings res = new CameraSettings();
        res.ip = input_ip.getText();

        res.model = (Model)input_model.getSelectedItem();
 
        if(res.model != null) 
            res.model_id = res.model.id;
        
        res.name = input_cam.getText();
        res.th = input_thread.getText();
        if(cam != null)
            res.id = cam.id;
        
        return res;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        myJLableBig1 = new OpenCV_cam.Panels.Components.MyJLableBig();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        lbl_error = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        myJLable3 = new OpenCV_cam.Panels.Components.MyJLable();
        input_cam = MyTextF.getFild(MyTextF.FORMAT_INT, this, 1);
        myJLable4 = new OpenCV_cam.Panels.Components.MyJLable();
        input_ip = MyTextF.getFild(MyTextF.FORMAT_IP, this);
        myJLable1 = new OpenCV_cam.Panels.Components.MyJLable();
        input_vendor = new OpenCV_cam.Panels.Components.MyCmb();
        myJLable2 = new OpenCV_cam.Panels.Components.MyJLable();
        input_model = new OpenCV_cam.Panels.Components.MyCmb();
        myJLable5 = new OpenCV_cam.Panels.Components.MyJLable();
        input_thread = MyTextF.getFild(MyTextF.FORMAT_INT, this, 1);
        btn_test = new OpenCV_cam.Panels.Components.MyButton();
        btn_ok = new OpenCV_cam.Panels.Components.MyButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel_viewTest = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setLayout(new java.awt.BorderLayout(5, 5));

        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        myJLableBig1.setText("Настройка камеры");
        jPanel6.add(myJLableBig1);

        add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("(1-7) Выбор настройки  |  ESC - Выход");
        jPanel7.add(jLabel11);

        lbl_error.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lbl_error.setForeground(new java.awt.Color(255, 0, 0));
        jPanel7.add(lbl_error);

        add(jPanel7, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 220, 20));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 25, 5));

        jPanel4.setLayout(new java.awt.GridLayout(6, 2, 10, 10));

        myJLable3.setText("1. Имя камеры");
        jPanel4.add(myJLable3);

        input_cam.setFont(input_cam.getFont());
        jPanel4.add(input_cam);

        myJLable4.setText("2. ip-адрес");
        jPanel4.add(myJLable4);

        input_ip.setText("ip");
        jPanel4.add(input_ip);

        myJLable1.setText("3. Производитель");
        jPanel4.add(myJLable1);

        input_vendor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                input_vendorItemStateChanged(evt);
            }
        });
        jPanel4.add(input_vendor);

        myJLable2.setText("4. Модель");
        jPanel4.add(myJLable2);
        jPanel4.add(input_model);

        myJLable5.setText("5. Поток");
        jPanel4.add(myJLable5);

        input_thread.setText("");
        input_thread.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input_threadActionPerformed(evt);
            }
        });
        jPanel4.add(input_thread);

        btn_test.setText("6. Тест");
        jPanel4.add(btn_test);

        btn_ok.setText("7. ОК");
        btn_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_okActionPerformed(evt);
            }
        });
        jPanel4.add(btn_ok);

        jPanel3.add(jPanel4);

        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        jLabel_viewTest.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_viewTest.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 20));
        jPanel5.add(jLabel_viewTest);

        jPanel3.add(jPanel5);

        add(jPanel3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void myCmb_manufacturerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myCmb_manufacturerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myCmb_manufacturerActionPerformed

    private void input_threadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_input_threadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_input_threadActionPerformed

    private void input_vendorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_input_vendorItemStateChanged
         updateModels();
    }//GEN-LAST:event_input_vendorItemStateChanged

    private void btn_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_okActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_okActionPerformed

    private void updateModels(){
        Vendor vendor = (Vendor)input_vendor.getSelectedItem();
        input_model.setModel(vendor.getModels()); 
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private OpenCV_cam.Panels.Components.MyButton btn_ok;
    private OpenCV_cam.Panels.Components.MyButton btn_test;
    public OpenCV_cam.Panels.Components.MyTextF input_cam;
    public OpenCV_cam.Panels.Components.MyTextF input_ip;
    private OpenCV_cam.Panels.Components.MyCmb input_model;
    private OpenCV_cam.Panels.Components.MyTextF input_thread;
    private OpenCV_cam.Panels.Components.MyCmb input_vendor;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel_viewTest;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lbl_error;
    private OpenCV_cam.Panels.Components.MyJLable myJLable1;
    private OpenCV_cam.Panels.Components.MyJLable myJLable2;
    private OpenCV_cam.Panels.Components.MyJLable myJLable3;
    private OpenCV_cam.Panels.Components.MyJLable myJLable4;
    private OpenCV_cam.Panels.Components.MyJLable myJLable5;
    private OpenCV_cam.Panels.Components.MyJLableBig myJLableBig1;
    // End of variables declaration//GEN-END:variables

    

    @Override
    public MyUIComponent[] GetDataContainers() {
        return new MyUIComponent[]{
            input_cam,
            input_ip,
            input_vendor,
            input_model,
            input_thread,
            btn_test,
            btn_ok
        };
    }

}
