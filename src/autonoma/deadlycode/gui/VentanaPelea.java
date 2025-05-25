/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package autonoma.deadlycode.gui;

import autonoma.deadlycode.elements.Arquitecto;
import autonoma.deadlycode.elements.JugadorCartman;
import autonoma.deadlycode.elements.Personaje;
import autonoma.deadlycode.elements.ProgramadorJunior;
import autonoma.deadlycode.elements.ProgramadorSenior;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Asus
 */
public class VentanaPelea extends javax.swing.JDialog {
    private Clip musicaFondo;

    private final String[] fondos = {
        "/autonoma/deadlycode/images/fondoPelea1.jpg",
        "/autonoma/deadlycode/images/fondoPelea2.jpg",
        "/autonoma/deadlycode/images/fondoPelea3.jpg"
    };
    
    private final String[] enemigos = {
        "/autonoma/deadlycode/images/ProgramadorJunior.png",
        "/autonoma/deadlycode/images/ProgramadorSenior.png",
        "/autonoma/deadlycode/images/Arquitecto.png"
    };
    
    private int faseActual = 0;
    private JugadorCartman jugador;
    private Personaje[] enemigosFases;
    private Personaje enemigoActual;
    private boolean turnoJugador = true;
    
    public VentanaPelea(java.awt.Frame parent, boolean modal, JugadorCartman jugador) {
        super(parent, modal);
        initComponents();
        iniciarMusicaFondo();
        this.jugador = jugador;
        this.turnoJugador = true;
        inicializarEnemigos();
        cargarFase(faseActual);
        lblJugador.requestFocusInWindow();
        
    }
    private void iniciarMusicaFondo() {
        try {
            InputStream audioStream = getClass().getResourceAsStream("/autonoma/deadlycode/sounds/musica_fondo_3.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(audioStream);
            musicaFondo = AudioSystem.getClip();
            musicaFondo.open(ais);
            FloatControl gainControl = (FloatControl) musicaFondo.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);
            musicaFondo.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.err.println("Error al cargar música de fondo: " + e.getMessage());
        }
    }

