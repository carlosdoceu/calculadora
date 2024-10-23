package calculadora.tela;

import calculadora.back.Memoria;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Teclado extends JPanel implements ActionListener {
    private final Color COR_CINZA_ESCURO = new Color(68, 68, 68);
    private final Color COR_CINZA_CLARO = new Color(99, 99, 99);
    private final Color COR_LARANJA = new Color(242, 163, 60);

    public Teclado() {
        // setBackground(Color.RED);

        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        setLayout(gridBagLayout);
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        // linha 1
        gridBagConstraints.gridwidth = 2;
        adicionarBotaoTela("AC", COR_CINZA_ESCURO, gridBagConstraints, 0, 0);
        gridBagConstraints.gridwidth = 1;
        
        adicionarBotaoTela("±", COR_CINZA_ESCURO, gridBagConstraints, 2, 0);
        // adicionarBotaoTela("%", COR_CINZA_ESCURO, gridBagConstraints, 2, 0);
        adicionarBotaoTela("/", COR_LARANJA, gridBagConstraints, 3, 0);

        // linha 2
        adicionarBotaoTela("7", COR_CINZA_CLARO, gridBagConstraints, 0, 1);
        adicionarBotaoTela("8", COR_CINZA_CLARO, gridBagConstraints, 1, 1);
        adicionarBotaoTela("9", COR_CINZA_CLARO, gridBagConstraints, 2, 1);
        adicionarBotaoTela("X", COR_LARANJA, gridBagConstraints, 3, 1);

        // linha 3
        adicionarBotaoTela("4", COR_CINZA_CLARO, gridBagConstraints, 0, 2);
        adicionarBotaoTela("5", COR_CINZA_CLARO, gridBagConstraints, 1, 2);
        adicionarBotaoTela("6", COR_CINZA_CLARO, gridBagConstraints, 2, 2);
        adicionarBotaoTela("-", COR_LARANJA, gridBagConstraints, 3, 2);

        // linha 4
        adicionarBotaoTela("1", COR_CINZA_CLARO, gridBagConstraints, 0, 3);
        adicionarBotaoTela("2", COR_CINZA_CLARO, gridBagConstraints, 1, 3);
        adicionarBotaoTela("3", COR_CINZA_CLARO, gridBagConstraints, 2, 3);
        adicionarBotaoTela("+", COR_LARANJA, gridBagConstraints, 3, 3);

        // linha 5
        gridBagConstraints.gridwidth = 2;
        adicionarBotaoTela("0", COR_CINZA_CLARO, gridBagConstraints, 0, 4);
        gridBagConstraints.gridwidth = 1;
        // adicionarBotaoTela("0", COR_CINZA_CLARO, gridBagConstraints, 1, 4);
        adicionarBotaoTela(",", COR_CINZA_CLARO, gridBagConstraints, 2, 4);
        adicionarBotaoTela("=", COR_LARANJA, gridBagConstraints, 3, 4);

        // add(new Botao("AC", COR_CINZA_ESCURO), gridBagConstraints);
        // add(new Botao("+/-", COR_CINZA_ESCURO), gridBagConstraints);
        // add(new Botao("%", COR_CINZA_ESCURO), gridBagConstraints);
        // add(new Botao("/", COR_LARANJA), gridBagConstraints);

        // add(new Botao("7", COR_CINZA_CLARO), gridBagConstraints);
        // add(new Botao("8", COR_CINZA_CLARO), gridBagConstraints);
        // add(new Botao("9", COR_CINZA_CLARO), gridBagConstraints);
        // add(new Botao("X", COR_LARANJA), gridBagConstraints);

        // add(new Botao("4", COR_CINZA_CLARO), gridBagConstraints);
        // add(new Botao("5", COR_CINZA_CLARO), gridBagConstraints);
        // add(new Botao("6", COR_CINZA_CLARO), gridBagConstraints);
        // add(new Botao("-", COR_LARANJA), gridBagConstraints);

        // add(new Botao("1", COR_CINZA_CLARO), gridBagConstraints);
        // add(new Botao("2", COR_CINZA_CLARO), gridBagConstraints);
        // add(new Botao("3", COR_CINZA_CLARO), gridBagConstraints);
        // add(new Botao("+", COR_LARANJA), gridBagConstraints);

        // add(new Botao("0", COR_CINZA_CLARO), gridBagConstraints);
        // add(new Botao("0", COR_CINZA_CLARO), gridBagConstraints);
        // add(new Botao(",", COR_CINZA_CLARO), gridBagConstraints);
        // add(new Botao("=", COR_LARANJA), gridBagConstraints);

    }

    public void adicionarBotaoTela(String text, Color color, GridBagConstraints gridBagConstraints, int x, int y) {
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        Botao botao = new Botao(text, color);
        botao.addActionListener(this);
        add(botao, gridBagConstraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() instanceof JButton) { 
            JButton botao = (JButton) e.getSource();      
            Memoria.getInstancia().processarComando(botao.getText());
        }
      
    }
}
