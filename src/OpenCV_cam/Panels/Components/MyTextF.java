package OpenCV_cam.Panels.Components;

import OpenCV_cam.Panels.IAction;
import OpenCV_cam.Panels.MyUIComponent;
import OpenCV_cam.Panels.SettingsPanel;
import OpenCV_cam.Panels.StyleManager;
import java.text.Format;
import javax.swing.JFormattedTextField;
import javax.swing.border.Border;
import javax.swing.text.DefaultFormatter;

public class MyTextF extends JFormattedTextField implements MyUIComponent {

    public static final int FORMAT_IP = 1;
    public static final int FORMAT_DATE = 2;
    public static final int FORMAT_TIME = 3;
    public static final int FORMAT_INT = 4;
   
    public static final int INT_DEF = 3;
    
    public static MyTextF getFild(int type, SettingsPanel panel, int count){
        MyTextF res = getFild(type, panel);
        if(type == FORMAT_INT) res = new MyTextField_INT(panel, count);
        return res;
    }
    
    public static MyTextF getFild(int type, SettingsPanel panel){
        MyTextF res = null;
        if(type == FORMAT_IP)   res = new MyTextFild_IP(panel);
        if(type == FORMAT_DATE) res = new MyTextFild_Date(panel);
        if(type == FORMAT_TIME) res = new MyTextFild_Time(panel);
        return res;
    }
    
    SettingsPanel panel;
    
    public MyTextF(){
        setHorizontalAlignment(javax.swing.JTextField.CENTER);
        //setFont(StyleManager.getMiddleFont());
        defBorder = getBorder();
    }
    
    public MyTextF(SettingsPanel panel){
        this();
        this.panel = panel;
    }
   
    public MyTextF(DefaultFormatter formatter, SettingsPanel panel) {
        super(formatter);
        defBorder = getBorder();
        setHorizontalAlignment(javax.swing.JTextField.CENTER);
       // setFont(StyleManager.getMiddleFont());
        this.panel = panel;
    }
    
    public MyTextF(Format format, SettingsPanel panel){
        super(format);
        defBorder = getBorder();
        this.panel = panel;
    }
    
    private Border defBorder;
    @Override
    public void exec(int cmd) {
        switch(cmd)
        {
            case OpenCV_cam.CmdCode.CMD_LEFT : left(); break;
            case OpenCV_cam.CmdCode.CMD_RIGHT : right(); break;
            case OpenCV_cam.CmdCode.CMD_1 : replaceSelection("1"); break;
            case OpenCV_cam.CmdCode.CMD_2 : replaceSelection("2"); break;
            case OpenCV_cam.CmdCode.CMD_3 : replaceSelection("3"); break;
            case OpenCV_cam.CmdCode.CMD_4 : replaceSelection("4"); break;
            case OpenCV_cam.CmdCode.CMD_5 : replaceSelection("5"); break;
            case OpenCV_cam.CmdCode.CMD_6 : replaceSelection("6"); break;
            case OpenCV_cam.CmdCode.CMD_7 : replaceSelection("7"); break;
            case OpenCV_cam.CmdCode.CMD_8 : replaceSelection("8"); break;
            case OpenCV_cam.CmdCode.CMD_9 : replaceSelection("9"); break;
            case OpenCV_cam.CmdCode.CMD_0 : replaceSelection("0"); break;
            case OpenCV_cam.CmdCode.CMD_POINT : replaceSelection("."); break;
            case OpenCV_cam.CmdCode.CMD_C : {
                String txt = getText();
                int len = txt.length();
                if(len > 0){
                     txt = txt.substring(0,getText().length()-1);
                }
                setText(txt);
                break;
            }
            case OpenCV_cam.CmdCode.CMD_ENTER : panel.KeybordEvent(OpenCV_cam.CmdCode.CMD_ESC);break;
        }
    }
    
    private void left() {
        int pos = this.getCaretPosition();
        if(pos > 0)
            this.setCaretPosition(--pos); 
    }

    private void right() {
        int pos = this.getCaretPosition();
        int len = getText().length();
        if(pos < len)
            this.setCaretPosition(++pos);  
    }

    @Override
    public void active() {
        defBorder = this.getBorder();
        this.setBorder(StyleManager.getBorderBlue());
    }

    @Override
    public void disActive() {
        this.setBorder(defBorder);
    }

    void setText(int name) {
        super.setText(Integer.toString(name));
    }
    
    @Override
    public void setFocus() {
        requestFocus();
    }
}
