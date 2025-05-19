package autonoma.deadlycode.elements;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Clase base abstracta para representar elementos gráficos del juego,
 * como el Soldado y las Pulgas. Gestiona la posición, tamaño y color,
 * y ofrece métodos de dibujo y detección de colisiones.
 * 
 * @author Juan Sebastian Lopez Guzman, Cristian Camilo Salazar, Juan Jose Morales
 * @version 1.0
 * @since 2025-05-19
 */
public abstract class Sprite {
    /** Posición horizontal del sprite en el campo. */
    protected int x;

    /** Posición vertical del sprite en el campo. */
    protected int y;

    /** Ancho del sprite. */
    protected int width;

    /** Alto del sprite. */
    protected int height;

    /** Color con el que se dibuja el sprite. */
    protected Color color;

    /**
     * Crea un nuevo sprite con su posición, tamaño y color inicial.
     *
     * @param x Posición X del sprite
     * @param y Posición Y del sprite
     * @param width Ancho del sprite
     * @param height Alto del sprite
     * @param color Color del sprite
     */
    public Sprite(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Dibuja el sprite como un rectángulo relleno en el contexto gráfico.
     *
     * @param g Objeto Graphics usado para dibujar
     */
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    /**
     * Determina si este sprite colisiona con otro rectángulo dado.
     *
     * @param x Posición X del rectángulo a verificar
     * @param y Posición Y del rectángulo a verificar
     * @param width Ancho del rectángulo
     * @param height Alto del rectángulo
     * @return true si hay intersección entre ambos rectángulos
     */
    public boolean hit(int x, int y, int width, int height) {
        return this.x < x + width &&
               this.x + this.width > x &&
               this.y < y + height &&
               this.y + this.height > y;
    }

    // Métodos getter y setter

    /** @return la posición X del sprite. */
    public int getX() {
        return x;
    }

    /** @param x Nueva posición X del sprite. */
    public void setX(int x) {
        this.x = x;
    }

    /** @return la posición Y del sprite. */
    public int getY() {
        return y;
    }

    /** @param y Nueva posición Y del sprite. */
    public void setY(int y) {
        this.y = y;
    }

    /** @return el ancho del sprite. */
    public int getWidth() {
        return width;
    }

    /** @param width Nuevo ancho del sprite. */
    public void setWidth(int width) {
        this.width = width;
    }

    /** @return el alto del sprite. */
    public int getHeight() {
        return height;
    }

    /** @param height Nuevo alto del sprite. */
    public void setHeight(int height) {
        this.height = height;
    }

    /** @return el color del sprite. */
    public Color getColor() {
        return color;
    }

    /** @param color Nuevo color del sprite. */
    public void setColor(Color color) {
        this.color = color;
    }
}

