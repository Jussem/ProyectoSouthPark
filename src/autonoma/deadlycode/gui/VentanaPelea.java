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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class VentanaPelea extends javax.swing.JDialog {

    private final String[] fondos = {
        "/autonoma/deadlycode/images/fondoPelea1.jpg",
        "/autonoma/deadlycode/images/fondoPelea2.jpg",
        "/autonoma/deadlycode/images/fondoPelea3.webp"
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
    
    public VentanaPelea(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializarEnemigos();
        cargarFase(faseActual);
    }
    private void inicializarEnemigos() {
        enemigosFases = new Personaje[]{
            new ProgramadorJunior(420, 170, 100, 100, Color.BLUE),
            new ProgramadorSenior(420, 170, 120, 120, Color.GREEN),
            new Arquitecto(420, 170, 150, 150, Color.RED)
        };
    }
    private void cargarFase(int fase) {
        enemigoActual = enemigosFases[fase];
        lblFondo.setIcon(new ImageIcon(getClass().getResource(fondos[fase])));
        lblEnemigo.setIcon(new ImageIcon(getClass().getResource(enemigos[fase])));
        mostrarEstado();
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
        String mensaje = "Vida enemigo: " + enemigoActual.getVida()
                + "\nPociones: " + jugador.tienePociones()
                + "\nHolaMundo: " + jugador.puedeUsarHolaMundo();
        lblEstado.setText(mensaje);
    }
    private void turnoEnemigo() {
        
        if (enemigoActual instanceof ProgramadorJunior) {
            ProgramadorJunior junior = (ProgramadorJunior) enemigoActual;
            junior.documentacionJava(jugador);
        } else if (enemigoActual instanceof ProgramadorSenior) {
            ProgramadorSenior senior = (ProgramadorSenior) enemigoActual;
            senior.bugs(jugador);
        } else if (enemigoActual instanceof Arquitecto) {
            Arquitecto arquitecto = (Arquitecto) enemigoActual;
            arquitecto.arbolRojinegro(jugador);
        }
        actualizarEstado();
        if (jugador.getVida() <= 0) {
            JOptionPane.showMessageDialog(this, "¡Has sido derrotado!", "Game Over", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblEnemigo = new javax.swing.JLabel();
        lblJugador = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        lblFondo = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEnemigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autonoma/deadlycode/images/ProgramadorJunior.png"))); // NOI18N
        jPanel1.add(lblEnemigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, -1, -1));

        lblJugador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autonoma/deadlycode/images/CartmanPelea.png"))); // NOI18N
        lblJugador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblJugadorKeyPressed(evt);
            }
        });
        jPanel1.add(lblJugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(-110, 200, 370, 210));

        lblEstado.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 3, 24)); // NOI18N
        lblEstado.setText("0");
        jPanel1.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 560, 40));

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autonoma/deadlycode/images/fondoPelea1.jpg"))); // NOI18N
        jPanel1.add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 420));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblJugadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblJugadorKeyPressed
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_Z:
                jugador.atacar(enemigoActual);
                break;
            case KeyEvent.VK_X:
                if (jugador.puedeUsarHolaMundo()) {
                    jugador.HolaMundo(enemigoActual);
                } else {
                    JOptionPane.showMessageDialog(this, "¡No te quedan usos de HolaMundo!", "Error", JOptionPane.WARNING_MESSAGE);
                }
                break;
            case KeyEvent.VK_H:
                if (jugador.tienePociones()) {
                    jugador.curar();
                } else {
                    JOptionPane.showMessageDialog(this, "¡No te quedan pociones!", "Error", JOptionPane.WARNING_MESSAGE);
                }
                break;
        }
        actualizarEstado();

        if (enemigoActual.getVida() > 0) {
            turnoEnemigo();
        } else {
            siguienteFase();
        }
    }//GEN-LAST:event_lblJugadorKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblEnemigo;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblJugador;
    // End of variables declaration//GEN-END:variables
}
