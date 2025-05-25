package autonoma.deadlycode.elements;

import java.awt.Color;

/**
 * Representa un enemigo Arquitecto en el campo de batalla, con alta resistencia
 * y poderosos ataques basados en estructuras de datos complejas.
 * 
 * @author Juan Sebastian Lopez Guzman
 * @author Cristian Camilo Salazar Arenas
 * @author Juan Jose Morales
 * @version 1.0
 * @since 2023
 */
public class Arquitecto extends Personaje {
    /**
     * Crea un nuevo Arquitecto con posición, tamaño y color específicos.
     * El Arquitecto tiene mayor vida inicial (200) pero menos pociones (1) que otros enemigos.
     * 
     * @param x Posición horizontal inicial
     * @param y Posición vertical inicial
     * @param width Ancho del personaje
     * @param height Alto del personaje
     * @param color Color representativo del personaje
     */
    public Arquitecto(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        this.vida = 200;
        this.pocionesRestantes = 1;
    }
    /**
     * Ejecuta el ataque especial "Árbol RojiNegro" contra un objetivo,
     * infligiendo un alto daño (40 puntos).
     * 
     * @param objetivo Personaje que recibirá el daño del ataque
     */
    public void arbolRojinegro(Personaje objetivo) {
        int danio = 50;
        objetivo.recibirDanio(danio);
    }
}
