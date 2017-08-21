package SqlLite;

import OpenCV_cam.Channel;
import OpenCV_cam.Panels.StyleManager;
import SqlLite.DadaBase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Content implements IdbUDI {

    private static final String TABLE = "content";

    public int id;
    public int position;
    public int cof_width;
    public int cof_height;
    public int camera_id;
    public int channel_id;
    public int panel_id;

    public Channel channel;
    public CameraSettings camera;

    
    public int[] getScreenSize(int n)
    {
        int w = (int) (StyleManager.getScreenWidth() / Math.ceil(Math.sqrt(n)) * cof_width);
        int h = (int) (StyleManager.getScreenHeight() / Math.ceil(Math.sqrt(n)) * cof_height);
        return new int[] { w , h };
    }
    
    public Panel getpanel() throws SQLException, ClassNotFoundException {
        return Panel.getById(panel_id);
    }

    public Content(){
        cof_height = 1;
        cof_width = 1;
    }
    
    public Content(HashMap<String, String> obj) throws SQLException, ClassNotFoundException {
        
        if(obj.isEmpty()) {
            return ;
        }
        
        id = Integer.parseInt(obj.get("id"));
        position = Integer.parseInt(obj.get("position"));
        cof_width = Integer.parseInt(obj.get("cof_width"));
        cof_height = Integer.parseInt(obj.get("cof_height"));
        
        String camera_id_str = obj.get("camera_id");
        if(camera_id_str != null) camera_id = Integer.parseInt(camera_id_str);

        String channel_id_str = obj.get("channel_id");
        if(channel_id_str != null)  channel_id =Integer.parseInt(channel_id_str);
        
        panel_id =Integer.parseInt(obj.get("panel_id"));

        if(camera_id > 0)  camera  = CameraSettings.getById(camera_id);
        if(channel_id > 0) channel = Channel.getById(channel_id);

    }

    public static ArrayList<Content> getByPanelId(int panel_id)throws SQLException, ClassNotFoundException
    {
        String sql = "SELECT * FROM " + TABLE + " WHERE panel_id=:1";
        ArrayList<Param> params = new ArrayList<>();
        params.add(new Param(panel_id));

        ArrayList<Content> res = new ArrayList<>();
        for (HashMap<String, String> p :  DadaBase.readRows(sql, params)){
            res.add(new Content(p));
        }
        return res;

    }
    
    public static void deleteByPanelId(int id) throws ClassNotFoundException, SQLException{
        String sql = "DELETE FROM " + TABLE +  " WHERE panel_id = ?";
        ArrayList<Param> params = new ArrayList();
        params.add(new Param(id));
        DadaBase.execute(sql, params);
    }

    public static ArrayList<Content> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<HashMap<String, String>> items = DadaBase.getAll(TABLE);
        ArrayList<Content> res = new ArrayList();
        for(HashMap<String, String> obj : items)
            res.add(new Content(obj));
        return res;
    }

    public static Content getById(int id) throws SQLException, ClassNotFoundException {
        HashMap<String, String> obj = DadaBase.getById(id, TABLE);
        Content res = new Content(obj);
        return res;
    }

    @Override
    public void insert() throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO " + TABLE + "(position, cof_width, cof_height, camera_id, channel_id, panel_id) VALUES(:1,:2,:3,:4,:5,:6)";
        ArrayList<Param> params = new ArrayList();
        params.add(new Param(position));
        params.add(new Param(cof_width));
        params.add(new Param(cof_height));
        params.add(new Param(camera_id));
        params.add(new Param(channel_id));
        params.add(new Param(panel_id));
        DadaBase.execute(sql, params);
    }

    @Override
    public void update() throws SQLException, ClassNotFoundException {
        String sql = "UPDATE " + TABLE +" SET position = :1, cof_width = :2, cof_height = :3, camera_id = :4, channel_id = :5, panel_id:6 WHERE id = :7";
        ArrayList<Param> params = new ArrayList();
        params.add(new Param(position));
        params.add(new Param(cof_width));
        params.add(new Param(cof_height));
        params.add(new Param(camera_id));
        params.add(new Param(channel_id));
        params.add(new Param(panel_id));
        params.add(new Param(id));
        DadaBase.execute(sql, params);
    }

    @Override
    public void delete() throws SQLException, ClassNotFoundException{
        String sql = "DELETE FROM " + TABLE +" WHERE id = :1";
        ArrayList<Param> params = new ArrayList();
        params.add(new Param(id));
        DadaBase.execute(sql, params);
    }

    @Override
    public String toString(){
        if(camera_id > 0)
            return "контент " + camera.name + "(" + camera.ip + ")";
        else
            return "контент " + channel.name + "(" + channel.id + ")" ;
    }


}
