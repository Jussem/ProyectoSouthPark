package autonoma.deadlycode.exceptions;

/**
 * Excepcion que aparece cuando el jugar gasta todas sus pociones para curarse
 *
 * @author Juan Sebastian Lopez Guzman, Cristian Camilo Salazar, Juan Jose
 * Morales
 * @version 1.0
 * @since 2025-05-19
 */
public class SinPocionesException extends RuntimeException{
    public SinPocionesException(){
        System.out.println("No hay mas posiones para usar");
    }
}
