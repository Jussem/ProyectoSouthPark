package autonoma.deadlycode.elements;

import autonoma.deadlycode.models.Escritor;
import autonoma.deadlycode.models.EscritorArchivoTextoPlano;
import autonoma.deadlycode.models.Lector;
import autonoma.deadlycode.models.LectorArchivoTextoPlano;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_Q;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Asus
 */
public class CampoDeBatalla {
    // Archivo donde se guardan los puntajes
    private File archivoPuntajes;
    
    // Objetos para lectura y escritura de archivos
    private final Lector lector;
    private final Escritor escritor;
    // Dimensiones máximas del campo
    private int maxX;
    private int maxY;
    // Puntaje actual del jugador
    private int puntaje;
    //Jugador que se mueve en el campo de batalla
    private JugadorCartman jugador;
    public CampoDeBatalla(String rutaArchivo) throws IOException {
        this.archivoPuntajes = new File(rutaArchivo);
        if (!this.archivoPuntajes.exists()) {
            this.archivoPuntajes.createNewFile();
        }
        this.lector = new LectorArchivoTextoPlano();
        this.escritor = new EscritorArchivoTextoPlano(rutaArchivo);
//        this.pulgasNormales = new ArrayList<>();
//        this.pulgasMutantes = new ArrayList<>();
        this.maxX = 700;
        this.maxY = 500;
//      this.puntaje = 0;
    }
    public void setBounds(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;

        // Actualizar límites del jugador
        if (this.jugador != null) {
            this.jugador.inicializarLimites(maxX, maxY);
        }

        // Si tienes las listas de enemigos, actualiza sus límites también
        /*
    for (PulgaNormal pulga : pulgasNormales) {
        pulga.inicializarLimites(maxX, maxY);
    }
    for (PulgaMutante pulga : pulgasMutantes) {
        pulga.inicializarLimites(maxX, maxY);
    }
         */
    }
    public void handleKey(KeyEvent e)throws IOException{
        switch (e.getKeyCode()) {
            case VK_Q:
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                if (jugador != null) {
                    jugador.mover(e);
                }
                break;
            
        }
    }
    public void terminarSimulacion(KeyEvent e) throws IOException {
        if (e.getKeyCode() == KeyEvent.VK_Q) {
//          detenerPulgas();
            guardarPuntaje(puntaje);
            this.puntaje = 0;
        }
    }
    public void guardarPuntaje(int nuevoPuntaje) throws IOException {
        ArrayList<String> lineas = lector.leer(archivoPuntajes.getPath());
        ArrayList<Integer> puntajes = new ArrayList<>();

        for (String linea : lineas) {
            if (!linea.isBlank()) {
                try {
                    puntajes.add(Integer.parseInt(linea.trim()));
                } catch (NumberFormatException e) {
                    // Ignorar
                }
            }
        }

        puntajes.add(nuevoPuntaje);

        while (puntajes.size() > 10) {
            puntajes.remove(0);
        }

        ArrayList<String> nuevasLineas = new ArrayList<>();
        for (int p : puntajes) {
            nuevasLineas.add(String.valueOf(p));
        }

        escritor.escribir(nuevasLineas, archivoPuntajes.getPath());
    }
    public ArrayList<Integer> leerPuntajes() throws IOException {
        ArrayList<String> lineas = lector.leer(archivoPuntajes.getPath());
        ArrayList<Integer> puntajes = new ArrayList<>();

        for (String linea : lineas) {
            if (!linea.isBlank()) {
                try {
                    puntajes.add(Integer.parseInt(linea.trim()));
                } catch (NumberFormatException e) {
                    // Ignorar
                }
            }
        }

        Collections.sort(puntajes, Collections.reverseOrder());
        return puntajes;
    }
    public void aumentarPuntaje(int puntos) { 
        puntaje += puntos; 
    }
}
