package OpenCV_cam.Panels.View;

import OpenCV_cam.Panels.ViewPanel;
import SqlLite.Content;
import SqlLite.Panel;
import java.util.ArrayList;
import javax.swing.JLabel;

public class Panel_1x2 extends ViewPanel {

    
    public Panel_1x2(ArrayList<Content> content) {
        this.content = content;
        initComponents(); 
        type = Panel.PANEL_1x2;
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(73, 64, 54));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new java.awt.GridLayout(2, 1, 5, 5));

        jPanel2.setLayout(new java.awt.BorderLayout(5, 5));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jLabel1, java.awt.BorderLayout.CENTER);

        add(jPanel2);

        jPanel3.setBackground(new java.awt.Color(73, 64, 54));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 5, 5));

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel2, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel4);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel5.add(jLabel3, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel5);

        add(jPanel3);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables


    @Override
    public JLabel[] GetDataContainers() {
        JLabel[] res = new JLabel[3];
        res[0] = jLabel1;
        res[1] = jLabel2;
        res[2] = jLabel3;
        return res;
    }
}
