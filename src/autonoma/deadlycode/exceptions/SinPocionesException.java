/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.deadlycode.exceptions;

/**
 *
 * @author Asus
 */
public class SinPocionesException extends RuntimeException{
    public SinPocionesException(){
        System.out.println("No hay mas posiones para usar");
    }
}
