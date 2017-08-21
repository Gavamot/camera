package SqlLite;

import SqlLite.DadaBase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


public class Vendor implements IdbUDI {

    private static final String TABLE = "vendor";

    public int id;
    public String name;
    public String con_str_rule;
    public String snap_str_rule;

    public Vendor(){}

    public Vendor(HashMap<String, String> obj){
        id = Integer.parseInt(obj.get("id"));
        name = obj.get("name");
        con_str_rule = obj.get("con_str_rule");
        snap_str_rule = obj.get("snap_str_rule");
    }
    
    public ArrayList<Model> getModels(){
        return Model.getByVendorId(id);
    }

    public static ArrayList<Vendor> getAll() {
        ArrayList<Vendor> res = new ArrayList<Vendor>();
        try 
        {
            ArrayList<HashMap<String, String>> items = DadaBase.getAll(TABLE);
            for(HashMap<String, String> obj : items)
            res.add(new Vendor(obj));
        } 
        catch (Exception e) {  }
        return res;
    }

    public static Vendor getById(int id) throws SQLException, ClassNotFoundException {
        HashMap<String, String> obj = DadaBase.getById(id, TABLE);
        Vendor res = new Vendor(obj);
        return res;
    }

    @Override
    public void insert() throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO " + TABLE + "(name, con_str_rule) VALUES(:1,:2)";
        ArrayList<Param> params = new ArrayList();
        params.add(new Param(name));
        params.add(new Param(con_str_rule));
        DadaBase.execute(sql, params);
    }

    @Override
    public void update() throws SQLException, ClassNotFoundException {
        String sql = "UPDATE " + TABLE +" SET  name = :1, con_str_rule = :2 WHERE id = :3";
        ArrayList<Param> params = new ArrayList();
        params.add(new Param(name));
        params.add(new Param(con_str_rule));
        params.add(new Param(id));
        DadaBase.execute(sql, params);
    }
    
    @Override
    public void delete(){

    }

    @Override
    public String toString(){
        return name;
    }
    
    @Override
    public boolean equals(Object other){
        Vendor ov = (Vendor)other; 
        if (ov == null) return false;
        return ov.id == this.id;
    }

}
