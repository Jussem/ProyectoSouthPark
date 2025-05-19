package autonoma.deadlycode.models;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Interfaz que define el comportamiento para escribir datos en un archivo.
 * Proporciona un método abstracto para escribir una lista de cadenas en un
 * archivo especificado.
 *
 * @author Juan Sebastian Lopez Guzman, Cristian Camilo Salazar, Juan Jose Morales
 * @version 1.0
 * @since 2025-05-19
 */
public interface Escritor {

    /**
     * Método abstracto que escribe el contenido de una lista en un archivo en
     * la ruta especificada.
     *
     * @param archivo Lista de cadenas que se escribirán en el archivo. No debe
     * ser nulo.
     * @param ruta Ruta del archivo donde se escribirá el contenido. No debe ser
     * nulo.
     * @throws IOException Si ocurre un error durante la operación de escritura
     * del archivo.
     * @since 2025.05.05
     */
    public abstract void escribir(ArrayList<String> archivo, String ruta) throws IOException;
}
