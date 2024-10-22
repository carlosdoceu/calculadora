package calculadora.tela;

import calculadora.back.Memoria;
import calculadora.back.MemoriaObserver;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Display extends JPanel  implements MemoriaObserver{

    private JLabel label;

    public Display() {
        setBackground(new Color(37, 27, 47));

        Memoria.getInstancia().adicionarObserver(this);
        
        label = new JLabel(Memoria.getInstancia().getTextoAtual());
        label.setForeground(Color.WHITE);
        label.setFont(new Font("courier", Font.PLAIN, 30));
        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 25));
        add(label);

        

    }

    @Override
    public void valorAlterado(String novoValor) {
        label.setText(novoValor);
    }
}
