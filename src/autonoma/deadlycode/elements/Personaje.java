package autonoma.deadlycode.elements;

import java.awt.Color;

/**
 * Clase abstracta que representa un personaje genérico en el juego.
 * Define los atributos y comportamientos comunes para todos los personajes
 * (jugador y enemigos) como vida, curación y recepción de daño.
 * 
 * @author Juan Sebastian Lopez Guzman
 * @author Cristian Camilo Salazar Arenas
 * @author Juan Jose Morales
 * @version 1.0
 * @since 2025-05-19
 */
public abstract class Personaje extends Sprite{
 /** Puntos de vida actuales del personaje */
    protected int vida;
    
    /** Cantidad de pociones de curación disponibles */
    protected int pocionesRestantes;

    /**
     * Crea un nuevo personaje con posición, dimensiones y color específicos.
     * 
     * @param x Posición horizontal inicial
     * @param y Posición vertical inicial
     * @param width Ancho del personaje
     * @param height Alto del personaje
     * @param color Color representativo del personaje
     */
    public Personaje(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }
     /**
     * Reduce los puntos de vida del personaje según el daño recibido.
     * La vida nunca será inferior a 0.
     * 
     * @param danio Cantidad de puntos de vida a restar
     */
    public void recibirDanio(int danio) {
        this.vida = Math.max(vida - danio, 0);
        
    }
    /**
     * Aumenta los puntos de vida del personaje usando una poción de curación.
     * Cada poción recupera 50 puntos de vida (hasta un máximo de 100)
     * y consume una unidad de las pociones disponibles.
     */
    public void curar() {
        if (pocionesRestantes > 0) {
            this.vida = Math.min(vida + 50, 100);
            pocionesRestantes--;
        }
    }
    public int getVida() {
        return this.vida;
    }
}
