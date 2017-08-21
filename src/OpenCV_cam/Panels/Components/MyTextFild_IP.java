package OpenCV_cam.Panels.Components;


import OpenCV_cam.Panels.SettingsPanel;

public class MyTextFild_IP extends MyTextF {
    public MyTextFild_IP(SettingsPanel panel) {
        super(new IPAddressFormatter(), panel);
        this.panel = panel;
        setHorizontalAlignment(javax.swing.JTextField.CENTER);
    }
}
