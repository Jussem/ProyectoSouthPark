package autonoma.deadlycode.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Clase que representa al personaje principal del juego: Cartman.
 * Hereda de la clase {@link Personaje} y contiene lógica relacionada con:
 * <ul>
 *   <li>Movimiento dentro del campo de batalla</li>
 *   <li>Acciones ofensivas (ataque normal y especial)</li>
 *   <li>Recuperación de vida mediante pociones</li>
 *   <li>Control de límites del campo y estados de combate</li>
 * </ul>
 * También gestiona la carga de imagen, vida y límites de movimiento.
 * 
 * @author Juan Sebastian Lopez Guzman
 * @author Cristian Camilo Salazar Arenas
 * @author Juan Jose Morales
 * @version 1.0
 * @since 2025-05-19
 */
public class JugadorCartman extends Personaje {
    
    /** Cantidad de pociones de curación disponibles. */
    public int pocionesRestantes = 5;
    
    /** Número de usos disponibles del ataque especial "Hola Mundo". */
    public int usosHolaMundo = 5;
    
    /** Cantidad de píxeles que se mueve Cartman por paso. */
    public static final int STEP = 5;
    
    /** Límite horizontal del campo de batalla. */
    private int maxX;
    
    /** Límite vertical del campo de batalla. */
    private int maxY;
    
    /** Referencia al campo de batalla (no utilizado directamente en este fragmento). */
    private CampoDeBatalla campo;
    
    /** Puntos de vida del jugador (máximo 100). */
    private int vida;

    /** Indicadores booleanos para el estado de tres peleas distintas. */
    private boolean pelea1Activa = true;
    private boolean pelea2Activa = true;
    private boolean pelea3Activa = true;

    /**
     * Constructor que inicializa al jugador con sus atributos gráficos y funcionales.
     * 
     * @param x Posición horizontal inicial
     * @param y Posición vertical inicial
     * @param width Ancho del personaje
     * @param height Alto del personaje
     * @param color Color representativo
     */
    public JugadorCartman(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        this.vida = 100;
        this.pocionesRestantes = 5;
        this.usosHolaMundo = 5;
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
     * Dibuja al jugador junto con su barra de vida en pantalla.
     * 
     * @param g Contexto gráfico donde se renderiza el personaje.
     */
    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, 90, 100, null); 
        g.setColor(Color.RED);
        g.fillRect(x, y, width * vida / 100, 5);
    }

    /**
     * Mueve al jugador horizontalmente (izquierda o derecha) según la tecla presionada.
     * 
     * @param e Evento de teclado con la tecla pulsada.
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
        x = Math.max(0, Math.min(x, maxX - width)); // Mantener dentro del campo horizontal
    }

    /**
     * Establece los límites máximos del campo de batalla para restringir el movimiento del jugador.
     * 
     * @param maxX Límite horizontal del campo.
     * @param maxY Límite vertical del campo.
     */
    public void inicializarLimites(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    /**
     * Realiza un ataque normal que inflige 20 puntos de daño al enemigo.
     * 
     * @param enemigo Personaje enemigo que recibirá el daño.
     */
    public void atacar(Personaje enemigo) {
        enemigo.recibirDanio(35);
    }

    /**
     * Realiza un ataque especial llamado "Hola Mundo" que inflige 35 de daño si hay usos disponibles.
     * 
     * @param enemigo Personaje enemigo que recibirá el daño.
     */
    public void HolaMundo(Personaje enemigo) {
        if (usosHolaMundo > 0) {
            enemigo.recibirDanio(55);
            usosHolaMundo--;
            System.out.println("Usos restantes de HolaMundo: " + usosHolaMundo);
        } else {
            System.out.println("¡No te quedan usos de HolaMundo!");
        }
    }

    /**
     * Recupera 50 puntos de vida utilizando una poción, si hay disponibles.
     */
    public void curar() {
        if (pocionesRestantes > 0) {
            this.vida = Math.min(vida + 50, 100);
            pocionesRestantes--;
        }
    }

    /**
     * Verifica si el jugador todavía tiene pociones disponibles.
     * 
     * @return true si hay pociones restantes, false en caso contrario.
     */
    public boolean tienePociones() {
        return pocionesRestantes > 0;
    }

    /**
     * Verifica si el ataque especial "Hola Mundo" se puede usar.
     * 
     * @return true si hay usos disponibles, false si se han agotado.
     */
    public boolean puedeUsarHolaMundo() {
        return usosHolaMundo > 0;
    }

    /**
     * Aplica daño al jugador, reduciendo sus puntos de vida.
     * 
     * @param danio Cantidad de daño recibido.
     */
    public void recibirDanio(int danio) {
        this.vida -= danio;
        if (this.vida < 0) {
            this.vida = 0;
        }
    }

    // Getters y Setters para posición
    public int getPosX() { return this.x; }
    public int getPosY() { return this.y; }
    public void setPosX(int x) { this.x = x; }
    public void setPosY(int y) { this.y = y; }

    /** @return Vida actual del jugador. */
    public int getVida() { return this.vida; }

    // Control de estado de combates (peleas)
    public boolean isPelea1Activa() { return pelea1Activa; }
    public void setPelea1Activa(boolean activa) { this.pelea1Activa = activa; }

    public boolean isPelea2Activa() { return pelea2Activa; }
    public void setPelea2Activa(boolean activa) { this.pelea2Activa = activa; }

    public boolean isPelea3Activa() { return pelea3Activa; }
    public void setPelea3Activa(boolean activa) { this.pelea3Activa = activa; }
}
