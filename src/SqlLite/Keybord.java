package SqlLite;

import SqlLite.DadaBase;
import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


public class Keybord{

    private static final String TABLE = "keybord";

    public Keybord(){}
    public Keybord(HashMap<String, String> obj){
        port = obj.get("port");
        polling = Integer.parseInt(obj.get("polling"));
        clicklag = Integer.parseInt(obj.get("clicklag"));
    }

    public String port;
    public int polling;
    public int clicklag;

    public static Keybord get() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM keybord";
        HashMap<String, String> obj = DadaBase.readOneRow(sql);
        Keybord res = new Keybord(obj);
        return res;
    }

    public void update() throws SQLException, ClassNotFoundException {
        String sql = "UPDATE keybord SET port = :1, polling = :2, clicklag = :3";
        ArrayList<Param> params = new ArrayList();
        params.add(new Param(port));
        params.add(new Param(polling));
        params.add(new Param(clicklag));
        DadaBase.execute(sql, params);
    }

}
