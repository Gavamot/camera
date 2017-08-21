package OpenCV_cam.Panels.Settings;

import OpenCV_cam.CmdCode;
import OpenCV_cam.Manager;
import OpenCV_cam.Panels.Components.Panel_element;
import OpenCV_cam.Panels.MyUIComponent;
import OpenCV_cam.Panels.SettingsPanel;
import OpenCV_cam.Panels.StyleManager;
import SqlLite.Content;
import SqlLite.Panel;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel_edit extends SettingsPanel {
    
    private JPanel[] itemPanels;
    private Panel_element[] items; 
    
    public Panel panel;
    private String promptString;
    public Panel_edit(int type){
        panel = new Panel(type);
        init(panel);
    }
    
    public Panel_edit(Panel panel) {
        init(panel);
    }
    
    private void init(Panel panel){
        
        initComponents();
        
        String header = panel.typeInfo.getHeader();
        this.lblHeader.setText(header);
        
        promptString =  panel.typeInfo.getPrompt();
        
        this.lblPrompt.setText(promptString);
        
        this.panel = panel;
        itemPanels = new JPanel[]{
            panel_0,
            panel_1,
            panel_2,
            panel_3,
            panel_4,
            panel_5,
            panel_6,
            panel_7,
            panel_8,
        };
        initSetPanel(panel);
    }
    
    int focusIndex = 0;
   
    private void hideBorders(){
        for(JPanel p : itemPanels){
            p.setBorder(null);
        }
    }
    
    private void setSelected(int index){
        hideBorders();
        if(index < 0) index = 0;
        int max = items.length - 1;
        
        if(index > max){ 
              focusIndex = index;
              btnOk.setFocus();
              return;
        }
        focusIndex = index;
        items[index].setFocus();
        itemPanels[index].setBorder(StyleManager.getBorderBlack());
        
    }
    
    @Override
    public void KeybordEvent(int cmd) {
        if(activeUI == null){
            if(cmd == CmdCode.CMD_0){
                setSelected(9);
                return;
            }
            if(cmd == CmdCode.CMD_1){
                setSelected(0);
                return;
            }
            if(cmd == CmdCode.CMD_2){
                 setSelected(1);
                 return;
            }
            if(cmd == CmdCode.CMD_3){
                 setSelected(2);
                 return;
            }
            if(cmd == CmdCode.CMD_4){
                setSelected(3);
                return;
            }
            if(cmd == CmdCode.CMD_5){
                setSelected(4);
                return;
            }
            if(cmd == CmdCode.CMD_6){
                setSelected(5);
                return;
            }
            if(cmd == CmdCode.CMD_7){
                 setSelected(6);  
                 return;
            }
            if(cmd == CmdCode.CMD_8){
                setSelected(7);
                return;
            }
            if(cmd == CmdCode.CMD_9){
                 setSelected(8);
                 return;
            }

            if(cmd == CmdCode.CMD_UP){
                 setSelected(--focusIndex);
                 return;
            }
            if(cmd == CmdCode.CMD_DOWN){
                 setSelected(++focusIndex);
                 return;
            }
            
            if(cmd == CmdCode.CMD_ENTER){
                 if(btnOk.isFocusOwner())
                {
                    try {
                        this.lblPrompt.setText(promptString);
                        if(panel.id == 0) Panel.insert(panel.type, items);
                        else Panel.update(panel, items);
                        Manager.SetCurPanel(new Panel_Set_ModesList());
                    }catch(Exception e){
                        lblHeader.setText(promptString + " " + e.getMessage());
                    }
                }
            }
        } else{
           
           
            
            
        }
        super.KeybordEvent(cmd);
      
    }    
    
    
    
    
    private void initSetPanel(Panel panel) {
        items = new Panel_element[panel.type];
        
        // Создаем панели  
        for (int i = 0; i < panel.type; i++) {
            JLabel lblImg = panel.typeInfo.getImgLbl(i);
            itemPanels[i].add(lblImg);
            int cw = 1;
            if(panel.type == Panel.PANEL_1x1 && i == 0){
                cw = 2;
            }
            
            
            Content content = new Content();
            if(i <  panel.contents.size()){
               content = panel.contents.get(i);
            }
            
            Panel_element elem = new Panel_element(i, cw, content);
            items[i] = elem;
            itemPanels[i].add(elem);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHeader = new javax.swing.JLabel();
        panelMain = new javax.swing.JPanel();
        panel_0 = new javax.swing.JPanel();
        panel_1 = new javax.swing.JPanel();
        panel_2 = new javax.swing.JPanel();
        panel_3 = new javax.swing.JPanel();
        panel_4 = new javax.swing.JPanel();
        panel_5 = new javax.swing.JPanel();
        panel_6 = new javax.swing.JPanel();
        panel_7 = new javax.swing.JPanel();
        panel_8 = new javax.swing.JPanel();
        btnOk = new OpenCV_cam.Panels.Components.MyButton();
        lblPrompt = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        lblHeader.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        lblHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeader.setText("Header");
        lblHeader.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        add(lblHeader, java.awt.BorderLayout.PAGE_START);

        panelMain.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 20, 5, 20));
        panelMain.setLayout(new java.awt.GridLayout(10, 1));

        panel_0.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        panel_0.setLayout(new javax.swing.BoxLayout(panel_0, javax.swing.BoxLayout.LINE_AXIS));
        panelMain.add(panel_0);

        panel_1.setLayout(new javax.swing.BoxLayout(panel_1, javax.swing.BoxLayout.LINE_AXIS));
        panelMain.add(panel_1);

        panel_2.setLayout(new javax.swing.BoxLayout(panel_2, javax.swing.BoxLayout.LINE_AXIS));
        panelMain.add(panel_2);

        panel_3.setLayout(new javax.swing.BoxLayout(panel_3, javax.swing.BoxLayout.LINE_AXIS));
        panelMain.add(panel_3);

        panel_4.setLayout(new javax.swing.BoxLayout(panel_4, javax.swing.BoxLayout.LINE_AXIS));
        panelMain.add(panel_4);

        panel_5.setLayout(new javax.swing.BoxLayout(panel_5, javax.swing.BoxLayout.LINE_AXIS));
        panelMain.add(panel_5);

        panel_6.setLayout(new javax.swing.BoxLayout(panel_6, javax.swing.BoxLayout.LINE_AXIS));
        panelMain.add(panel_6);

        panel_7.setLayout(new javax.swing.BoxLayout(panel_7, javax.swing.BoxLayout.LINE_AXIS));
        panelMain.add(panel_7);

        panel_8.setLayout(new javax.swing.BoxLayout(panel_8, javax.swing.BoxLayout.LINE_AXIS));
        panelMain.add(panel_8);

        btnOk.setText("0. Сохранить");
        btnOk.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        panelMain.add(btnOk);

        add(panelMain, java.awt.BorderLayout.CENTER);

        lblPrompt.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblPrompt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrompt.setText("prompt");
        lblPrompt.setRequestFocusEnabled(false);
        add(lblPrompt, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public MyUIComponent[] GetDataContainers() {
        MyUIComponent[] res = new MyUIComponent[panel.type];
        for (int i = 0; i < panel.type; i++) {
            for(Component c : itemPanels[i].getComponents()){
                if(c instanceof MyUIComponent)
                {
                    res[i] = (MyUIComponent)c;
                }
            }
            
        }
        return res;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private OpenCV_cam.Panels.Components.MyButton btnOk;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblPrompt;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panel_0;
    private javax.swing.JPanel panel_1;
    private javax.swing.JPanel panel_2;
    private javax.swing.JPanel panel_3;
    private javax.swing.JPanel panel_4;
    private javax.swing.JPanel panel_5;
    private javax.swing.JPanel panel_6;
    private javax.swing.JPanel panel_7;
    private javax.swing.JPanel panel_8;
    // End of variables declaration//GEN-END:variables
}
