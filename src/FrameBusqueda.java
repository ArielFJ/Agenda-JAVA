import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameBusqueda extends JFrame{

    public FrameBusqueda(){

        setTitle("Buscar");
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        MiTextField cuadroBusqueda = new MiTextField();
        JButton buscar = new JButton();

        ImageIcon imgIcn = new ImageIcon("lupa3d.png");
        Image img = imgIcn.getImage();
        ImageIcon iconoBuscar = new ImageIcon(img.getScaledInstance(30,30, Image.SCALE_SMOOTH));

        buscar.setOpaque(false);
        buscar.setContentAreaFilled(false);
        buscar.setBorderPainted(false);
        buscar.setIcon(iconoBuscar);

        JRadioButton btnNombre = new JRadioButton("Por Nombre");
        JRadioButton btnApellido = new JRadioButton("Por Apellido");
        JRadioButton btnNombreCompleto = new JRadioButton("Por Nombre Completo");
        JRadioButton btnCompania = new JRadioButton("Por Compañía");

        JPanel panelBusqueda = new JPanel();
        panelBusqueda.add(cuadroBusqueda);
        panelBusqueda.add(buscar);

        JPanel panelSeleccion = new JPanel();
        panelSeleccion.setLayout(new GridLayout(4,0));
        panelSeleccion.setBorder(BorderFactory.createEmptyBorder(0,80,20,0));
        panelSeleccion.add(btnNombre);
        panelSeleccion.add(btnApellido);
        panelSeleccion.add(btnNombreCompleto);
        panelSeleccion.add(btnCompania);

        btnNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btnApellido.setSelected(false);
                btnCompania.setSelected(false);
                btnNombreCompleto.setSelected(false);
            }
        });
        btnApellido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btnNombre.setSelected(false);
                btnCompania.setSelected(false);
                btnNombreCompleto.setSelected(false);
            }
        });
        btnNombreCompleto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btnApellido.setSelected(false);
                btnCompania.setSelected(false);
                btnNombre.setSelected(false);
            }
        });
        btnCompania.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btnApellido.setSelected(false);
                btnNombre.setSelected(false);
                btnNombreCompleto.setSelected(false);
            }
        });


        add(panelBusqueda, BorderLayout.NORTH);
        add(panelSeleccion,BorderLayout.CENTER);

    }

}
