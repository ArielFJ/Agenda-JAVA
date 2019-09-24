import java.sql.*;

public class Delete {
    private Connection con;
    private Statement stmnt;

    public Delete(Connection con, Statement stmnt) {
        this.con = con;
        this.stmnt = stmnt;
    }

    public void borrarRegistro(Contacto contacto) throws SQLException{

        String consulta = "DELETE FROM Contactos WHERE id="+contacto.getId();

        PreparedStatement pStmnt= con.prepareStatement(consulta);

        pStmnt.executeUpdate();
    }
}
