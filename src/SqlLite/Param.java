package SqlLite;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by OEO on 03.12.2016.
 */
public class Param {

    private int type;
    private int intVal;
    private String stringVal;

    public Param(String val){
        stringVal = val;
        type = 2;
    }

    public Param(int val){
        intVal = val;
        type = 1;
    }

    public void set(PreparedStatement q, int i) throws SQLException {
        if(type == 1) q.setInt(i + 1, intVal);
        if(type == 2) q.setString(i + 1, stringVal);
    }
}
