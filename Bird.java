package FlappyBird;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Bird {
    public float x, y, vx, vy;
    public static final int RAD = 25;
    private Image img;
    public Bird() {
        x = FlappyBird.WIDTH/2;
        y = FlappyBird.HEIGHT/2;
        try {
            img = ImageIO.read(new File("src/Immagini/sticker,375x360.u2.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    //Aggiorna la posizione dell'uccello simlando l'effetto della gravit
    public void physics() {
        x+=vx;
        y+=vy;
        vy+=0.5f;
    }
    //Disegna l'immagine dell'uccello sullo schermo.
    public void update(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawImage(img, Math.round(x-RAD),Math.round(y-RAD),2*RAD,2*RAD, null);
    }
    //Simula il salto dell'uccello impostando la sua velocità verticale
    public void jump() {
        vy = -8;
    }
    //Reimposta la posizione e le velocità dell'uccello al centro dello schermo e azzera le velocità.
    public void reset() {
        x = 640/2;
        y = 640/2;
        vx = vy = 0;
    }
}
