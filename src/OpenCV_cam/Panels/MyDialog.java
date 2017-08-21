package OpenCV_cam.Panels;

import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyDialog extends JDialog {
   
   private SettingsPanel parent;

   private JButton btnYes = new JButton("Да (ENTER)");
   private JButton btnNo = new JButton("Нет (ESC)");
   
   public MyDialog(JFrame frame, String title, SettingsPanel parent) {
       
      super(frame, title, false);
      
      this.parent = parent;
      
      JPanel panel = new JPanel();
      panel.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
      panel.add(btnYes);
      panel.add(btnNo);

      add(panel);
      pack();
      setLocationRelativeTo(frame);
      
      btnYes.addActionListener((e) -> {
          parent.dialog = null;
          dispose();
      });
      btnNo.addActionListener((e) -> {
          parent.dialog = null;
          dispose();
      });
      
      this.addComponentListener(new ComponentListener() {
        public void componentHidden(ComponentEvent e)
        { 
            parent.dialog = null;
            dispose();
        }

        public void componentMoved(ComponentEvent e)
        {
          
        }

        public void componentResized(ComponentEvent e)
        {
          
        }

        public void componentShown(ComponentEvent e)
        {
         
        }
      });

   }
  
   public void enter(){
       btnYes.doClick();
   }
   
   public void esc(){
       btnNo.doClick();
   }
   
   public void addYesListener(ActionListener listener) {
       if(listener != null)
           btnYes.addActionListener(listener);
   }
   
   public void addNoListener(ActionListener listener) {
       if(listener != null)
           btnNo.addActionListener(listener);
   }
}