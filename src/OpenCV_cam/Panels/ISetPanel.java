package OpenCV_cam.Panels;

import OpenCV_cam.Panels.Components.Panel_element;

public interface ISetPanel {
    public Panel_element[] getPanelElement();
    public void initSetPanel();
    public void resetSelectionPanel();
}
