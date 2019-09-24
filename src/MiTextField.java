import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//Esta clase existe solo para darle un tamaño a los Text Fields
public class MiTextField extends JTextField{

    public MiTextField(){
        setColumns(15);
    }

    public void agregarRestrincionDígitos(int maximo){
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                char c = keyEvent.getKeyChar();

                if(c != KeyEvent.VK_BACK_SPACE){
                    if(getText().length() >= maximo || !Character.isDigit(c)){
                        keyEvent.consume();
                        getToolkit().beep();
                        if(getText().length() >= maximo)
                            JOptionPane.showMessageDialog(null, "Ha sobrepasado el límite de "+maximo+" caracteres.");
                        else
                            JOptionPane.showMessageDialog(null, "Usted ha ingresado un caracter que no es numérico.");

                    }
                }

            }
        });
    }

    public void agregarRestriccionSoloLetras(int maximo){
        addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent ke){
                char c = ke.getKeyChar();

                if(c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_SPACE) {
                    if (getText().length() >= maximo || !Character.isLetter(c)) {
                        ke.consume();
                        getToolkit().beep();
                        if (getText().length() >= maximo)
                            JOptionPane.showMessageDialog(null, "Ha sobrepasado el límite de " + maximo + " caracteres.");
                        else
                            JOptionPane.showMessageDialog(null, "Usted ha ingresado un caracter no alfabético.");

                    }
                }
            }

        });
    }

    public void agregarRestriccionMaximosCaracteres(int maximo){
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                if(getText().length() >= maximo){
                    keyEvent.consume();
                    getToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Ha sobrepasado el límite de "+maximo+" caracteres.");
                }
            }
        });
    }

    public void agregarRestriccionLetrasNumeros(int maximo){
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                char c = keyEvent.getKeyChar();

                if(c != KeyEvent.VK_SPACE && c != KeyEvent.VK_BACK_SPACE) {
                    if (getText().length() >= maximo || !Character.isLetterOrDigit(c)) {
                        keyEvent.consume();
                        getToolkit().beep();
                        if (getText().length() >= maximo)
                            JOptionPane.showMessageDialog(null, "Ha sobrepasado el límite de " + maximo + " caracteres.");
                        else
                            JOptionPane.showMessageDialog(null, "Usted ha ingresado un caracter que no es numérico ni alfabético.");
                    }
                }
            }
        });
    }



}
