package FlappyBird;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FlappyBird implements ActionListener, KeyListener {
    
    public static final int FPS = 60, WIDTH = 640, HEIGHT = 480;
    
    private Bird bird;
    private JFrame frame;
    private JPanel panel;
    private ArrayList<Rectangle> rects;
    private int time, scroll;
    private Timer t;
    private boolean paused;
    
    // Metodo per iniziare il gioco
    public void go() {
    	
        frame = new JFrame("Flappy Bird");
        bird = new Bird();
        rects = new ArrayList<Rectangle>();
        panel = new GamePanel(this, bird, rects);
        frame.add(panel);
        frame.getBackground();
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(this);
        paused = true;
        
        t = new Timer(1000/FPS, this);
        t.start();
    }
 // Metodo main per avviare il gioco
    public static void main(String[] args) {
        new FlappyBird().go();
    }
 // Metodo chiamato ogni volta che il timer genera un evento
    @Override
    public void actionPerformed(ActionEvent e) {
        panel.repaint();
        if(!paused) {
            bird.physics();
            if(scroll % 90 == 0) {
                Rectangle r = new Rectangle(WIDTH, 0, GamePanel.PIPE_W, (int) ((Math.random()*HEIGHT)/5f + (0.2f)*HEIGHT));
                int h2 = (int) ((Math.random()*HEIGHT)/5f + (0.2f)*HEIGHT);
                Rectangle r2 = new Rectangle(WIDTH, HEIGHT - h2, GamePanel.PIPE_W, h2);
                rects.add(r);
                rects.add(r2);
            }
            ArrayList<Rectangle> toRemove = new ArrayList<Rectangle>();
            boolean game = true;
         // Aggiorna la posizione degli ostacoli
            for(Rectangle r : rects) {
                r.x-=3;
             // Rimuove gli ostacoli fuori dallo schermo
                if(r.x + r.width <= 0) {
                    toRemove.add(r);
                }
                // Controlla le collisioni con l'uccello
                if(r.contains(bird.x, bird.y)) {
                    JOptionPane.showMessageDialog(frame, "Hai perso!\n"+"Hai avuto un punteggio di: "+time+".");
                    game = false;
                }
            }
            rects.removeAll(toRemove);
            time++;
            scroll++;

            if(bird.y > HEIGHT || bird.y+bird.RAD < 0) {
                game = false;
            }

            if(!game) {
                rects.clear();
                bird.reset();
                time = 0;
                scroll = 0;
                paused = true;
            }
        }
        else {
            
        }
    }
    
    public int getScore() {
        return time;
    }
 // Metodo chiamato quando un tasto viene premut
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP) {
            bird.jump();
        }
        else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
            paused = false;
        }
    }
    public void keyReleased(KeyEvent e) {
        
    }
    public void keyTyped(KeyEvent e) {
        
    }
    
    public boolean paused() {
        return paused;
    }
}
