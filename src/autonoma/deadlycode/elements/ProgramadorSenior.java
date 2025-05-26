package autonoma.deadlycode.elements;

import java.awt.Color;

/**
 * Representa un enemigo Programador Senior en el campo de batalla.
 * Posee mayor resistencia y daño que un Junior, pero menos capacidad de curación.
 * 
 * @author Juan Sebastian Lopez Guzman
 * @author Cristian Camilo Salazar Arenas
 * @author Juan Jose Morales
 * @version 1.0
 * @since 2025-05-19
 */
public class ProgramadorSenior extends Personaje {
    /**
     * Crea un nuevo Programador Senior con posición, dimensiones y color específicos.
     * Inicializa con 150 puntos de vida y 2 pociones de curación.
     * 
     * @param x Posición horizontal inicial
     * @param y Posición vertical inicial
     * @param width Ancho del personaje
     * @param height Alto del personaje
     * @param color Color representativo del personaje
     */
    public ProgramadorSenior(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        this.vida = 150;
        this.pocionesRestantes = 2;
    }
    /**
     * Ejecuta el ataque especial "Bugs" contra un objetivo,
     * infligiendo 25 puntos de daño.
     * Representa los problemas complejos que genera un programador experimentado.
     * 
     * @param objetivo Personaje que recibirá el daño
     */
    public void bugs(Personaje objetivo) {
        int danio = 20;
        objetivo.recibirDanio(danio);
    }
}