    private void detenerMusica() {
        if (musicaFondo != null && musicaFondo.isRunning()) {
            musicaFondo.stop();
            musicaFondo.close();
        }
    }
    private void inicializarEnemigos() {
        enemigosFases = new Personaje[]{
            new ProgramadorJunior(420, 170, 100, 100, Color.BLUE),
            new ProgramadorSenior(420, 170, 120, 120, Color.GREEN),
            new Arquitecto(420, 170, 150, 150, Color.RED)
        };
    }
    private void cambiarTurno() {
        turnoJugador = !turnoJugador;
        actualizarEstado();

        if (!turnoJugador) {
            lblJugador.setEnabled(false);//Lo que hace que cambie de color (gris/blanquecino)

            Timer timer = new Timer(1500, e -> {
                turnoEnemigo();
                lblJugador.setEnabled(true);
                lblJugador.requestFocusInWindow();
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
    private void cargarFase(int fase) {
        enemigoActual = enemigosFases[fase];
        lblFondo.setIcon(new ImageIcon(getClass().getResource(fondos[fase])));

        // Cargar imagen del enemigo
        ImageIcon iconoEnemigo = new ImageIcon(getClass().getResource(enemigos[fase]));
        lblEnemigo.setIcon(iconoEnemigo);

        // Ajustar tamaño y posición
        lblEnemigo.setSize(iconoEnemigo.getIconWidth(), iconoEnemigo.getIconHeight());
        lblEnemigo.setLocation(420, 170);

        // Mostrar mensaje de nueva fase
        String nombreEnemigo = "";
        if (fase == 0) {
            nombreEnemigo = "Programador Junior";
        } else if (fase == 1) {
            nombreEnemigo = "Programador Senior";
        } else if (fase == 2) {
            nombreEnemigo = "Arquitecto";
        }

        JOptionPane.showMessageDialog(this,
                "¡Nueva fase! Enfrentando a: " + nombreEnemigo,
                "Fase " + (fase + 1),
                JOptionPane.INFORMATION_MESSAGE);

        actualizarEstado();
    }
    public void siguienteFase() {
        faseActual++;
        if (faseActual < 3) {
            cargarFase(faseActual);
        } else {
            JOptionPane.showMessageDialog(this, "¡Has ganado el juego!");
            this.dispose();
        }
    }
    private void mostrarEstado() {
        JOptionPane.showMessageDialog(this, 
            "Vida del enemigo: " + enemigoActual.getVida(), 
            "Estado", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    private void actualizarEstado() {
        String mensaje = String.format(
                "Turno: %s | Vida Jugador: %d | Vida Enemigo: %d | %nPociones: %d | HolaMundo: %d",
                turnoJugador ? "JUGADOR" : "ENEMIGO",
                jugador.getVida(),
                enemigoActual.getVida(),
                jugador.tienePociones() ? jugador.pocionesRestantes : 0,
                jugador.puedeUsarHolaMundo() ? jugador.usosHolaMundo : 0
        );
        lblEstado.setText(mensaje);
    }
    private void turnoEnemigo() {
        if (enemigoActual instanceof ProgramadorJunior) {
            ProgramadorJunior junior = (ProgramadorJunior) enemigoActual;
            junior.documentacionJava(jugador);
            JOptionPane.showMessageDialog(this,
                    "¡El Programador Junior te ataca con Documentación Java!",
                    "Ataque Enemigo",
                    JOptionPane.WARNING_MESSAGE);
        } else if (enemigoActual instanceof ProgramadorSenior) {
            ProgramadorSenior senior = (ProgramadorSenior) enemigoActual;
            senior.bugs(jugador);
            JOptionPane.showMessageDialog(this,
                    "¡El Programador Senior te ataca con Bugs!",
                    "Ataque Enemigo",
                    JOptionPane.WARNING_MESSAGE);
        } else if (enemigoActual instanceof Arquitecto) {
            Arquitecto arquitecto = (Arquitecto) enemigoActual;
            arquitecto.arbolRojinegro(jugador);
            JOptionPane.showMessageDialog(this,
                    "¡El Arquitecto te ataca con Árbol Rojinegro!",
                    "Ataque Enemigo",
                    JOptionPane.WARNING_MESSAGE);
        }

        actualizarEstado();

        if (jugador.getVida() <= 0) {
            JOptionPane.showMessageDialog(this,
                    "¡Has sido derrotado!",
                    "Game Over",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } else {
            turnoJugador = true; 
            actualizarEstado();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblEnemigo = new javax.swing.JLabel();
        lblJugador = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblEstado = new javax.swing.JLabel();
        lblFondo = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEnemigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autonoma/deadlycode/images/ProgramadorJunior.png"))); // NOI18N
        jPanel1.add(lblEnemigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, -1, -1));

        lblJugador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autonoma/deadlycode/images/CartmanPelea.png"))); // NOI18N
        lblJugador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblJugadorKeyPressed(evt);
            }
        });
        jPanel1.add(lblJugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(-120, 190, 370, 210));

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        lblEstado.setBackground(new java.awt.Color(0, 0, 0));
        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(255, 255, 255));
        lblEstado.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 40));

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autonoma/deadlycode/images/fondoPelea1.jpg"))); // NOI18N
        jPanel1.add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 410));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblJugadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblJugadorKeyPressed
        if (!turnoJugador || !lblJugador.isEnabled()) {
            JOptionPane.showMessageDialog(this,
                    "Espera tu turno!",
                    "Turno del enemigo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean accionValida = true;

        switch (evt.getKeyCode()) {
            case KeyEvent.VK_Z:
                jugador.atacar(enemigoActual);
                
                break;

            case KeyEvent.VK_X:
                if (jugador.puedeUsarHolaMundo()) {
                    jugador.HolaMundo(enemigoActual);
                    
                } else {
                    JOptionPane.showMessageDialog(this,
                            "¡No te quedan usos de HolaMundo!",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                    accionValida = false;
                }
                break;

            case KeyEvent.VK_H:
                if (jugador.tienePociones()) {
                    jugador.curar();
                    
                } else {
                    JOptionPane.showMessageDialog(this,
                            "¡No te quedan pociones!",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                    accionValida = false;
                }
                break;

            default:
                accionValida = false;
        }

        if (accionValida) {
            actualizarEstado();

            if (enemigoActual.getVida() <= 0) {
                detenerMusica();
                siguienteFase();
            } else {
                cambiarTurno();
            }
        }
    }//GEN-LAST:event_lblJugadorKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblEnemigo;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblJugador;
    // End of variables declaration//GEN-END:variables
}
