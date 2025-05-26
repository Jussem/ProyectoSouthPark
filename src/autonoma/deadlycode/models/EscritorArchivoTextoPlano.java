package autonoma.deadlycode.models;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Implementación concreta de la interfaz Escritor para escribir en archivos de
 * texto plano. Esta clase permite escribir contenido en un archivo de texto
 * utilizando una codificación de caracteres específica (UTF-8 por defecto).
 *
 * @author Juan Sebastian Lopez Guzman, Cristian Camilo Salazar, Juan Jose Morales
 * @version 1.0
 * @since 2025-05-19
 */
public class EscritorArchivoTextoPlano implements Escritor {

    /**
     * Codificación de caracteres utilizada para escribir el archivo (UTF-8 por
     * defecto).
     *
     * @since 2025-05-19
     */
    private Charset charset = Charset.forName("UTF-8");

    /**
     * Ruta del archivo donde se escribirá el contenido.
     *
     * @since 2025-05-19
     */
    private String filePath;

    /**
     * Constructor que inicializa la ruta del archivo destino.
     *
     * @param filePath Ruta del archivo donde se escribirá el contenido.
     * @since 2025-05-19
     */
    public EscritorArchivoTextoPlano(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Implementación del método escribir que guarda el contenido de una lista
     * en un archivo de texto, línea por línea.
     *
     * @param archivo Lista de cadenas que se escribirán en el archivo.
     * @param ruta Ruta del archivo destino donde se escribirá el contenido.
     * @throws IOException Si ocurre un error al escribir el archivo (permisos,
     * ruta inválida, etc.).
     * @since 2025-05-19
     */
    @Override
    public void escribir(ArrayList<String> archivo, String ruta) throws IOException {
        File fichero = new File(ruta);
        FileWriter writer = new FileWriter(fichero, false);
        PrintWriter pw = new PrintWriter(writer);

        for (String linea : archivo) {
            pw.println(linea);
        }

        pw.close();
        writer.close();
    }
}

