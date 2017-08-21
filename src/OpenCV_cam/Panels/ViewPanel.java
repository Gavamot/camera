package OpenCV_cam.Panels;

import OpenCV_cam.ThreadChannel;
import SqlLite.Content;
import java.util.ArrayList;
import javax.swing.JLabel;
import OpenCV_cam.CmdCode;
import OpenCV_cam.Manager;
import OpenCV_cam.ThreadCam;



public abstract class ViewPanel extends AbstractPanel{
    
    public abstract JLabel[] GetDataContainers();

    public int type;
    
    @Override
    public void KeybordEvent(int cmd) {
        if(cmd == CmdCode.CMD_LEFT)
            Manager.toPrevPanel();
        if(cmd == CmdCode.CMD_RIGHT)
            Manager.toNextPanel();
        if(cmd == CmdCode.CMD_1)
            Manager.fullScreenMode(0);
        if(cmd == CmdCode.CMD_2)
            Manager.fullScreenMode(1);
        if(cmd == CmdCode.CMD_3)
           Manager.fullScreenMode(2);
        if(cmd == CmdCode.CMD_4)
           Manager.fullScreenMode(3);
        if(cmd == CmdCode.CMD_5)
           Manager.fullScreenMode(4);
        if(cmd == CmdCode.CMD_6)
           Manager.fullScreenMode(5);
        if(cmd == CmdCode.CMD_7)
           Manager.fullScreenMode(6);
        if(cmd == CmdCode.CMD_8)
           Manager.fullScreenMode(7);
        if(cmd == CmdCode.CMD_9)
           Manager.fullScreenMode(8);
        if(cmd == CmdCode.CMD_MENU)
           Manager.toMenu();
        if(cmd == CmdCode.CMD_ESC)
           Manager.exitAction();
    }
    
    public ArrayList<ThreadCam> threadCams = new ArrayList<>();
    public ArrayList<ThreadChannel> threadChannels = new ArrayList<>();
    public ArrayList<Content> content = new ArrayList<>();
   
}
