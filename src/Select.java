import java.sql.*;
import java.util.ArrayList;

public class Select{
    private Connection con;
    private Statement stmnt;
    private ResultSet rs;

    public Select(Connection con, Statement stmnt) {
        this.con = con;
        this.stmnt = stmnt;
    }


    //Método que filtra los registros por nombre
    public ArrayList<Contacto> obtenerRegistroPorNombre(String nombre) throws SQLException {
        rs = stmnt.executeQuery("SELECT * FROM Contactos WHERE nombre='" + nombre + "'");

        while (rs.next()) {
            System.out.println(rs.getString("nombre") + "//" + rs.getString("apellido"));
        }
        return null;
    }


    //Método que muestra todos los contactos para cuando abre la aplicación
    public ArrayList<Contacto> mostrarTodo() throws SQLException{
        rs = stmnt.executeQuery("SELECT * FROM Contactos");

        ArrayList<Contacto> arrContacto = new ArrayList<Contacto>();

        while (rs.next()) {
            Contacto contacto = new Contacto(rs.getInt("id"),
                    rs.getString("foto"), rs.getString("nombre"), rs.getString("apellido"),
                    rs.getString("compania"), rs.getString("posicion"), rs.getString("email"),
                    rs.getString("telefono"), rs.getString("notas"));

            arrContacto.add(contacto);
        }

        return arrContacto;
    }

    //Método para saber cuál es el último registro agregado en la BD mediante su índice
    public int retornaUltimoIndice() throws SQLException{
        rs = stmnt.executeQuery(" select max(id) as ultimo from Contactos");

        int x = 0;
        while(rs.next()){
            x = rs.getInt(1);
        }

        return x;
    }

    //Método que retorna un registro mediante su id
    public Contacto retornaPorID(int id) throws SQLException{
        rs = stmnt.executeQuery("select * from Contactos where id="+id);

        Contacto seleccionado = null;

        while(rs.next()){
            seleccionado = new Contacto(rs.getInt("id"), rs.getString("foto"), rs.getString("nombre"),
                    rs.getString("apellido"),rs.getString("compania"),rs.getString("posicion"),
                    rs.getString("email"),rs.getString("telefono"),rs.getString("notas"));
        }

        return seleccionado;
    }
}
