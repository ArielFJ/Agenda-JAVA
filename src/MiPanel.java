import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class MiPanel extends JPanel{

    //Text Fields para desplegar información básica
    JLabel idLBL;
    JLabel nombreLBL;
    JLabel apellidoLBL;
    JLabel telefonoLBL;
    String rutaImagen;

    public MiPanel(ManejaBD mbd, MiFrame frame, Contacto contacto){
        idLBL = new JLabel(String.valueOf(contacto.getId()));
        nombreLBL = new JLabel(contacto.getNombre());
        apellidoLBL = new JLabel(contacto.getApellido());
        telefonoLBL = new JLabel(contacto.getTelefono());

        this.rutaImagen = contacto.getFoto();

        nombreLBL.setForeground(Color.blue);
        apellidoLBL.setForeground(Color.blue);
        telefonoLBL.setForeground(Color.blue);

        //Características del panel
        setSize(600,200);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,3),"Contacto #"+(frame.getContactosDesplegados()+1)));
        setLayout(new BorderLayout());

        //Bloque de código para poner la imagen del usuario
        ImageIcon imagen = new ImageIcon(rutaImagen);
        Image foto = imagen.getImage();
        ImageIcon fotoUsuario = new ImageIcon(foto.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        JLabel label1 = new JLabel();
        label1.setIcon(fotoUsuario);
        label1.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));

        ImageIcon imgIconDetalles = new ImageIcon("detalles.png");
        Image imgDetalles = imgIconDetalles.getImage();
        ImageIcon iconoDetalles = new ImageIcon(imgDetalles.getScaledInstance(50,50,Image.SCALE_SMOOTH));

        ImageIcon imgIconEliminar = new ImageIcon("eliminar.png");
        Image imgEliminar = imgIconEliminar.getImage();
        ImageIcon iconoEliminar = new ImageIcon(imgEliminar.getScaledInstance(50,50,Image.SCALE_SMOOTH));

        //Panel para desplegar nombre, apellido y compañía
        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new GridLayout(3, 0));

        JPanel panelNombre = new JPanel();
        JPanel panelApellido = new JPanel();
        JPanel panelCompania = new JPanel();

        panelNombre.add(new JLabel("Nombre:"));
        panelNombre.add(nombreLBL);
        panelApellido.add(new JLabel("Apellido:"));
        panelApellido.add(apellidoLBL);
        panelCompania.add(new JLabel("Teléfono:"));
        panelCompania.add(telefonoLBL);

        //El id lo dejo invisible pero aún así puedo usarlo para distintas operaciones
        idLBL.setVisible(false);
        panelCompania.add(idLBL);

        panelDatos.add(panelNombre);
        panelDatos.add(panelApellido);
        panelDatos.add(panelCompania);

        JPanel panelBotones = new JPanel();

        JButton btnDetalles = new JButton();
        btnDetalles.setOpaque(false);
        btnDetalles.setContentAreaFilled(false);
        btnDetalles.setBorderPainted(false);
        btnDetalles.setIcon(iconoDetalles);

        btnDetalles.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int id = Integer.parseInt(idLBL.getText());
                try{
                    Contacto aDetallar = mbd.select.retornaPorID(id);
                    FrameDetalles frameDetalles = new FrameDetalles(mbd, frame, aDetallar);
                    frameDetalles.setVisible(true);
                }catch(SQLException sql){
                    sql.printStackTrace();
                    System.out.println("Error en el boton Detalles");
                }

            }
        });

        JButton eliminar = new JButton();
        eliminar.setBorderPainted(false);
        eliminar.setOpaque(false);
        eliminar.setContentAreaFilled(false);
        eliminar.setIcon(iconoEliminar);

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    mbd.delete.borrarRegistro(contacto);
                    frame.setContactosDesplegados(0);
                    frame.setIteradorContactos(0);
                    frame.setPaginaActual(1);
                    frame.actualizarContactos(0);
                    System.out.println(frame.getUltimoContactoIndexado());
                }catch (SQLException e){
                    e.printStackTrace();
                    System.out.println("Error en la parte de eliminar contacto");
                }


            }
        });

        panelBotones.add(btnDetalles);
        panelBotones.add(eliminar);

        add(label1, BorderLayout.WEST);
        add(panelDatos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.EAST);

    }

}
