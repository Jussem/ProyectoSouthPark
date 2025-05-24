package autonoma.deadlycode.elements;

import java.awt.Color;

/**
 * Representa un enemigo Programador Junior en el campo de batalla.
 * Tiene características de combate básicas pero puede curarse múltiples veces.
 * 
 * @author Juan Sebastian Lopez Guzman
 * @author Cristian Camilo Salazar Arenas
 * @author Juan Jose Morales
 * @version 1.0
 * @since 2023
 */
public class ProgramadorJunior extends Personaje {
    
    /**
     * Crea un nuevo Programador Junior con posición, dimensiones y color específicos.
     * Inicializa con 100 puntos de vida y 3 pociones de curación.
     * 
     * @param x Posición horizontal inicial
     * @param y Posición vertical inicial
     * @param width Ancho del personaje
     * @param height Alto del personaje
     * @param color Color representativo del personaje
     */
    public ProgramadorJunior(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        this.vida = 100;
        this.pocionesRestantes = 3;
    }
    /**
     * Realiza el ataque especial "Documentación Java" contra un objetivo,
     * infligiendo 15 puntos de daño.
     * 
     * @param objetivo Personaje que recibirá el daño
     */
    public void documentacionJava(Personaje objetivo) {
        objetivo.recibirDanio(15);
    }
}