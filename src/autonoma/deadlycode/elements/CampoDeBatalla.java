package autonoma.deadlycode.elements;

import autonoma.deadlycode.models.Escritor;
import autonoma.deadlycode.models.EscritorArchivoTextoPlano;
import autonoma.deadlycode.models.Lector;
import autonoma.deadlycode.models.LectorArchivoTextoPlano;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Representa el área principal de juego donde interactúan el jugador y los enemigos.
 * Gestiona los límites del escenario, el control de teclado, y el sistema de puntuación.
 * 
 * @author Juan Sebastian Lopez Guzman
 * @author Cristian Camilo Salazar Arenas
 * @author Juan Jose Morales
 * @version 1.0
 * @since 2025-05-19
 */
public class CampoDeBatalla {
        
    /** Archivo donde se persisten los puntajes históricos */
    private File archivoPuntajes;
    
    /** Componente para lectura de archivos */
    private final Lector lector;
    
    /** Componente para escritura de archivos */
    private final Escritor escritor;
    
    /** Límite horizontal del campo */
    private int maxX;
    
    /** Límite vertical del campo */
    private int maxY;
    
    /** Puntaje acumulado en la partida actual */
    private int puntaje;
    
    /** Referencia al jugador principal */
    private JugadorCartman jugador;
    
    /**
     * Crea un nuevo campo de batalla con archivo de persistencia de puntajes.
     * Inicializa las dimensiones predeterminadas (1156x500) y puntaje en cero.
     * 
     * @param rutaArchivo Ruta del archivo para guardar los puntajes
     * @throws IOException Si ocurre un error al crear/leer el archivo
     */
    public CampoDeBatalla(String rutaArchivo) throws IOException {
        this.archivoPuntajes = new File(rutaArchivo);
        if (!this.archivoPuntajes.exists()) {
            this.archivoPuntajes.createNewFile();
        }
        this.lector = new LectorArchivoTextoPlano();
        this.escritor = new EscritorArchivoTextoPlano(rutaArchivo);

        this.maxX = 1156;
        this.maxY = 500;
        this.puntaje = 0;
        this.jugador = new JugadorCartman(32, 130, 120, 170, Color.RED);
        this.jugador.inicializarLimites(this.maxX, this.maxY);
    }

    /**
     * Establece los nuevos límites del campo y actualiza los límites
     * de todos los elementos contenidos.
     * 
     * @param maxX Nuevo límite horizontal
     * @param maxY Nuevo límite vertical
     */
    public void setBounds(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;

        // Actualizar límites del jugador
        if (this.jugador != null) {
            this.jugador.inicializarLimites(maxX, maxY);
        }
    }

    /**
     * Procesa los eventos de teclado para controlar al jugador:
     * - Movimiento (teclas direccionales)
     * - Curación (tecla H)
     * 
     * @param e Evento de tecla presionada
     * @throws IOException Si falla la persistencia al terminar
     */
    public void handleKey(KeyEvent e) throws IOException {
        if (jugador == null) {
            return;
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                jugador.mover(e);
                break;
            case KeyEvent.VK_H: 
                if (jugador.tienePociones()) {
                    jugador.curar();
                }
                break;
        }
    }

    /**
     * Finaliza la partida actual guardando el puntaje y reiniciando
     * el contador para una nueva partida.
     * 
     * @param e Evento de tecla que activó la terminación
     * @throws IOException Si falla la persistencia del puntaje
     */
    public void terminarSimulacion(KeyEvent e) throws IOException {
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            guardarPuntaje(puntaje);
            this.puntaje = 0;
        }
    }

    /**
     * Persiste un nuevo puntaje en el archivo histórico, manteniendo solo los
     * 10 mejores puntajes ordenados de mayor a menor.
     *
     * @param nuevoPuntaje Puntaje a guardar
     * @throws IOException Si falla la operación de archivo
     */
    public void guardarPuntaje(int nuevoPuntaje) throws IOException {
        ArrayList<String> lineas = lector.leer(archivoPuntajes.getPath());
        ArrayList<Integer> puntajes = new ArrayList<>();

        // Leer puntajes existentes
        for (String linea : lineas) {
            if (!linea.isBlank()) {
                try {
                    puntajes.add(Integer.parseInt(linea.trim()));
                } catch (NumberFormatException e) {
                }
            }
        }

        // Agregar nuevo puntaje y ordenar
        puntajes.add(nuevoPuntaje);
        Collections.sort(puntajes, Collections.reverseOrder());

        // Mantener solo los 10 mejores
        while (puntajes.size() > 10) {
            puntajes.remove(puntajes.size() - 1); // Eliminar el menor
        }

        // Escribir al archivo
        ArrayList<String> nuevasLineas = new ArrayList<>();
        for (int p : puntajes) {
            nuevasLineas.add(String.valueOf(p));
        }
        escritor.escribir(nuevasLineas, archivoPuntajes.getPath());
    }

    /**
     * Recupera los puntajes históricos ordenados descendentemente.
     * 
     * @return Lista de los mejores puntajes
     * @throws IOException Si falla la lectura del archivo
     */
    public ArrayList<Integer> leerPuntajes() throws IOException {
        ArrayList<String> lineas = lector.leer(archivoPuntajes.getPath());
        ArrayList<Integer> puntajes = new ArrayList<>();

        for (String linea : lineas) {
            if (!linea.isBlank()) {
                try {
                    puntajes.add(Integer.parseInt(linea.trim()));
                } catch (NumberFormatException e) {
                    // Ignorar líneas inválidas
                }
            }
        }

        Collections.sort(puntajes, Collections.reverseOrder());
        return puntajes;
    }

    /**
     * Incrementa el puntaje actual de la partida.
     * 
     * @param puntos Cantidad a sumar al puntaje
     */
    public void aumentarPuntaje(int puntos) {
        puntaje += puntos;
    }

    /**
     * Retorna la instancia actual del jugador principal.
     *
     * @return JugadorCartman actual en el campo de batalla
     */
    public JugadorCartman getJugador() {
        return this.jugador;
    }

    /**
     * Establece una nueva instancia del jugador y actualiza sus límites
     * al tamaño actual del campo de batalla.
     *
     * @param jugador Nueva instancia de JugadorCartman a usar en el campo
     */
    public void setJugador(JugadorCartman jugador) {
        this.jugador = jugador;
        this.jugador.inicializarLimites(this.maxX, this.maxY); // para respetar los límites
    }
}
