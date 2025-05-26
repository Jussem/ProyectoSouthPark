package autonoma.deadlycode.main;

import autonoma.deadlycode.gui.VentanaPrincipal;
import java.io.IOException;

/**
 * Clase principal que inicia la aplicación Deadly Code. Crea y muestra la
 * ventana principal del juego con la configuración inicial.
 *
 * @author Juan Sebastian Lopez Guzman
 * @author Cristian Camilo Salazar Arenas
 * @author Juan Jose Morales
 * @version 1.0
 * @since 2025-05-19
 */
public class DeadlyCode {

    /**
     * Punto de entrada principal para la aplicación Deadly Code. Inicializa la
     * ventana principal del juego con las siguientes características: - Tamaño
     * predeterminado de 1328x770 píxeles - Centrada en la pantalla - Configura
     * el archivo de puntajes en "puntajes.txt"
     *
     * @param args Argumentos de línea de comandos (no utilizados en esta
     * aplicación)
     */
    public static void main(String[] args) {
        try {
            String ruta = "puntajes.txt";
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setSize(1328, 770);
            ventana.setLocationRelativeTo(null); // Centra la ventana en la pantalla
            ventana.setVisible(true); // Hace visible la ventana
        } catch (IOException e) {
            // Maneja errores de E/S imprimiendo el stack trace
            e.printStackTrace();
        }
    }
}