package autonoma.deadlycode.gui;

import autonoma.deadlycode.elements.CampoDeBatalla;
import autonoma.deadlycode.elements.JugadorCartman;
import autonoma.deadlycode.elements.Personaje;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * Ventana del mundo principal del juego, donde el jugador se desplaza e interactúa
 * con los enemigos antes de entrar en batalla.
 * Controla la música de fondo, la posición del jugador, las peleas activas
 * y efectos visuales.
 * 
 * Extiende de {@code javax.swing.JDialog}.
 * 
 * @author Juan Sebastian Lopez Guzman
 * @author Cristian Camilo Salazar Arenas
 * @author Juan Jose Morales
 * @version 1.0
 * @since 2025-05-19
 */
public class VentanaMundo extends javax.swing.JDialog {

    /** Clip de sonido que representa la música de fondo del mundo */
    private Clip musicaFondo;

    /** Campo de batalla que contiene al jugador y gestiona el estado del juego */
    CampoDeBatalla campo;

    /** Lista de enemigos presentes en el mundo */
    private List<Personaje> enemigos;

    /** Estado de la primera pelea */
    private boolean pelea1Activa = true;

    /** Estado de la segunda pelea */
    private boolean pelea2Activa = true;

    /** Estado de la tercera pelea */
    private boolean pelea3Activa = true;
    
    /** Última posición del jugar en el eje x*/
    private int ultimaPosX;
    
    /** Última posición del jugar en el eje y */
    private int ultimaPosY;

