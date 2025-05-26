package autonoma.deadlycode.models;

/**
 * Interfaz que define un contrato para ejecutar tareas en un hilo.
 * <p>
 * Las clases que implementan esta interfaz deben proporcionar una implementación
 * concreta del método {@link #run()} que contenga la lógica a ejecutar.
 * </p>
 * 
 * <p><b>Nota:</b> Esta interfaz puede coincidir con {@link java.lang.Runnable}, pero está 
 * definida localmente en el paquete {@code autonoma.pulgaslocas.elements} y no debe confundirse con la estándar de Java.</p>
 * 
 * @author Juan Sebastian Lopez Guzman, Cristian Camilo Salazar, Juan Jose Morales
 * @version 1.0
 * @since 2025-05-19
 */
public interface Runnable {

    /**
     * Ejecuta la acción definida por la clase que implementa esta interfaz.
     * <p>
     * Este método debe contener la lógica que se desea ejecutar de forma concurrente
     * o secuencial, según el uso del programa.
     * </p>
     * 
     * @since 2025-05-19
     */
    void run();
}

