import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class MiFrame extends JFrame {

    private int ultimoContactoIndexado;
    private int contactosDesplegados;
    private int iteradorContactos;
    private int paginaActual;

    private ArrayList<Contacto> contactos;
    private Connection con;
    private Statement stmnt;
    private ManejaBD mbd;

    private JPanel panelAplicacion;

    public int getContactosDesplegados() {
        return contactosDesplegados;
    }

    public void setIteradorContactos(int x){
        this.iteradorContactos = x;
    }

    public void setContactosDesplegados(int x){
        this.contactosDesplegados = x;
    }

    public void setPaginaActual(int paginaActual) {
        this.paginaActual = paginaActual;
    }

    public void setContactosPorPagina(int contactosPorPagina) {
        this.contactosPorPagina = contactosPorPagina;
    }

    public int getUltimoContactoIndexado() {
        return ultimoContactoIndexado;
    }

    public MiFrame(){

        try{
            //Variable necesaria para definir por donde empezar la nueva
            //hoja de contactos
            ultimoContactoIndexado = 0;

            //Conexión con BD y sentencia
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Final","usuario","3290");
            stmnt = con.createStatement();

            mbd = new ManejaBD(con, stmnt);

            //Características del Frame
            setResizable(false);
            setTitle("Contactos");
            setSize(600, 800);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            //Barra de menú del frame
            JMenuBar menuBar = new JMenuBar();
            JMenuItem busqueda = new JMenuItem("Búsqueda");
            JMenuItem nuevo = new JMenuItem("Nuevo");

            busqueda.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    FrameBusqueda frameBusqueda = new FrameBusqueda();
                    frameBusqueda.setVisible(true);
                }
            });

            //Esta variable solo se usa en el método siguiente
            MiFrame esteMismo = this;
            nuevo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    FrameAgregarContacto nuevoContacto =  new FrameAgregarContacto(mbd, esteMismo);
                    nuevoContacto.setVisible(true);
                }
            });

            menuBar.add(busqueda);
            menuBar.add(nuevo);

            //Esta variable abarca la parte donde se muestran los contactos
            panelAplicacion = new JPanel();
            panelAplicacion.setLayout(new GridLayout(4,0));
            panelAplicacion.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

            //Método para actualizar la vista donde se muestran los contactos
            iteradorContactos = 0;
            paginaActual = 1;
            contactosDesplegados = 0;
            actualizarContactos(0);
            System.out.println(ultimoContactoIndexado);

            //En este panel van los botones para cambiar de página de contactos
            JPanel panelBotonesOtrosContactos = new JPanel();
            panelBotonesOtrosContactos.setLayout(new BorderLayout());
            panelBotonesOtrosContactos.setBorder(BorderFactory.createEmptyBorder(5,100,10,150));

            JButton retroceder = new JButton("Retroceder");
            JButton avanzar = new JButton("Avanzar");

            retroceder.addActionListener(new Retroceder());
            avanzar.addActionListener(new Avanzar());

            panelBotonesOtrosContactos.add(retroceder, BorderLayout.WEST);
            panelBotonesOtrosContactos.add(avanzar,BorderLayout.EAST);

            add(menuBar, BorderLayout.NORTH);
            add(panelAplicacion, BorderLayout.CENTER);
            add(panelBotonesOtrosContactos, BorderLayout.SOUTH);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void actualizarContactos(int numeroInicio) throws SQLException{
        panelAplicacion.removeAll();
        contactos = mbd.select.mostrarTodo();
        System.out.println(contactos.size());
        contactosPorPagina = 0;

        for(iteradorContactos = numeroInicio; iteradorContactos < contactos.size(); iteradorContactos++){
            Contacto contacto = contactos.get(iteradorContactos);
            MiPanel panel = new MiPanel(mbd, this, contacto);
            panelAplicacion.add(panel);
            ultimoContactoIndexado = Integer.parseInt(panel.idLBL.getText());
            contactosDesplegados++;
            contactosPorPagina++;
            if(contactosDesplegados % 4 == 0) break;
        }
        SwingUtilities.updateComponentTreeUI(panelAplicacion);
    }


    private int contactosPorPagina;
    class Avanzar implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(paginaActual >= 1){
                try{
                    if(contactosDesplegados == paginaActual*4 && contactos.size() > contactosDesplegados) {
                        contactosPorPagina = 0;
                        actualizarContactos(contactosDesplegados);
                        paginaActual++;
                    }

                }catch (Exception sql){
                    System.out.println("Error en Avanzar");
                    sql.printStackTrace();
                }
            }
        }
    }

    class Retroceder implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(paginaActual > 1){
                try{
                    if(contactosDesplegados <= paginaActual*4) {
                        contactosDesplegados -= contactosPorPagina + 4;
                        actualizarContactos(contactosDesplegados);
                        paginaActual--;
                    }
                }catch (Exception sql){
                    System.out.println("Error en Retroceder");
                    sql.printStackTrace();
                }
            }
        }
    }
}