    /**
     * Constructor de la ventana del mundo. Inicializa el campo de batalla,
     * configura la música de fondo, recupera el estado de las peleas del jugador
     * y ajusta la interfaz gráfica.
     * 
     * @param parent Ventana padre
     * @param modal Indica si la ventana es modal
     * @param jugador Instancia del jugador a posicionar en el mundo
     */
    public VentanaMundo(java.awt.Frame parent, boolean modal, JugadorCartman jugador) {
        super(parent, modal);
        try {
            String rutaArchivo = "src/autonoma/deadlycode/models/puntajes.txt";
            this.campo = new CampoDeBatalla(rutaArchivo);
            this.campo.setJugador(jugador);
        } catch (IOException ex) {
            Logger.getLogger(VentanaMundo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al crear/cargar el archivo de puntajes",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        initComponents();
        iniciarMusicaFondo();

        // Sincroniza el estado de las peleas con el jugador
        this.pelea1Activa = jugador.isPelea1Activa();
        this.pelea2Activa = jugador.isPelea2Activa();
        this.pelea3Activa = jugador.isPelea3Activa();

        lblJugador.setFocusable(false);
        lblJugador.removeKeyListener(lblJugador.getKeyListeners()[0]);
        pnlJugador.setFocusable(true);
        pnlJugador.requestFocusInWindow();

        // Posicionar al jugador en su última posición o inicial si es nuevo
        pnlJugador.setLocation(jugador.getPosX(), jugador.getPosY());

        // Configura visibilidad de enemigos
        lblPelea1.setVisible(pelea1Activa);
        lblPelea2.setVisible(pelea2Activa);
        lblPelea3.setVisible(pelea3Activa);

        iniciarAnimacionParpadeo();
    }
    
    /**
     * Constructor alternativo que permite especificar la posición inicial del
     * jugador.
     *
     * @param parent Ventana padre
     * @param modal Indica si la ventana es modal
     * @param jugador Instancia del jugador a posicionar en el mundo
     * @param posX Posición X donde colocar al jugador
     * @param posY Posición Y donde colocar al jugador
     */
    public VentanaMundo(java.awt.Frame parent, boolean modal, JugadorCartman jugador, int posX, int posY) {
        super(parent, modal);
        try {
            String rutaArchivo = "src/autonoma/deadlycode/models/puntajes.txt";
            this.campo = new CampoDeBatalla(rutaArchivo);
            this.campo.setJugador(jugador);
        } catch (IOException ex) {
            Logger.getLogger(VentanaMundo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al crear/cargar el archivo de puntajes",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        initComponents();
        iniciarMusicaFondo();

        // Sincroniza el estado de las peleas con el jugador
        this.pelea1Activa = jugador.isPelea1Activa();
        this.pelea2Activa = jugador.isPelea2Activa();
        this.pelea3Activa = jugador.isPelea3Activa();

        lblJugador.setFocusable(false);
        lblJugador.removeKeyListener(lblJugador.getKeyListeners()[0]);
        pnlJugador.setFocusable(true);
        pnlJugador.requestFocusInWindow();

        // CLAVE: Usar las coordenadas especificadas para el PANEL
        pnlJugador.setLocation(posX, posY);
        // Y también actualizar el objeto jugador por consistencia
        jugador.setPosX(posX);
        jugador.setPosY(posY);

        // Configura visibilidad de enemigos
        lblPelea1.setVisible(pelea1Activa);
        lblPelea2.setVisible(pelea2Activa);
        lblPelea3.setVisible(pelea3Activa);

        iniciarAnimacionParpadeo();
    }

    /**
     * Verifica si el jugador se encuentra cerca de un enemigo
     * y si dicha pelea está activa.
     * 
     * @param enemigo JLabel que representa visualmente al enemigo
     * @return {@code true} si el jugador está dentro del rango de colisión con el enemigo
     */
    private boolean estaSobreEnemigo(javax.swing.JLabel enemigo) {
        if ((enemigo == lblPelea1 && !pelea1Activa) ||
            (enemigo == lblPelea2 && !pelea2Activa) ||
            (enemigo == lblPelea3 && !pelea3Activa)) {
            return false;
        }

        int jugadorX = pnlJugador.getX() + pnlJugador.getWidth() / 2;
        int jugadorY = pnlJugador.getY() + pnlJugador.getHeight() / 2;
        int enemigoX = enemigo.getX() + enemigo.getWidth() / 2;
        int enemigoY = enemigo.getY() + enemigo.getHeight() / 2;
        int rango = 50;

        return Math.abs(jugadorX - enemigoX) < rango && Math.abs(jugadorY - enemigoY) < rango;
    }

    /**
     * Inicia la música de fondo del mundo cargando el archivo de sonido
     * y reproduciéndolo en bucle con volumen reducido.
     */
    private void iniciarMusicaFondo() {
        try {
            InputStream audioStream = getClass().getResourceAsStream("/autonoma/deadlycode/sounds/musica_fondo_2.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(audioStream);
            musicaFondo = AudioSystem.getClip();
            musicaFondo.open(ais);

            // Control de volumen
            FloatControl gainControl = (FloatControl) musicaFondo.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f); // Volumen reducido

            musicaFondo.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.err.println("Error al cargar música de fondo: " + e.getMessage());
        }
    }

    /**
     * Detiene y libera los recursos del clip de música si está en reproducción.
     */
    private void detenerMusica() {
        if (musicaFondo != null && musicaFondo.isRunning()) {
            musicaFondo.stop();
            musicaFondo.close();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlJugador = new javax.swing.JPanel();
        lblJugador = new javax.swing.JLabel();
        lblPelea1 = new javax.swing.JLabel();
        lblPelea2 = new javax.swing.JLabel();
        lblPelea3 = new javax.swing.JLabel();
        lblCampo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlJugador.setOpaque(false);
        pnlJugador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pnlJugadorKeyPressed(evt);
            }
        });

        lblJugador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autonoma/deadlycode/images/Cartman.png"))); // NOI18N
        lblJugador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblJugadorKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout pnlJugadorLayout = new javax.swing.GroupLayout(pnlJugador);
        pnlJugador.setLayout(pnlJugadorLayout);
        pnlJugadorLayout.setHorizontalGroup(
            pnlJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJugadorLayout.createSequentialGroup()
                .addComponent(lblJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlJugadorLayout.setVerticalGroup(
            pnlJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlJugadorLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(pnlJugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 90, -1));

        lblPelea1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        lblPelea1.setForeground(new java.awt.Color(255, 0, 0));
        lblPelea1.setText("Iniciar pelea 'T'");
        jPanel1.add(lblPelea1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, -1, -1));

        lblPelea2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        lblPelea2.setForeground(new java.awt.Color(255, 0, 0));
        lblPelea2.setText("Iniciar pelea 'T'");
        jPanel1.add(lblPelea2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, -1, -1));

        lblPelea3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        lblPelea3.setForeground(new java.awt.Color(255, 0, 0));
        lblPelea3.setText("Iniciar pelea 'T'");
        jPanel1.add(lblPelea3, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 160, -1, -1));

        lblCampo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autonoma/deadlycode/images/casasJuego.jpg"))); // NOI18N
        jPanel1.add(lblCampo, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, 258));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Evento que maneja la pulsación de teclas sobre el componente lblJugador.
     * Se utiliza principalmente para mover al jugador hacia la izquierda o derecha,
     * o salir del juego presionando la tecla 'Q'.
     *
     * @param evt Evento de teclado generado
     */
    private void lblJugadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblJugadorKeyPressed
        try {
            switch (evt.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_RIGHT:
                    campo.handleKey(evt); 
                    pnlJugador.setLocation(
                            campo.getJugador().getPosX(),
                            campo.getJugador().getPosY()
                    );
                    break;

                case KeyEvent.VK_Q:
                    exitGame(); // Cierra el juego al presionar Q
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(VentanaMundo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblJugadorKeyPressed
        /**
     * Evento que maneja la pulsación de teclas sobre el panel del jugador.
     * Permite mover al jugador, iniciar pelea con 'T', o salir con 'Q'.
     * También actualiza el feedback visual al moverse.
     *
     * @param evt Evento de teclado generado
     */
    private void pnlJugadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnlJugadorKeyPressed
        try {
            switch (evt.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_RIGHT:
                    campo.handleKey(evt);
                    pnlJugador.setLocation(campo.getJugador().getPosX(), campo.getJugador().getPosY());
                    actualizarFeedback();
                    break;
                case KeyEvent.VK_Q:
                    exitGame();
                    break;
                case KeyEvent.VK_T:
                    if (estaSobreEnemigo(lblPelea1) || estaSobreEnemigo(lblPelea2) || estaSobreEnemigo(lblPelea3)) {
                        iniciarPelea();
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "No estás sobre el enemigo",
                                "Información",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;

            }
        } catch (IOException ex) {
            Logger.getLogger(VentanaMundo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pnlJugadorKeyPressed
    /**
     * Inicia una pelea si el jugador está sobre un enemigo y dicha pelea está habilitada.
     * Las peleas se deben completar en orden (1 → 2 → 3).
     * Cierra esta ventana, inicia la pelea, y luego vuelve al mundo.
     */
    private void iniciarPelea() {
        // Guardar posición actual del PANEL antes de la pelea (esta es la clave)
        this.ultimaPosX = pnlJugador.getX();
        this.ultimaPosY = pnlJugador.getY();

        // IMPORTANTE: También actualizar la posición en el objeto jugador
        JugadorCartman jugador = campo.getJugador();
        jugador.setPosX(this.ultimaPosX);
        jugador.setPosY(this.ultimaPosY);

        int fasePelea = -1;

        if (estaSobreEnemigo(lblPelea1) && pelea1Activa) {
            fasePelea = 0;
            pelea1Activa = false;
            jugador.setPelea1Activa(false);
            lblPelea1.setVisible(false);
        } else if (estaSobreEnemigo(lblPelea2) && pelea2Activa && !jugador.isPelea1Activa()) {
            fasePelea = 1;
            pelea2Activa = false;
            jugador.setPelea2Activa(false);
            lblPelea2.setVisible(false);
        } else if (estaSobreEnemigo(lblPelea3) && pelea3Activa && !jugador.isPelea2Activa()) {
            fasePelea = 2;
            pelea3Activa = false;
            jugador.setPelea3Activa(false);
            lblPelea3.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this,
                    "No puedes iniciar esta pelea aún.",
                    "Acceso denegado",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        detenerMusica();
        this.dispose();

        // Pasar las coordenadas del panel, no del objeto jugador
        VentanaPelea ventanaPelea = new VentanaPelea(null, true, jugador, fasePelea, ultimaPosX, ultimaPosY);
        ventanaPelea.setLocationRelativeTo(null);
        ventanaPelea.setVisible(true);
    }
    
    /**
     * Cierra la ventana actual y termina la ejecución del programa.
     */
    private void exitGame() {
        this.dispose(); 
        System.exit(0);
    }
    
    /**
     * Inicia una animación intermitente que mantiene visibles los enemigos activos
     * mediante un temporizador que se actualiza cada 500 milisegundos.
     */
    private void iniciarAnimacionParpadeo() {
    Timer timer = new Timer(500, e -> {
        if (pelea1Activa)
            lblPelea1.setVisible(true);
        if (pelea2Activa)
            lblPelea2.setVisible(true);
        if (pelea3Activa)
            lblPelea3.setVisible(true);
    });
    timer.start();
}

    /**
     * Actualiza los colores de los enemigos para indicar visualmente si el jugador
     * está sobre uno de ellos. Verde significa disponible para pelear, rojo no disponible.
     */
    private void actualizarFeedback() {
    lblPelea1.setForeground(estaSobreEnemigo(lblPelea1) ? Color.GREEN : Color.RED);
    lblPelea2.setForeground(estaSobreEnemigo(lblPelea2) ? Color.GREEN : Color.RED);
    lblPelea3.setForeground(estaSobreEnemigo(lblPelea3) ? Color.GREEN : Color.RED);
}

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCampo;
    private javax.swing.JLabel lblJugador;
    private javax.swing.JLabel lblPelea1;
    private javax.swing.JLabel lblPelea2;
    private javax.swing.JLabel lblPelea3;
    private javax.swing.JPanel pnlJugador;
    // End of variables declaration//GEN-END:variables
}
