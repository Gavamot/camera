package SqlLite;
import OpenCV_cam.Panels.View.Panel_3x3;
import OpenCV_cam.Panels.View.Panel_1x2;
import OpenCV_cam.Panels.View.Panel_1x1;
import OpenCV_cam.Panels.View.Panel_2x2;
import OpenCV_cam.Panels.Components.Panel_element;
import OpenCV_cam.Panels.*;
import OpenCV_cam.Panels.ViewPanel;
import SqlLite.DadaBase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Panel implements IdbUDI {

    private static final String TABLE = "panel";

    public static final int PANEL_1x1 = 1;
    public static final int PANEL_1x2 = 3;
    public static final int PANEL_2x2 = 4;
    public static final int PANEL_3x3 = 9;
    
    public int id;
    public int type;
    public String name;
    
    public static void insert(int type, Panel_element[] elements) throws Exception
    {
        Panel p = new Panel(type);
        p.insert();
        int panel_id = DadaBase.getMaxID(TABLE);
        
        for(Panel_element elem : elements){
            Content content = elem.getContent(panel_id);
            content.insert();
        }
    } 
    
    public static void update(Panel p, Panel_element[] elements) throws Exception
    {
        p.update();
      
         for(Content elem : p.contents){
            elem.delete();
        }
        
        for(Panel_element elem : elements){
            Content content = elem.getContent(p.id);
            content.insert();
        }
    } 
    
    
    @Override
    public String toString(){
        return "сетка " + name + " - режим " + id;
    }
    
    
    public static Panel getPanelByJListValue(String str){
        String[] tmp = str.split(" ");
        try{
            int val = Integer.parseInt(tmp[tmp.length - 1]);
            return getById(val);
        }catch(Exception e){
            return null;
        }
    }
    
    public ViewPanel getPanelObj() throws Exception{
        switch(type)
        {
            case PANEL_1x1 : return new Panel_1x1(contents);
            case PANEL_1x2 : return new Panel_1x2(contents);
            case PANEL_2x2 : return new Panel_2x2(contents);
            case PANEL_3x3 : return new Panel_3x3(contents);
            default: throw new Exception("Unknow panel type");
        } 
    }
    

    public ArrayList<Content> contents = new ArrayList<>();

    
 
    public PanelTypeInfo typeInfo;
    
    public Panel(int type){
        this.type = type;
        typeInfo = new PanelTypeInfo(type);
        name = typeInfo.getTypeName();
    }
    
    public Panel(HashMap<String, String> obj) throws SQLException, ClassNotFoundException
    {
        id = Integer.parseInt(obj.get("id"));
        name = obj.get("name");
        type = Integer.parseInt(obj.get("type"));
        contents = Content.getByPanelId(id);
        typeInfo = new PanelTypeInfo(type);
    }

    public static ArrayList<Panel> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<HashMap<String, String>> items = DadaBase.getAll(TABLE);
        ArrayList<Panel> res = new ArrayList();
        for(HashMap<String, String> obj : items){
            Panel p = new Panel(obj);
            res.add(p);
        }
        return res;
    }

    public static Panel getById(int id) throws SQLException, ClassNotFoundException {
        HashMap<String, String> obj = DadaBase.getById(id, TABLE);
        Panel res = new Panel(obj);
        return res;
    }

    @Override
    public void insert() throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO " + TABLE + "(type, name) VALUES(:1,:2)";
        ArrayList<Param> params = new ArrayList();
        params.add(new Param(type));
        params.add(new Param(name));
        DadaBase.execute(sql, params);
    }

    @Override
    public void update() throws SQLException, ClassNotFoundException {
        String sql = "UPDATE " + TABLE +" SET type = :1, name = :2 WHERE id = :3";
        ArrayList<Param> params = new ArrayList();
        params.add(new Param(type));
        params.add(new Param(name));
        params.add(new Param(id));
        DadaBase.execute(sql, params);
    }

    @Override
    public void delete(){
        try{
            Content.deleteByPanelId(id);
            String sql = "DELETE FROM " + TABLE +" WHERE id = :1";
            ArrayList<Param> params = new ArrayList();
            params.add(new Param(id));
            DadaBase.execute(sql, params);
        }catch(Exception e){}
    }
}
