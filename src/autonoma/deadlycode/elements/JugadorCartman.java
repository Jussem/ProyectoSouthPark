package autonoma.deadlycode.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Asus
 */
public class JugadorCartman extends Sprite {
    private int maxAnchoPantalla;
    private int maxAltoPantalla;
    public static final int STEP = 10;
    /** Límite máximo en X dentro del campo. */
    private int maxX;

    /** Límite máximo en Y dentro del campo. */
    private int maxY;
    /** Referencia al campo de batalla donde actúa el soldado. */
    private CampoDeBatalla campo;
    
    
    
    public JugadorCartman(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        try {
            imagen = ImageIO.read(getClass().getResource("/autonoma/deadlycode/images/JugadorCaminando.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, 50, 50, null);
    }
    public void mover(java.awt.event.KeyEvent e) {
        switch (e.getKeyCode()) {
            case java.awt.event.KeyEvent.VK_UP:
                y -= STEP;
                break;
            case java.awt.event.KeyEvent.VK_DOWN:
                y += STEP;
                break;
            case java.awt.event.KeyEvent.VK_LEFT:
                x -= STEP;
                break;
            case java.awt.event.KeyEvent.VK_RIGHT:
                x += STEP;
                break;
        }
        x = Math.max(0, Math.min(x, maxX - width));
        y = Math.max(0, Math.min(y, maxY - height));
    }
    public void inicializarLimites(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.x = (maxX - width) / 2;
        this.y = (maxY - height) / 2;
    }
    private void mantenerDentroDeLimites() {
        x = Math.max(0, Math.min(x, maxAnchoPantalla - width));
        y = Math.max(0, Math.min(y, maxAltoPantalla - height));
    }
//    public VentanaPrincipal() {
//        // ... inicialización de componentes ...
//        jugador = new JugadorCartman(100, 100, 50, 50, Color.BLUE);
//        jugador.inicializarLimites(getContentPane().getWidth(), getContentPane().getHeight());
//    }
    
}
