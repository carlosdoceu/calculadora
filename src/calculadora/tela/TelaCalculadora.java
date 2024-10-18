package calculadora.tela;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

public class TelaCalculadora extends JFrame {

    public TelaCalculadora() {
        organizarLayout();

        // setUndecorated(true);
        setSize(332,422);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(this);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TelaCalculadora();
    }

    private void organizarLayout() {
        setLayout(new BorderLayout());

        Display display = new Display();
        display.setPreferredSize(new Dimension(233,60));
        add(display, BorderLayout.NORTH);
        Teclado teclado = new Teclado();
        add(teclado, BorderLayout.CENTER);
    }

}
