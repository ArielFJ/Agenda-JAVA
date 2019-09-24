import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class FrameAgregarContacto extends JFrame{

    ManejaBD mbd;
    MiFrame frame;

    String rutaFotoNueva;
    MiTextField nombreTF;
    MiTextField apellidoTF;
    MiTextField companiaTF;
    MiTextField posicionTF;
    MiTextField emailTF;
    MiTextField telefonoTF;
    MiTextField notasTF;

    String rutaImagen;

    public FrameAgregarContacto(ManejaBD mbd, MiFrame frame){
        this.mbd = mbd;
        this.frame = frame;
        rutaImagen = "/home/ariel/Documentos/PROGRAMACIÓN/PROGRAMACIÓN 1/Final_Interfaz/panelAgregarUsuario.png";
        rutaFotoNueva = "/home/ariel/Documentos/PROGRAMACIÓN/PROGRAMACIÓN 1/Final_Interfaz/usuarioDefault.png";

        setResizable(false);
        setTitle("Nuevo Contacto");
        setSize(600,400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        ImageIcon imagen = new ImageIcon(rutaImagen);
        Image foto = imagen.getImage();
        ImageIcon fotoUsuario = new ImageIcon(foto.getScaledInstance(140, 140, Image.SCALE_SMOOTH));

        JLabel label1 = new JLabel();
        label1.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
        label1.setIcon(fotoUsuario);

        label1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                JFileChooser fileChooser = new JFileChooser();
                int seleccion = fileChooser.showOpenDialog(null);
                if(seleccion == JFileChooser.APPROVE_OPTION){
                    rutaFotoNueva = fileChooser.getSelectedFile().getAbsolutePath();
                    rutaImagen = fileChooser.getSelectedFile().getAbsolutePath();
                    ImageIcon nuevaFoto = new ImageIcon(rutaImagen);
                    Image image = nuevaFoto.getImage();
                    ImageIcon fotoACambiar = new ImageIcon(image.getScaledInstance(140,140,Image.SCALE_SMOOTH));
                    label1.setIcon(fotoACambiar);
                }
            }
        });

        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new GridLayout(7, 0));

        JPanel panelNombre = new JPanel();
        JPanel panelApellido = new JPanel();
        JPanel panelCompania = new JPanel();
        JPanel panelPosicion = new JPanel();
        JPanel panelEmail = new JPanel();
        JPanel panelTelefono = new JPanel();
        JPanel panelNotas = new JPanel();

        nombreTF = new MiTextField();
        apellidoTF = new MiTextField();
        companiaTF = new MiTextField();
        posicionTF = new MiTextField();
        emailTF = new MiTextField();
        telefonoTF = new MiTextField();

        notasTF = new MiTextField();

        panelNombre.add(new JLabel("Nombre:"));
        panelNombre.add(nombreTF);
        nombreTF.agregarRestriccionLetrasNumeros(25);

        panelApellido.add(new JLabel("Apellido:"));
        panelApellido.add(apellidoTF);
        apellidoTF.agregarRestriccionLetrasNumeros(25);

        panelCompania.add(new JLabel("Compañía:"));
        panelCompania.add(companiaTF);
        companiaTF.agregarRestriccionMaximosCaracteres(30);

        panelPosicion.add(new JLabel("Posición:"));
        panelPosicion.add(posicionTF);
        posicionTF.agregarRestriccionLetrasNumeros(20);

        panelEmail.add(new JLabel("Email:"));
        panelEmail.add(emailTF);
        emailTF.agregarRestriccionMaximosCaracteres(50);

        panelTelefono.add(new JLabel("Teléfono:"));
        panelTelefono.add(telefonoTF);
        telefonoTF.agregarRestrincionDígitos(11);

        panelNotas.add(new JLabel("Notas:"));
        panelNotas.add(notasTF);
        notasTF.agregarRestriccionMaximosCaracteres(100);

        panelDatos.add(panelNombre);
        panelDatos.add(panelApellido);
        panelDatos.add(panelCompania);
        panelDatos.add(panelPosicion);
        panelDatos.add(panelEmail);
        panelDatos.add(panelTelefono);
        panelDatos.add(panelNotas);


        JButton guardar = new JButton();
        ImageIcon imgIconGuardar = new ImageIcon("guardar.png");
        Image imgGuardar = imgIconGuardar.getImage();
        ImageIcon iconoGuardar = new ImageIcon(imgGuardar.getScaledInstance(55,55,Image.SCALE_SMOOTH));
        guardar.setIcon(iconoGuardar);
        guardar.setBorderPainted(false);
        guardar.setOpaque(false);
        guardar.setContentAreaFilled(false);
        guardar.addActionListener(new Guardar());

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BorderLayout());

        panelBotones.setBorder(BorderFactory.createLineBorder(panelBotones.getBackground(), 40));
        panelBotones.add(guardar, BorderLayout.NORTH);


        add(label1, BorderLayout.WEST);
        add(panelDatos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.EAST);

    }

    class Guardar implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try{

                if(!nombreTF.getText().isEmpty() && !telefonoTF.getText().isEmpty()){

                    Contacto nuevo = new Contacto(mbd.select.retornaUltimoIndice() + 1,rutaFotoNueva,nombreTF.getText(),
                            apellidoTF.getText(), companiaTF.getText(), posicionTF.getText(),
                            emailTF.getText(), telefonoTF.getText(), notasTF.getText());

                    mbd.insert.insertarRegistro(nuevo);

                    frame.setContactosPorPagina(0);
                    frame.setContactosDesplegados(0);
                    frame.setPaginaActual(1);
                    frame.actualizarContactos(0);

                    dispose();

                    System.out.println(nuevo.toString());

                }else{
                    getToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Debe llenar los campos nombre y teléfono para guardar.");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
