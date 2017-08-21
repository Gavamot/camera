package SqlLite;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class CameraSettings implements IdbUDI {

    private static final String TABLE = "camera";

    public CameraSettings(){}
    public CameraSettings(HashMap<String, String> obj) throws SQLException, ClassNotFoundException {
        
      
        id = Integer.parseInt(obj.get("id"));
        name = obj.get("name");
        
        if(id == 1) return;
        ip = obj.get("ip");
        th = obj.get("th");
        model_id = Integer.parseInt(obj.get("model_id"));
        model = Model.getById(model_id);
    }

    public static final String IP_PATTRN = "\\{ip\\}";
    public static final String TH_PATTRN = "\\{th\\}";

    public int id;
    public String ip;
    public String th;
    public String name;
    public int model_id;
    public Model model;

    public String get_con_str_rule()
    {
        String res = model.get_con_str_rule();
        res = res.replaceAll(IP_PATTRN, this.ip);
        res = res.replaceAll(TH_PATTRN, this.th);
        return res;
    }
    
     public String get_snap_str_rule(){
    
        String res = model.get_snap_str_rule();
        res = res.replaceAll(IP_PATTRN, this.ip);
        res = res.replaceAll(TH_PATTRN, this.th);
        return res;
    }
     
    public static CameraSettings getPanelByJListValue(String str){
        String[] tmp = str.split(" ");
        try{
            String val = tmp[1];
            return getByName(val);
        }catch(Exception e){
            return null;
        }
    }
     
    public static CameraSettings getByName(String name) throws SQLException, ClassNotFoundException {
        ArrayList<Param> params = new ArrayList();
        params.add(new Param(name));
        String sql = "Select * From " + TABLE + " Where name=:1";
        HashMap<String, String> obj = DadaBase.readOneRow(sql, params);
        if(obj.isEmpty()) return null;
        CameraSettings res = new CameraSettings(obj);
        return res;
    }
     
     
    public static ArrayList<CameraSettings> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<HashMap<String, String>> items = DadaBase.getAll(TABLE);
        ArrayList<CameraSettings> res = new ArrayList();
        for(HashMap<String, String> obj : items)
            res.add(new CameraSettings(obj));
        return res;
    }

    public static CameraSettings getById(int id) throws SQLException, ClassNotFoundException {
        HashMap<String, String> obj = DadaBase.getById(id, TABLE);
        if(obj.isEmpty()) return null;
        CameraSettings res = new CameraSettings(obj);
        return res;
    }

    @Override
    public void insert() throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO " + TABLE + "(name, ip, model_id, th) VALUES(:1,:2,:3,:4)";
        ArrayList<Param> params = new ArrayList();
        params.add(new Param(name));
        params.add(new Param(ip));
        params.add(new Param(model_id));
        params.add(new Param(th));
        DadaBase.execute(sql, params);
    }

    @Override
    public void update() throws SQLException, ClassNotFoundException {
        String sql = "UPDATE " + TABLE +" SET name = :1, ip = :2, model_id = :3, th = :4 WHERE id = :5";
        ArrayList<Param> params = new ArrayList();
        params.add(new Param(name));
        params.add(new Param(ip));
        params.add(new Param(model_id));
        params.add(new Param(th));
        params.add(new Param(id));
        DadaBase.execute(sql, params);
    }

    @Override
    public void delete(){
        if(id == 1) return;
        String sql = "DELETE FROM " + TABLE +" WHERE id = :1";
        ArrayList<Param> params = new ArrayList();
        params.add(new Param(id));
        try{
            DadaBase.execute(sql, params);
        }catch(Exception e){}
    }

    @Override
    public String toString(){
        String res = "Камера " + this.name;
        if(ip != null){
           res += " (" + this.ip + ")";
        }
        return res;
    }



}
