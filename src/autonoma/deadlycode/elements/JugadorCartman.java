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
public class JugadorCartman extends Personaje {
    
    /** Cantidad de pociones de curación disponibles */
    public int pocionesRestantes = 3;
    public int usosHolaMundo = 3;
    
    /** Distancia de movimiento por paso */
    public static final int STEP = 5;
    
    /** Límite máximo en el eje X del campo */
    private int maxX;
    
    /** Límite máximo en el eje Y del campo */
    private int maxY;
    
    /** Referencia al campo de batalla asociado */
    private CampoDeBatalla campo;
    
    /** Puntos de vida actuales del jugador */
    private int vida;
    
    private boolean pelea1Activa = true;
    private boolean pelea2Activa = true;
    private boolean pelea3Activa = true;

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
        super(x, y, width, height, color); // Llama al constructor de Personaje
        this.vida = 100;
        this.pocionesRestantes = 3;
        this.usosHolaMundo = 3;
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
        g.drawImage(imagen, x, y, 90, 100, null); 
        g.setColor(Color.RED);
        g.fillRect(x, y, width * vida / 100, 5);
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
//        y = Math.max(0, Math.min(y, maxY - height));
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
//        this.x = (maxX - width) / 2;
//        this.y = (maxY - height) / 2;
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
        if (usosHolaMundo > 0) {
            enemigo.recibirDanio(35);
            usosHolaMundo--;
            System.out.println("Usos restantes de HolaMundo: " + usosHolaMundo);
        } else {
            System.out.println("¡No te quedan usos de HolaMundo!");
        }
    }
    /**
     * Utiliza una poción para recuperar salud (50 puntos), si quedan pociones disponibles.
     */
    public void curar() {
        if (pocionesRestantes > 0) {
            this.vida = Math.min(vida + 50, 100);
            pocionesRestantes--; // Atributo heredado
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
    public boolean puedeUsarHolaMundo() {
        return usosHolaMundo > 0;
    }
    public void recibirDanio(int danio) {
        this.vida -= danio;
        if (this.vida < 0) {
            this.vida = 0;
        }
    }
    //Getters
    public int getPosX() {
        return this.x;
    }

    public int getPosY() {
        return this.y;
    }
    public int getVida() {
        return this.vida;
    }
     public boolean isPelea1Activa() { return pelea1Activa; }
    public void setPelea1Activa(boolean activa) { this.pelea1Activa = activa; }

    public boolean isPelea2Activa() { return pelea2Activa; }
    public void setPelea2Activa(boolean activa) { this.pelea2Activa = activa; }

    public boolean isPelea3Activa() { return pelea3Activa; }
    public void setPelea3Activa(boolean activa) { this.pelea3Activa = activa; }
    
}
