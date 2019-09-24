import java.sql.*;

public class Insert {

    private Connection con;
    private Statement stmnt;

    public Insert(Connection con, Statement stmnt){
        this.con = con;
        this.stmnt = stmnt;
    }

    public void insertarRegistro(Contacto contacto) throws SQLException{

        String consulta = "insert into Contactos(foto, nombre, apellido, compania, posicion, email, telefono, notas)" +
                " values('"+contacto.getFoto()+"','"+contacto.getNombre()+"', '"+contacto.getApellido()+"', '"+contacto.getCompania()+
                "', '"+contacto.getPosicion()+"', '"+contacto.getEmail()+"','"+contacto.getTelefono()+
                "','"+ contacto.getNotas() +"')";

        stmnt.executeUpdate(consulta);
    }

}