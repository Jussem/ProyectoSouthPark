/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package autonoma.deadlycode.gui;

import autonoma.deadlycode.elements.CampoDeBatalla;
import autonoma.deadlycode.elements.JugadorCartman;
import autonoma.deadlycode.elements.Personaje;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class VentanaMundo extends javax.swing.JDialog {

    CampoDeBatalla campo;
    private List<Personaje> enemigos;
    
    public VentanaMundo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            String rutaArchivo = "src/autonoma/deadlycode/models/puntajes.txt";
            this.campo = new CampoDeBatalla(rutaArchivo);
        } catch (IOException ex) {
            Logger.getLogger(VentanaMundo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al crear/cargar el archivo de puntajes", "Error", JOptionPane.ERROR_MESSAGE);
        }
        initComponents();
        lblJugador.setFocusable(false);  
        lblJugador.removeKeyListener(lblJugador.getKeyListeners()[0]);
        pnlJugador.setFocusable(true);
        pnlJugador.requestFocusInWindow();
    }
    private boolean estaSobreEnemigo() {
        int jugadorX = pnlJugador.getX() + pnlJugador.getWidth() / 2;
        int jugadorY = pnlJugador.getY() + pnlJugador.getHeight() / 2;

        int enemigoX = lblPelea1.getX() + lblPelea1.getWidth() / 2;
        int enemigoY = lblPelea1.getY() + lblPelea1.getHeight() / 2;
        int rango = 50;

        return Math.abs(jugadorX - enemigoX) < rango
                && Math.abs(jugadorY - enemigoY) < rango;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlJugador = new javax.swing.JPanel();
        lblJugador = new javax.swing.JLabel();
        lblPelea1 = new javax.swing.JLabel();
        lblCampo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlJugador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pnlJugadorKeyPressed(evt);
            }
        });

        lblJugador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autonoma/deadlycode/images/JugadorCaminando.png"))); // NOI18N
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
        jPanel1.add(lblPelea1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, -1, -1));

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
                    if (estaSobreEnemigo()) {
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
        this.dispose();
        VentanaPelea ventanaPelea = new VentanaPelea(null, true, campo.getJugador());
        ventanaPelea.setVisible(true);
    }
    private void exitGame() {
        this.dispose(); 
        System.exit(0);
    }
    private void actualizarFeedback() {
        if (estaSobreEnemigo()) {
            lblPelea1.setForeground(Color.GREEN); 
        } else {
            lblPelea1.setForeground(Color.RED); 
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCampo;
    private javax.swing.JLabel lblJugador;
    private javax.swing.JLabel lblPelea1;
    private javax.swing.JPanel pnlJugador;
    // End of variables declaration//GEN-END:variables
}
