package SpaceInvaders;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class SpaceInvaders extends JFrame  {

    public SpaceInvaders() {
        initUI();
    }

    private void initUI() {

        add(new Board());
        setTitle("Space Invaders");
        setSize(Dati.BOARD_WIDTH, Dati.BOARD_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
    	// EventQueue.invokeLater()  l'inizializzazione dell'evento delle componenti swing
        EventQueue.invokeLater(() -> {
        	// Crea un'istanza della classe SpaceInvaders
            var ex = new SpaceInvaders();
            ex.setVisible(true);
        });
    }
}
