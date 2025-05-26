package autonoma.deadlycode.models;

//Importamos las clases necesarias
import java.io.IOException;
import java.util.ArrayList;

/**
 * Interfaz que define el comportamiento para leer datos de un archivo.
 * Proporciona un método abstracto para leer el contenido de un archivo y
 * devolverlo como lista de cadenas.
 *
 * @author Juan Sebastian Lopez Guzman, Cristian Camilo Salazar, Juan Jose Morales
 * @version 1.0
 * @since 2025-05-19
 */
public interface Lector {

    /**
     * Método abstracto que lee el contenido de un archivo y lo devuelve como
     * lista de cadenas.
     *
     * @param localizacionArchivo Ruta del archivo que se va a leer. No debe ser
     * nulo.
     * @return ArrayList<String> Lista de cadenas con el contenido del archivo
     * leído.
     * @throws IOException Si ocurre un error durante la operación de lectura
     * del archivo.
     * @since 2025-05-19
     */
    public abstract ArrayList<String> leer(String localizacionArchivo) throws IOException;
}