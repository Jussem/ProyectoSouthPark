package autonoma.deadlycode.elements;

import java.awt.Color;

/**
 *
 * @author Asus
 */
public class ProgramadorJunior extends Personaje {
    
    public ProgramadorJunior(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        this.vida = 100;
        this.pocionesRestantes = 3;
    }

    public void documentacionJava(Personaje objetivo) {
        objetivo.recibirDanio(15);
    }
}