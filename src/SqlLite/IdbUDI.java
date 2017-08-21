package SqlLite;

import java.sql.SQLException;

public interface IdbUDI {
    void insert() throws SQLException, ClassNotFoundException;
    void update() throws SQLException, ClassNotFoundException;
    void delete() throws SQLException, ClassNotFoundException;
}
