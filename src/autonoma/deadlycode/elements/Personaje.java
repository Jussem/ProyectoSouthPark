/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.deadlycode.elements;

import java.awt.Color;

/**
 *
 * @author Asus
 */
public abstract class Personaje extends Sprite{

    protected int vida;
    protected int pocionesRestantes;
    
    
    public Personaje(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    public void recibirDanio(int danio) {
        this.vida = Math.max(vida - danio, 0);
    }
    public void curar() {
        if (pocionesRestantes > 0) {
            this.vida = Math.min(vida + 50, 100);
            pocionesRestantes--;
        }
    }
}
