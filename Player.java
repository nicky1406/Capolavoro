package SpaceInvaders;


import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class Player extends Sprite {

    private int width;

    public Player() {

        initPlayer();
    }

    private void initPlayer() {
        var playerImg = "src/Immagini/player.png";
        var ii = new ImageIcon(playerImg);
        width = ii.getImage().getWidth(null);
        setImage(ii.getImage());
        int START_X = 270;
        setX(START_X);
        int START_Y = 280;
        setY(START_Y);
    }

    //aggiorna la posizione del giocatre
    public void act() {
        x += dx;
        if (x <= 2) {
            x = 2;
        }

        if (x >= Dati.BOARD_WIDTH - 2 * width) {
            x = Dati.BOARD_WIDTH - 2 * width;
        }
    }
    
    //Gestisce la pressione dei tasti
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }
}
