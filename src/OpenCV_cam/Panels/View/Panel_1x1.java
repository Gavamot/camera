package OpenCV_cam.Panels.View;

import OpenCV_cam.Panels.ViewPanel;
import SqlLite.Content;
import SqlLite.Panel;
import java.util.ArrayList;
import javax.swing.JLabel;

public class Panel_1x1 extends ViewPanel {

    
    public Panel_1x1(ArrayList<Content> content) {
        this.content = content;
        initComponents(); 
        type = Panel.PANEL_1x1;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Label_Data = new javax.swing.JLabel();

        setBackground(new java.awt.Color(73, 64, 54));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setAlignmentX(0.0F);
        setLayout(new java.awt.BorderLayout());

        Label_Data.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(Label_Data, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Label_Data;
    // End of variables declaration//GEN-END:variables


    @Override
    public JLabel[] GetDataContainers() {
        JLabel[] res = new JLabel[1];
        res[0] = Label_Data;
        return res;
    }
}
