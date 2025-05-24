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

    protected int vida=100;
    
    
    public Personaje(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    public void atacar(){
        
    }
    public void curar(){
        if (this.vida<100){
            this.vida+=50;
            if(this.vida>100){
                this.vida=100;
            }
        }
        
    }
    public void HolaMundo(){
        
    }
}
