package autonoma.deadlycode.exceptions;

/**
 * Excepcion que obliga al jugador esperar un turno antes de atacar
 *
 * @author Juan Sebastian Lopez Guzman, Cristian Camilo Salazar, Juan Jose
 * Morales
 * @version 1.0
 * @since 2025-05-19
 */
public class EsperarTurnoException extends RuntimeException {
    public EsperarTurnoException (){
        System.out.println("No puedes realizar ninguna accion, espera tu turno");
    }
}
