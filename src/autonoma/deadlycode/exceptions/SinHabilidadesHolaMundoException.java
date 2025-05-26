package autonoma.deadlycode.exceptions;

/**
 * Excepcion que no le permite al jugador atacar con más HolaMundos
 *
 * @author Juan Sebastian Lopez Guzman, Cristian Camilo Salazar, Juan Jose
 * Morales
 * @version 1.0
 * @since 2025-05-19
 */
public class SinHabilidadesHolaMundoException extends RuntimeException{
    public SinHabilidadesHolaMundoException(){
        System.out.println("No tienes mas habilidades HolaMundo");
    }
}
