package autonoma.deadlycode.elements;

import java.awt.Color;

/**
 *
 * @author Asus
 */
public class ProgramadorSenior extends Personaje {
    
    public ProgramadorSenior(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        this.vida = 150;
        this.pocionesRestantes = 2;
    }

    public void bugs(Personaje objetivo) {
        objetivo.recibirDanio(25);
    }
}
