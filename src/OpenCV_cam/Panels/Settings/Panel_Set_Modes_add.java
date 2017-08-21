package OpenCV_cam.Panels.Settings;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;
import OpenCV_cam.CmdCode;
import OpenCV_cam.Manager;
import OpenCV_cam.Panels.MyUIComponent;
import OpenCV_cam.Panels.SettingsPanel;
import OpenCV_cam.Panels.StyleManager;
import SqlLite.Panel;
import javax.swing.JPanel;

public class Panel_Set_Modes_add extends SettingsPanel {

    public Panel_Set_Modes_add() {
        blackline = BorderFactory.createLineBorder(Color.black);
        initComponents();
        new Thread(() -> {
            try{
                Thread.sleep(200);
                selectPanel(panel1x1);
            }catch(Exception e){}
        },"setFocus").start();
    }

    
    
     private void selectPanel(int val){
        if(val < 0) val  = 0;
        if(val > 3) val = 3;
        
        index = val;
        if(val == 0){
             selectPanel(panel1x1);
        }
        if(val == 1){
            selectPanel(panel1x2); 
        }
        if(val == 2){
            selectPanel(panel2x2); 
        }
        if(val == 3){
             selectPanel(panel3x3);
        }
     }
    
    private void selectPanel(JPanel panel){
        
        panel1x1.setBorder(null);
        panel1x2.setBorder(null);
        panel2x2.setBorder(null);
        panel3x3.setBorder(null);
        
        panel.requestFocus();
        panel.setBorder(StyleManager.getBorderBlack());
    }
    
    int index = 0;
    
    @Override
    public void KeybordEvent(int cmd) {
        
        if(activeUI == null)
        {   
            if(cmd == CmdCode.CMD_1)
            {
                selectPanel(0);
            }
            
            if(cmd == CmdCode.CMD_2)
            {  
                selectPanel(1);
            }
            
            if(cmd == CmdCode.CMD_3)
            {
                selectPanel(2);
            }
            
            if(cmd == CmdCode.CMD_4)
            {
               selectPanel(3);
            }
            
            if(cmd == CmdCode.CMD_UP){
                selectPanel(--index);
            }
            
            if(cmd == CmdCode.CMD_DOWN){
                selectPanel(++index);
            }
            
            if(cmd == CmdCode.CMD_ENTER)
            {
                if(index == 0) Manager.SetCurPanel(new Panel_edit(Panel.PANEL_1x1));
                if(index == 1) Manager.SetCurPanel(new Panel_edit(Panel.PANEL_1x2));   
                if(index == 2) Manager.SetCurPanel(new Panel_edit(Panel.PANEL_2x2));   
                if(index == 3) Manager.SetCurPanel(new Panel_edit(Panel.PANEL_3x3));   
            }

            if(cmd == CmdCode.CMD_ESC)
                Manager.SetCurPanel(new Panel_Set_ModesList());
        }
        
            
        
    }    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        myLableSmall1 = new OpenCV_cam.Panels.Components.MyLableSmall();
        jPanel8 = new javax.swing.JPanel();
        myJLableBig1 = new OpenCV_cam.Panels.Components.MyJLableBig();
        jPanel1 = new javax.swing.JPanel();
        panel1x1 = new javax.swing.JPanel();
        ImageIcon icon = new ImageIcon("pictures/model1.png");
        jLabel2 = new javax.swing.JLabel(icon, JLabel.CENTER);
        p1x1 = new OpenCV_cam.Panels.Components.MyJLable();
        panel1x2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        p1x2 = new OpenCV_cam.Panels.Components.MyJLable();
        panel2x2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        p2x2 = new OpenCV_cam.Panels.Components.MyJLable();
        panel3x3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        p3x3 = new OpenCV_cam.Panels.Components.MyJLable();
        jPanel6 = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setLayout(new java.awt.BorderLayout());

        jPanel9.setLayout(new java.awt.GridLayout(1, 0));

        myLableSmall1.setText("(1-4) Выбор настройки |  ENTER - Выбрать");
        myLableSmall1.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jPanel9.add(myLableSmall1);

        add(jPanel9, java.awt.BorderLayout.PAGE_END);

        jPanel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 20, 1));
        jPanel8.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jPanel8.setLayout(new java.awt.GridLayout(1, 0, 5, 5));

        myJLableBig1.setText("Добавление режима просмотра");
        myJLableBig1.setToolTipText("");
        myJLableBig1.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jPanel8.add(myJLableBig1);

        add(jPanel8, java.awt.BorderLayout.PAGE_START);

        jPanel1.setLayout(new java.awt.GridLayout(6, 2, 0, 5));

        panel1x1.setLayout(new javax.swing.BoxLayout(panel1x1, javax.swing.BoxLayout.LINE_AXIS));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/modes/mode1x1.png"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 30));
        panel1x1.add(jLabel2);

        p1x1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p1x1.setText("1. Режим 1x1");
        p1x1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        panel1x1.add(p1x1);

        jPanel1.add(panel1x1);

        panel1x2.setLayout(new javax.swing.BoxLayout(panel1x2, javax.swing.BoxLayout.LINE_AXIS));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/modes/mode1x2.png"))); // NOI18N
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 30));
        panel1x2.add(jLabel3);

        p1x2.setText("2. Режим 1x2");
        p1x2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        panel1x2.add(p1x2);

        jPanel1.add(panel1x2);

        panel2x2.setLayout(new javax.swing.BoxLayout(panel2x2, javax.swing.BoxLayout.LINE_AXIS));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/modes/mode2x2.png"))); // NOI18N
        jLabel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 30));
        panel2x2.add(jLabel4);

        p2x2.setText("3. Режим 2x2");
        p2x2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        panel2x2.add(p2x2);

        jPanel1.add(panel2x2);

        panel3x3.setLayout(new javax.swing.BoxLayout(panel3x3, javax.swing.BoxLayout.LINE_AXIS));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/modes/mode3x3.png"))); // NOI18N
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 30));
        panel3x3.add(jLabel5);

        p3x3.setText("4. Режим 3x3");
        p3x3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        panel3x3.add(p3x3);

        jPanel1.add(panel3x3);

        jPanel6.setLayout(new java.awt.GridLayout(1, 0));
        jPanel1.add(jPanel6);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private OpenCV_cam.Panels.Components.MyJLableBig myJLableBig1;
    private OpenCV_cam.Panels.Components.MyLableSmall myLableSmall1;
    private OpenCV_cam.Panels.Components.MyJLable p1x1;
    private OpenCV_cam.Panels.Components.MyJLable p1x2;
    private OpenCV_cam.Panels.Components.MyJLable p2x2;
    private OpenCV_cam.Panels.Components.MyJLable p3x3;
    private javax.swing.JPanel panel1x1;
    private javax.swing.JPanel panel1x2;
    private javax.swing.JPanel panel2x2;
    private javax.swing.JPanel panel3x3;
    // End of variables declaration//GEN-END:variables
    public static Border blackline;

    
    
    @Override
    public MyUIComponent[] GetDataContainers() {
        return new MyUIComponent[]{
            
        };
    }
}
