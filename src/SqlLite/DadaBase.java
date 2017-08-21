package SqlLite;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DadaBase  {

    public static Connection conn = null;

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:main.db");
    }

    public static ArrayList<HashMap<String, String>> readRows(String sql, ArrayList<Param> params) throws ClassNotFoundException, SQLException {
        if(conn == null) connect();
        PreparedStatement statmt = getPreparedStatement(sql, params);

        ResultSet resSet = statmt.executeQuery();
        ResultSetMetaData metaData = resSet.getMetaData();
        int count = metaData.getColumnCount();

        ArrayList<HashMap<String, String>> res = new ArrayList();
        while(resSet.next())
        {
            HashMap<String, String> row = new HashMap();
            for (int i = 1; i <= count; i++)
            {
                String key = metaData.getColumnLabel(i);
                String val = resSet.getString(key);
                row.put(key,val);
            }
            res.add(row);
        }

        statmt.close();
        resSet.close();
        return res;
    }

    public static ArrayList<HashMap<String, String>> readRows(String sql) throws ClassNotFoundException, SQLException {
        if(conn == null) connect();
        PreparedStatement statmt = conn.prepareStatement(sql);
        ResultSet resSet = statmt.executeQuery();

        ResultSetMetaData metaData = resSet.getMetaData();
        int count = metaData.getColumnCount();

        ArrayList<HashMap<String, String>> res = new ArrayList();

        while(resSet.next())
        {
            HashMap<String, String> row = new HashMap();
            for (int i = 1; i <= count; i++)
            {
                String key = metaData.getColumnLabel(i);
                String val = resSet.getString(key);
                row.put(key,val);
            }
            res.add(row);
        }

        statmt.close();
        resSet.close();
        return res;
    }

    public static HashMap<String, String> readOneRow(String sql) throws ClassNotFoundException, SQLException {
        if(conn == null) connect();
        PreparedStatement statmt = conn.prepareStatement(sql);
        ResultSet resSet = statmt.executeQuery();

        ResultSetMetaData metaData = resSet.getMetaData();
        int count = metaData.getColumnCount();

       // resSet.next();
        HashMap<String, String> res = new HashMap();
        for (int i = 1; i <= count; i++)
        {
            String key = metaData.getColumnLabel(i);
            String val = resSet.getString(key);
            res.put(key,val);
        }

        statmt.close();
        resSet.close();
        return res;
    }

    public static HashMap<String, String> readOneRow(String sql, ArrayList<Param> params) throws ClassNotFoundException, SQLException {
        if(conn == null) connect();

        PreparedStatement statmt = getPreparedStatement(sql, params);

        ResultSet resSet = statmt.executeQuery();
        ResultSetMetaData metaData = resSet.getMetaData();
        int count = metaData.getColumnCount();

        HashMap<String, String> res = new HashMap();
        if(resSet.next()) {
            for (int i = 1; i <= count; i++) {
                try {
                    String key = metaData.getColumnLabel(i);
                    String val = resSet.getString(key);
                    res.put(key, val);
                } catch (Exception e) {

                    System.err.println(metaData.getColumnLabel(i));
                }
            }
        }

        statmt.close();
        resSet.close();
        return res;
    }

    public static void execute(String sql) throws ClassNotFoundException, SQLException{
        if(conn == null) connect();

        Statement statmt = conn.createStatement();
        statmt.execute(sql);
        statmt.close();
    }

    public static void execute(String sql, ArrayList<Param> params) throws ClassNotFoundException, SQLException{
        if(conn == null) connect();
        PreparedStatement statmt = getPreparedStatement(sql, params);
        statmt.execute();
        statmt.close();
    }

    private static PreparedStatement getPreparedStatement(String sql, ArrayList<Param> params) throws SQLException {
        PreparedStatement statmt = conn.prepareStatement(sql);
        for(int i = 0; i < params.size(); i++){
            params.get(i).set(statmt, i);
        }
        return statmt;
    }

    public static void closeDB() throws ClassNotFoundException, SQLException {
        conn.close();
        System.out.println("Соединения закрыты");
    }


    public static HashMap<String, String> getById(int id, String table) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM " + table + " WHERE id=:1";
        ArrayList<Param> params = new ArrayList();
        params.add(new Param(id));
        return readOneRow(sql, params);
    }

    public static void deleteById(int id, String table) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM " + table + " WHERE id=:1";
        ArrayList<Param> params = new ArrayList();
        params.add(new Param(id));
        execute(sql, params);
    }

    public static ArrayList<HashMap<String, String>> getAll(String table) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM " + table + " ORDER BY id";
        return readRows(sql);
    }
    
    
    public static int getMaxID(String table) throws ClassNotFoundException, SQLException{
        String sql = "SELECT MAX(id) as id FROM " + table;
        HashMap<String, String> obj  = readOneRow(sql);
        int res = Integer.parseInt(obj.get("id"));
        return res;

    }
}

