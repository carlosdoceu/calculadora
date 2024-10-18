package calculadora.tela;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Botao extends JButton {

    public Botao(String text, Color color) {
        setText(text);
        setBackground(color);
        setOpaque(true);

        setForeground(Color.WHITE); // cor do texto
        setFont(new Font("courier", Font.PLAIN, 25));// configura o tamanho da fonte e o tipo
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // configura a borda do botão 

    }

}
