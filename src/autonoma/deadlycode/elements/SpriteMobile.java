package autonoma.deadlycode.elements;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Representa un objeto móvil dentro del entorno del juego.
 * <p>
 * Esta clase extiende a {@link Sprite} y añade capacidades de movimiento
 * mediante velocidades en los ejes X e Y. Se utiliza como clase base para
 * elementos móviles como las pulgas.
 * </p>
 * 
 * @author Juan Sebastian Lopez Guzman, Cristian Camilo Salazar, Juan Jose Morales
 * @version 1.0
 * @since 2025-05-19
 */
public class SpriteMobile extends Sprite {
    
    /**
     * Velocidad del objeto en el eje X.
     * 
     * @since 1.0
     */
    protected int velocidadX;

    /**
     * Velocidad del objeto en el eje Y.
     * 
     * @since 1.0
     */
    protected int velocidadY;
    
    /**
     * Crea un objeto SpriteMobile con posición, tamaño y color especificados.
     *
     * @param x Coordenada X inicial.
     * @param y Coordenada Y inicial.
     * @param width Ancho del sprite.
     * @param height Alto del sprite.
     * @param color Color del sprite.
     * @since 1.0
     */
    public SpriteMobile(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    /**
     * Imprime la trayectoria del sprite en la salida estándar.
     * <p>
     * Este método puede ser sobrescrito por clases hijas para definir un comportamiento
     * más específico de movimiento o trayecto.
     * </p>
     *
     * @since 1.0
     */
    public void trayectoria() {
        System.out.println("trayectoria del sprite.");
    }

    /**
     * Método que debe ser sobrescrito para dibujar el sprite móvil.
     * Actualmente no implementado.
     *
     * @param g El contexto gráfico donde se dibujará el sprite.
     * @throws UnsupportedOperationException Indica que el método no está aún soportado.
     * @since 1.0
     */
    @Override
    public void draw(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

