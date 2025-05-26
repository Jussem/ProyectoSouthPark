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

/**
 *
 * @author Asus
 */
public class VentanaMundo extends javax.swing.JDialog {

    private Clip musicaFondo;
    CampoDeBatalla campo;
    private List<Personaje> enemigos;
    private boolean pelea1Activa = true;
    private boolean pelea2Activa = true;
    private boolean pelea3Activa = true;
    
    public VentanaMundo(java.awt.Frame parent, boolean modal,JugadorCartman jugador) {
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
        lblJugador.setFocusable(false);  
        lblJugador.removeKeyListener(lblJugador.getKeyListeners()[0]);
        pnlJugador.setFocusable(true);
        pnlJugador.requestFocusInWindow();
        pnlJugador.setLocation(jugador.getPosX(), jugador.getPosY());
        
    if (!jugador.isPelea1Activa()) lblPelea1.setVisible(false);
    if (!jugador.isPelea2Activa()) lblPelea2.setVisible(false);
    if (!jugador.isPelea3Activa()) lblPelea3.setVisible(false);
    }

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


    
    private void iniciarMusicaFondo() {
        try {
            InputStream audioStream = getClass().getResourceAsStream("/autonoma/deadlycode/sounds/musica_fondo_2.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(audioStream);
            musicaFondo = AudioSystem.getClip();
            musicaFondo.open(ais);
            FloatControl gainControl = (FloatControl) musicaFondo.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f);
            musicaFondo.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.err.println("Error al cargar música de fondo: " + e.getMessage());
        }
    }

    private void detenerMusica(){
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
        jPanel1.add(lblPelea1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 170, -1, -1));

        lblPelea2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        lblPelea2.setForeground(new java.awt.Color(255, 0, 0));
        lblPelea2.setText("Iniciar pelea 'T'");
        jPanel1.add(lblPelea2, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 170, -1, -1));

        lblPelea3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        lblPelea3.setForeground(new java.awt.Color(255, 0, 0));
        lblPelea3.setText("Iniciar pelea 'T'");
        jPanel1.add(lblPelea3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, -1, -1));

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
    private void iniciarPelea() {
    JugadorCartman jugador = campo.getJugador(); // Obtener jugador actual

    if (estaSobreEnemigo(lblPelea1)) {
        pelea1Activa = false;
        jugador.setPelea1Activa(false); // Marca como desactivada permanentemente
        lblPelea1.setVisible(false);
    } else if (estaSobreEnemigo(lblPelea2)) {
        pelea2Activa = false;
        jugador.setPelea2Activa(false);
        lblPelea2.setVisible(false);
    } else if (estaSobreEnemigo(lblPelea3)) {
        pelea3Activa = false;
        jugador.setPelea3Activa(false);
        lblPelea3.setVisible(false);
    }

    this.dispose();
    detenerMusica();
    VentanaPelea ventanaPelea = new VentanaPelea(null, true, jugador);
    ventanaPelea.setLocationRelativeTo(null);
    ventanaPelea.setVisible(true);
}


    private void exitGame() {
        this.dispose(); 
        System.exit(0);
    }
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
