package OpenCV_cam;

import OpenCV_cam.Panels.AbstractPanel;
import OpenCV_cam.Panels.NewJFrame;
import OpenCV_cam.Panels.Settings.Panel_Menu;
import OpenCV_cam.Panels.View.Panel_1x1;
import OpenCV_cam.Panels.ViewPanel;
import SqlLite.Content;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Manager {
    
    //static final Logger logger = LogManager.getLogger(Manager.class.getName());
    
    public static JFrame frame;
    public static NewJFrame subFrame;
    public static AbstractPanel curPanel;
    public static ArrayList<ViewPanel> viewsPanels = new ArrayList<>();  
    
    public static Program Program;
    
    public static void restartProgramm(){
        Program.restartProgramm();
    }
    
    public static ChannelWriter getChannelWriter(){
        return Program.channelWriter;
    }
    
    public static void toPrevPanel()
    {
        int index = viewsPanels.indexOf(curPanel);
        if(--index < 0)
            index =  viewsPanels.size() - 1;
        setViewPanel(index);
    }
   
    public static void exitMenu()
    {
        curPanel.exit();
        SetCurPanel(new Panel_Menu());
        //logger.info("cmd=Перешел в меню");
    }
    
    public static void exitSettings()
    {
        curPanel.exit();
        setViewPanel(0);
    }
    
    public static void toNextPanel()
    {
        int index = viewsPanels.indexOf(curPanel);
        if(++index >= viewsPanels.size())
            index = 0;
        setViewPanel(index);
    }
    
    public static void toCustomPanel(int index)
    {
        if(index >= 0 && index < viewsPanels.size())
            setViewPanel(index);
    }
    
    public static void fullScreenMode(int i)
    {
       if(curPanel == null || !(curPanel instanceof ViewPanel) || subFrame!=null ) return;
       
       ViewPanel vp = (ViewPanel)curPanel;
       int contentSize = vp.content.size();
       
       if(contentSize < 2 || i < 0 || i > contentSize - 1) return;
 
        Content content = ((ViewPanel)curPanel).content.get(i);
        
        subFrame = new NewJFrame((ViewPanel) curPanel);
        
        Dimension size = frame.getSize();
        subFrame.setSize(size.width, size.height);
        
        if(content.camera != null){
            subFrame.camId = content.camera.id;
            subFrame.camPosition = i;
            ThreadCam camera = getCameraThread(content.camera.id);
            camera.setLable(subFrame.jLabel1);
        }else{
            ThreadChannel tc = getChannelThread(content.channel_id);
            tc.chartPanel.setSize(size.width, size.height);
            subFrame.camPosition = i;
            subFrame.channelId = tc.id;
            
            subFrame.jPanel1.removeAll();
            tc.panel =  subFrame.jPanel1;
        }

        subFrame.show();
        //logger.info("cmd=Полноэкранный режим для cell="+i);
    }
 
    public static void exitAction()
    {
        if(subFrame == null || !(curPanel instanceof ViewPanel)) return;
        
        JLabel[] lbls = ((ViewPanel)curPanel).GetDataContainers();

        int pos = subFrame.camPosition;
        
        if(subFrame.camId != -1 && pos >= 0 && pos < lbls.length ){
            ThreadCam camera =  getCameraThread(subFrame.camId);
            camera.setLable(lbls[pos]);
        }
 
        if(subFrame.channelId != -1 && pos >= 0 && pos < lbls.length){
            ThreadChannel channel =  getChannelThread(subFrame.channelId);
            channel.panel.setSize(subFrame.Width, subFrame.Height);
            channel.chartPanel.setSize(subFrame.Width, subFrame.Height);
            channel.panel = (JPanel)lbls[pos].getParent();
            
        }
        subFrame.dispose();
        subFrame = null;
        //logger.info("cmd=Выход из полноэкранного режима");
    }
    
    public static void setViewPanel(int i)
    {
        if(subFrame != null) return;
        if(viewsPanels.size() > 0){
            ViewPanel vp = viewsPanels.get(i);
            initPanel(vp);
            SetCurPanel(vp);
            //logger.info("cmd=Перешел на панель №" + i);
        }else{
            ArrayList<Content> content = new ArrayList<>();
            content.add(new Content());
            SetCurPanel(new Panel_1x1(content));
        }
    }
    
    public static void SetCurPanel(AbstractPanel newPanel){
        
        if(null != curPanel){
            frame.remove(curPanel);
        }
        
        curPanel = newPanel;
        frame.add(newPanel);
    }
    
    public static void redraw(){
        if(frame != null){
            frame.repaint(1);
            frame.revalidate();
        }
        if(subFrame != null){
            subFrame.repaint(1);
            subFrame.revalidate();
        }
    }
    
    public static void toMenu(){
        exitAction();
        SetCurPanel(new Panel_Menu());
    }
 
    public static ThreadCam getCameraThread(int id){
        for(ThreadCam c : Program.camersPool)
            if(c.settings.id == id)
                return c;
        return null;
    }
    
    public static ThreadChannel getChannelThread(int id){
        for(ThreadChannel c : Program.channelsPool)
            if(c.id == id)
                return c;
        return null;
    }
    
    private static void initPanel(ViewPanel vp){
        
        JLabel[] lbls = vp.GetDataContainers();
        
        for(int i = 0, max = vp.content.size(); i < max; i++){
            
            Content ctx = vp.content.get(i);
            
            if(ctx.camera_id > 0){
                if(ctx.camera != null){
                    ThreadCam cam = getCameraThread(ctx.camera.id);
                    if(cam != null){
                        cam.setLable(lbls[i]);
                    }else{
                       // logger.error("камера " + ctx.camera.toString() + " c ID=" + ctx.camera.id + " не найдена в пуле потоков камер");
                    }
                }
            }else{
                if(ctx.channel != null){
                    JPanel p = (JPanel)lbls[i].getParent();
                    ThreadChannel tc = getChannelThread(ctx.channel.id);
                    p.add(tc.chartPanel);
                    tc.panel = p;
                    if(!tc.isAlive()){
                        tc.start();
                    }
                }
            }
       }
    }
       
    public static AbstractPanel GetCurPanel() {
        return curPanel;
    }
    
    public static void Signal(int cmd) {
        
        //logger.info("cmd = " + cmd);
        
        try{
            if(cmd == -1){
                //logger.info("Вася вырубил рубильник");
                Program.exit();
            }
            else if(curPanel != null){
                curPanel.KeybordEvent(cmd);
            }
        }catch(Exception e){
            //logger.error("Ошибка при выполнении команды " + cmd + " (" + e.getMessage() + ")");
        }
        
    }   
  
}
