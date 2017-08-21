package SqlLite;


import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Model {

    private static final String TABLE = "model";

    public static ArrayList<Model> getByVendorId(int id) {
        
        ArrayList<Model> res = new ArrayList<>();
        try
        {
            String sql = "SELECT * FROM " + TABLE + " WHERE vendor_id=:1";
            ArrayList<Param> params = new ArrayList();
            params.add(new Param(id));
            ArrayList<HashMap<String, String>> items = DadaBase.readRows(sql, params);
            for(HashMap<String, String> item : items)
                res.add(new Model(item));
        }catch(Exception e){}

        return res;
    }

    public int id;
    public String name;
    private String con_str_rule;
    public int vendor_id;
    private String snap_str_rule;

    public String get_con_str_rule(){
        if(con_str_rule != null && !con_str_rule.isEmpty())
            return con_str_rule;
        return vendor.con_str_rule;
    }

    public void set_con_str_rule(String value){
        con_str_rule = value;
    }

    public String get_snap_str_rule(){
        if(snap_str_rule != null && !snap_str_rule.isEmpty())
            return snap_str_rule;
        return vendor.snap_str_rule;
    }

    public void set_snap_str_rule(String value){
        snap_str_rule = value;
    }
    
    

    public Vendor vendor;

    public Model(){}

    public Model(HashMap<String, String> obj) throws SQLException, ClassNotFoundException {
        id = Integer.parseInt(obj.get("id"));
        name = obj.get("name");
        con_str_rule = obj.get("con_str_rule");
        vendor_id = Integer.parseInt(obj.get("vendor_id"));
        vendor = Vendor.getById(vendor_id);
        snap_str_rule = obj.get("snap_str_rule");
    }

    public static ArrayList<Model> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<HashMap<String, String>> items = DadaBase.getAll(TABLE);
        ArrayList<Model> res = new ArrayList();
        for(HashMap<String, String> obj : items)
            res.add(new Model(obj));
        return res;
    }

    public static Model getById(int id) throws SQLException, ClassNotFoundException {
        HashMap<String, String> obj = DadaBase.getById(id, TABLE);
        Model res = new Model(obj);
        return res;
    }

    @Override
    public String toString(){
        return name;
    }
    
    @Override
    public boolean equals(Object other){
        Model ov = (Model)other; 
        if (ov == null) return false;
        return ov.id == this.id;
    }

}
