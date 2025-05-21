/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.deadlycode.exceptions;

/**
 *
 * @author Asus
 */
public class EsperarTurnoException extends RuntimeException {
    public EsperarTurnoException (){
        System.out.println("No puedes realizar ninguna accion, espera tu turno");
    }
}
