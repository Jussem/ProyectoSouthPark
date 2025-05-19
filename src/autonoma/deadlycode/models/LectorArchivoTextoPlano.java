package autonoma.deadlycode.models;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Implementación concreta de la interfaz Lector para leer archivos de texto
 * plano. Esta clase permite leer el contenido de un archivo de texto línea por
 * línea utilizando una codificación de caracteres específica (UTF-8 por
 * defecto).
 *
 * @author Juan Sebastian Lopez Guzman, Cristian Camilo Salazar, Juan Jose Morales
 * @version 1.0
 * @since 2025-05-19
 */
public class LectorArchivoTextoPlano implements Lector {

    /**
     * Lista que almacena las líneas del archivo leído.
     *
     * @since 2025.05.05
     */
    private ArrayList<String> archivo;

    /**
     * Codificación de caracteres utilizada para leer el archivo (UTF-8 por
     * defecto).
     *
     * @since 2025.05.05
     */
    private Charset charset = Charset.forName("UTF-8");

    /**
     * Constructor que inicializa la lista para almacenar las líneas del
     * archivo.
     *
     * @since 2025.05.05
     */
    public LectorArchivoTextoPlano() {
        this.archivo = new ArrayList<>();
    }

    /**
     * Implementación del método leer que carga el contenido de un archivo de
     * texto línea por línea en una lista de cadenas.
     *
     * @param localizacionArchivo Ruta del archivo que se va a leer.
     * @return ArrayList<String> Lista con todas las líneas del archivo.
     * @throws IOException Si ocurre un error al leer el archivo (no existe, no
     * se puede acceder, etc.).
     * @since 2025.05.05
     */
    @Override
    public ArrayList<String> leer(String localizacionArchivo) throws IOException {
        archivo.clear();
        File file = new File(localizacionArchivo);
        FileReader reader = new FileReader(file, charset);
        BufferedReader buffer = new BufferedReader(reader);

        String linea;
        while ((linea = buffer.readLine()) != null) {
            archivo.add(linea);
        }

        reader.close();
        return archivo;
    }
}