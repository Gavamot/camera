package OpenCV_cam.Panels;

import javax.swing.JPanel;

public abstract class AbstractPanel extends JPanel{
    
    public abstract void KeybordEvent(int cmd);

    public void exit() {
        this.setVisible(false);
    }
}
