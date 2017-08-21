package SqlLite;

import static SqlLite.Panel.PANEL_1x1;
import static SqlLite.Panel.PANEL_1x2;
import static SqlLite.Panel.PANEL_2x2;
import static SqlLite.Panel.PANEL_3x3;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PanelTypeInfo {
    
    
    private int type;
    private ImageIcon[] icons;
    public PanelTypeInfo(int type)
    {
        this.type = type;
        this.icons = getImgagesList();
    }
    
     public String getTypeName() {
        switch (type){
            case PANEL_1x1: return "1x1";
            case PANEL_1x2: return "1x2";
            case PANEL_2x2: return "2x2";
            case PANEL_3x3: return "3x3";
            default: return "-";
        }
    }
    
    public String getHeader(){
        return "Настройка режима " + getTypeName();
    }
    
    public String getPrompt(){
        String res = "Выбор настройки (0-";
        switch (type){
            case PANEL_1x1: return res + "1) | ESC - Выход  ";
            case PANEL_1x2: return res + "3) | ESC - Выход  ";
            case PANEL_2x2: return res + "4) | ESC - Выход  ";
            case PANEL_3x3: return res + "9) | ESC - Выход  ";
            default: return "-";
        }
    }
    
     public JLabel getImgLbl(int pos){
        JLabel res = new javax.swing.JLabel();
        res.setFont(new java.awt.Font("Dialog", 0, 18));
        res.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        res.setIcon(icons[pos]);
        res.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        res.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 30));
        return res;
    }
    
    public ImageIcon[] getImgagesList(){
        switch(type){
            case PANEL_1x1 : return new ImageIcon[]{
                new ImageIcon(getClass().getResource("/pictures/modes/1.1.png"))
            };
            case PANEL_1x2 : return new ImageIcon[]{
                new ImageIcon(getClass().getResource("/pictures/modes/2.1.1.png")),
                new ImageIcon(getClass().getResource("/pictures/modes/2.1.2.png")),
                new ImageIcon(getClass().getResource("/pictures/modes/2.1.3.png"))
            };
            case PANEL_2x2 : return new ImageIcon[]{
                new ImageIcon(getClass().getResource("/pictures/modes/2.1.png")),
                new ImageIcon(getClass().getResource("/pictures/modes/2.2.png")),
                new ImageIcon(getClass().getResource("/pictures/modes/2.3.png")),
                new ImageIcon(getClass().getResource("/pictures/modes/2.4.png"))
            };
            case PANEL_3x3 : return new ImageIcon[]{
                new ImageIcon(getClass().getResource("/pictures/modes/9.1.png")),
                new ImageIcon(getClass().getResource("/pictures/modes/9.2.png")),
                new ImageIcon(getClass().getResource("/pictures/modes/9.3.png")),
                new ImageIcon(getClass().getResource("/pictures/modes/9.4.png")),
                new ImageIcon(getClass().getResource("/pictures/modes/9.5.png")),
                new ImageIcon(getClass().getResource("/pictures/modes/9.6.png")),
                new ImageIcon(getClass().getResource("/pictures/modes/9.7.png")),
                new ImageIcon(getClass().getResource("/pictures/modes/9.8.png")),
                new ImageIcon(getClass().getResource("/pictures/modes/9.9.png")),
            };  
        }
        return null;
    }
}
