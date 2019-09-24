import java.sql.*;

public class Update {
    private Connection con;
    private Statement stmnt;

    public Update(Connection con, Statement stmnt) {
        this.con = con;
        this.stmnt = stmnt;
    }

    public void modificarRegistro(Contacto contacto) throws SQLException{

        String query = "UPDATE Contactos SET nombre=?, apellido=?, compania=?,posicion=?," +
                "email=?, telefono=?, notas=? WHERE id=?";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, contacto.getNombre());
        ps.setString(2, contacto.getApellido());
        ps.setString(3,contacto.getCompania());
        ps.setString(4,contacto.getPosicion());
        ps.setString(5,contacto.getEmail());
        ps.setString(6,contacto.getTelefono());
        ps.setString(7,contacto.getNotas());
        ps.setInt(8, contacto.getId());

        ps.executeUpdate();
        ps.close();
    }

    public void cambiarFoto(Contacto contacto) throws SQLException{
        String query = "UPDATE Contactos SET foto=? WHERE id=?";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, contacto.getFoto());
        ps.setInt(2, contacto.getId());

        ps.execute();
    }

    public void modificarRegistroStatement(Contacto contacto) throws SQLException{

        System.out.println(contacto.toString());
        String query = "UPDATE Contactos SET nombre='Sujeto 1', apellido='Fermin', compania='Ninguna' WHERE id=" +contacto.getId();

        Statement ps = con.createStatement();

        ps.executeUpdate(query);

    }
}