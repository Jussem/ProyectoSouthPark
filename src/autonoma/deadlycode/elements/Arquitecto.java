package autonoma.deadlycode.elements;

import java.awt.Color;

/**
 *
 * @author Asus
 */
public class Arquitecto extends Personaje {
    
    public Arquitecto(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        this.vida = 200;
        this.pocionesRestantes = 1;
    }

    public void arbolRojinegro(Personaje objetivo) {
        objetivo.recibirDanio(40);
    }
}
