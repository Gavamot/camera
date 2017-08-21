package OpenCV_cam.Panels.Components;

import OpenCV_cam.Panels.MyUIComponent;
import OpenCV_cam.Panels.SettingsPanel;
import java.text.ParseException;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class MyTextField_INT extends MyTextF{
    public MyTextField_INT(SettingsPanel panel, int countInt){
      
      super(panel);
        
      MaskFormatter maskFormatter = null;
      try {
          String maskStr = "";
          while (countInt-- > 0) maskStr += "#";
         maskFormatter = new MaskFormatter(maskStr);
      } catch (ParseException e) {
         e.printStackTrace();
      }
  
      maskFormatter.setPlaceholderCharacter('_');
      setFormatterFactory(new DefaultFormatterFactory(maskFormatter));
      
    }
}
