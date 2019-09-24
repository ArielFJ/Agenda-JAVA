import java.sql.*;

public class ManejaBD {
    private Connection conexion;
    private Statement stmnt;
    public Select select;
    public Insert insert;
    public Delete delete;
    public Update update;

    public ManejaBD(Connection conexion, Statement stmnt){
        this.conexion = conexion;
        this.stmnt = stmnt;
        select = new Select(conexion, stmnt);
        insert = new Insert(conexion, stmnt);
        delete = new Delete(conexion, stmnt);
        update = new Update(conexion, stmnt);
    }

}
