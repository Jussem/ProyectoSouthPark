package autonoma.deadlycode.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Representa al jugador principal (Cartman) en el campo de batalla.
 * Controla el movimiento, ataques, curación y límites del personaje.
 * 
 * @author Juan Sebastian Lopez Guzman
 * @author Cristian Camilo Salazar Arenas
 * @author Juan Jose Morales
 * @version 1.0
 * @since 2023
 */
public class JugadorCartman extends Sprite {
    
    /** Cantidad de pociones de curación disponibles */
    private int pocionesRestantes = 3;
    
    /** Distancia de movimiento por paso */
    public static final int STEP = 10;
    
    /** Límite máximo en el eje X del campo */
    private int maxX;
    
    /** Límite máximo en el eje Y del campo */
    private int maxY;
    
    /** Referencia al campo de batalla asociado */
    private CampoDeBatalla campo;
    
    /** Puntos de vida actuales del jugador */
    private int vida;

    /**
     * Crea una nueva instancia del jugador Cartman con posición, tamaño y color específicos.
     * 
     * @param x Posición horizontal inicial
     * @param y Posición vertical inicial
     * @param width Ancho del personaje
     * @param height Alto del personaje
     * @param color Color representativo del personaje
     */
    public JugadorCartman(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        this.vida=100;
        cargarImagen();
    }
    /**
     * Carga la imagen del jugador desde los recursos.
     */
    private void cargarImagen() {
        try {
            imagen = ImageIO.read(getClass().getResource("/autonoma/deadlycode/images/JugadorCaminando.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     /**
     * Dibuja al jugador y su barra de vida en el contexto gráfico especificado.
     * 
     * @param g Contexto gráfico donde se dibujará
     */
    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, 50, 50, null);
        g.setColor(Color.RED);
        g.fillRect(x, y - 10, width * vida / 100, 5);
    }
     /**
     * Mueve al jugador según la tecla presionada, manteniéndolo dentro de los límites del campo.
     * 
     * @param e Evento de teclado que indica la dirección de movimiento
     */
    public void mover(java.awt.event.KeyEvent e) {
        switch (e.getKeyCode()) {
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
    /**
     * Establece los límites del campo de batalla y centra al jugador.
     * 
     * @param maxX Límite horizontal máximo
     * @param maxY Límite vertical máximo
     */
    public void inicializarLimites(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.x = (maxX - width) / 2;
        this.y = (maxY - height) / 2;
    }
    /**
     * Realiza un ataque normal contra un enemigo.
     * 
     * @param enemigo Objetivo que recibirá el daño
     */
    public void atacar(Personaje enemigo) {
        enemigo.recibirDanio(20); // Ataque normal
    }
    /**
     * Realiza el ataque especial "Hola Mundo" contra un enemigo.
     * 
     * @param enemigo Objetivo que recibirá el daño
     */
    public void HolaMundo(Personaje enemigo) {
        enemigo.recibirDanio(35); // Ataque especial
    }
    /**
     * Utiliza una poción para recuperar salud (50 puntos), si quedan pociones disponibles.
     */
     public void curar() {
        if (pocionesRestantes > 0) {
            this.vida = Math.min(vida + 50, 100);
            pocionesRestantes--;
        }
    }
    /**
     * Verifica si el jugador tiene pociones de curación disponibles.
     * 
     * @return true si tiene pociones disponibles, false en caso contrario
     */
    public boolean tienePociones() {
        return pocionesRestantes > 0;
    }
    
}
