import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class FrameDetalles extends JFrame{

    private MiTextField nombreTF;
    private MiTextField apellidoTF;
    private MiTextField companiaTF;
    private MiTextField posicionTF;
    private MiTextField emailTF;
    private MiTextField telefonoTF;
    private MiTextField notasTF;

    public FrameDetalles(ManejaBD mbd, MiFrame frame, Contacto contacto){


        //Características del frame
        setTitle("Más Información");
        setResizable(false);
        setSize(700,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Bloque para poner la foto del usuario
        String rutaImagen = contacto.getFoto();
        setLayout(new BorderLayout());
        ImageIcon imagen = new ImageIcon(rutaImagen);
        Image foto = imagen.getImage();
        ImageIcon fotoUsuario = new ImageIcon(foto.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        JLabel label1 = new JLabel();
        label1.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
        label1.setIcon(fotoUsuario);

        ImageIcon imgIconEliminar = new ImageIcon("eliminar.png");
        Image imgEliminar = imgIconEliminar.getImage();
        ImageIcon iconoEliminar = new ImageIcon(imgEliminar.getScaledInstance(50,50,Image.SCALE_SMOOTH));


        //En este panel se despliegan los datos del usuario
        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new GridLayout(7, 0));

        JPanel panelNombre = new JPanel();
        JPanel panelApellido = new JPanel();
        JPanel panelCompania = new JPanel();
        JPanel panelPosicion = new JPanel();
        JPanel panelEmail = new JPanel();
        JPanel panelTelefono = new JPanel();
        JPanel panelNotas = new JPanel();

        int id = contacto.getId();
        nombreTF = new MiTextField();
        apellidoTF = new MiTextField();
        companiaTF = new MiTextField();
        posicionTF = new MiTextField();
        emailTF = new MiTextField();
        telefonoTF = new MiTextField();
        notasTF = new MiTextField();

        nombreTF.setText(contacto.getNombre());
        apellidoTF.setText(contacto.getApellido());
        companiaTF.setText(contacto.getCompania());
        posicionTF.setText(contacto.getPosicion());
        emailTF.setText(contacto.getEmail());
        telefonoTF.setText(contacto.getTelefono());
        notasTF.setText(contacto.getNotas());

        panelNombre.add(new JLabel("Nombre:"));
        panelNombre.add(nombreTF);
        panelApellido.add(new JLabel("Apellido:"));
        panelApellido.add(apellidoTF);
        panelCompania.add(new JLabel("Compañía:"));
        panelCompania.add(companiaTF);
        panelPosicion.add(new JLabel("Posición:"));
        panelPosicion.add(posicionTF);
        panelEmail.add(new JLabel("Email:"));
        panelEmail.add(emailTF);
        panelTelefono.add(new JLabel("Teléfono:"));
        panelTelefono.add(telefonoTF);
        panelNotas.add(new JLabel("Notas:"));
        panelNotas.add(notasTF);

        panelDatos.add(panelNombre);
        panelDatos.add(panelApellido);
        panelDatos.add(panelCompania);
        panelDatos.add(panelPosicion);
        panelDatos.add(panelEmail);
        panelDatos.add(panelTelefono);
        panelDatos.add(panelNotas);


        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(8,0));

        JButton modificar = new JButton("Modificar");

        JButton cambiarFoto = new JButton("Cambiar Foto");

        JButton eliminar = new JButton();
        eliminar.setContentAreaFilled(false);
        eliminar.setOpaque(false);
        eliminar.setBorderPainted(false);
        eliminar.setIcon(iconoEliminar);

        eliminar.addActionListener(new Eliminar(mbd, frame, contacto));
        cambiarFoto.addActionListener(new CambiarFoto(mbd, frame, contacto));
        modificar.addActionListener(new Modificar(mbd, frame, contacto));

        //Borde para dar estética al frame
        panelBotones.setBorder(BorderFactory.createLineBorder(panelBotones.getBackground(), 20));

        panelBotones.add(new JPanel());
        panelBotones.add(modificar);
        panelBotones.add(new JPanel());
        panelBotones.add(cambiarFoto);
        panelBotones.add(new JPanel());
        panelBotones.add(eliminar);
        panelBotones.add(new JPanel());
        panelBotones.add(new JPanel());

        add(label1, BorderLayout.WEST);
        add(panelDatos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.EAST);

    }

    class Eliminar implements ActionListener{
        ManejaBD mbd;
        MiFrame frame;
        Contacto contacto;

        public Eliminar(ManejaBD mbd, MiFrame frame, Contacto contacto){
            this.mbd=mbd;
            this.frame=frame;
            this.contacto=contacto;
        }

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
            dispose();

        }
    }

    class Modificar implements ActionListener{
        ManejaBD mbd;
        MiFrame frame;
        Contacto contacto;

        public Modificar(ManejaBD mbd, MiFrame frame, Contacto contacto){
            this.mbd=mbd;
            this.frame=frame;
            this.contacto=contacto;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try{

                contacto.setNombre(nombreTF.getText());
                contacto.setApellido(apellidoTF.getText());
                contacto.setCompania(companiaTF.getText());
                contacto.setPosicion(posicionTF.getText());
                contacto.setEmail(emailTF.getText());
                contacto.setTelefono(telefonoTF.getText());
                contacto.setNotas(notasTF.getText());

                mbd.update.modificarRegistro(contacto);
                frame.setContactosPorPagina(0);
                frame.setContactosDesplegados(0);
                frame.setPaginaActual(1);
                frame.actualizarContactos(0);
                System.out.println(frame.getUltimoContactoIndexado());
            }catch (SQLException e){
                e.printStackTrace();
                System.out.println("Error en la parte de modificar contacto");
            }
            dispose();

        }
    }

    class CambiarFoto implements ActionListener{
        ManejaBD mbd;
        MiFrame frame;
        Contacto contacto;

        public CambiarFoto(ManejaBD mbd, MiFrame frame, Contacto contacto){
            this.mbd=mbd;
            this.frame=frame;
            this.contacto=contacto;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try{

                JFileChooser fileChooser = new JFileChooser();

                fileChooser.showOpenDialog(null);

                contacto.setFoto(fileChooser.getSelectedFile().getAbsolutePath());

                mbd.update.cambiarFoto(contacto);
                frame.setContactosPorPagina(0);
                frame.setContactosDesplegados(0);
                frame.setPaginaActual(1);
                frame.actualizarContactos(0);
                System.out.println(frame.getUltimoContactoIndexado());
            }catch (SQLException e){
                e.printStackTrace();
                System.out.println("Error en la parte de modificar contacto");
            }
            dispose();

        }
    }

}
