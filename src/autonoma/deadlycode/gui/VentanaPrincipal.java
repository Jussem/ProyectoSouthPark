package autonoma.deadlycode.gui;

import autonoma.deadlycode.elements.CampoDeBatalla;
import autonoma.deadlycode.elements.JugadorCartman;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Clase que representa la ventana principal del juego "The Deadly Code".
 * Controla la reproducción de música de fondo, muestra puntajes almacenados y 
 * permite al jugador iniciar o salir del juego con efectos de sonido.
 * 
 * Esta clase extiende JFrame e incluye interacción gráfica con el usuario.
 * 
 * @author Juan Sebastian Lopez Guzman
 * @author Cristian Camilo Salazar Arenas
 * @author Juan Jose Morales
 * @version 1.0
 * @since 2025
 */
public class VentanaPrincipal extends javax.swing.JFrame {
    
    /** Clip de audio que representa la música de fondo. */
    private Clip musicaFondo;

    /**
     * Constructor de la ventana principal. Inicializa componentes,
     * muestra los puntajes almacenados y comienza la música de fondo.
     * 
     * @throws FileNotFoundException si no se encuentra el archivo de puntajes
     * @throws IOException si ocurre un error al leer el archivo de puntajes
     */
    public VentanaPrincipal() throws FileNotFoundException, IOException {
        initComponents();
        this.mostrarPuntajes();
        iniciarMusicaFondo();
    }

    /**
     * Inicia la música de fondo en bucle desde el archivo de recursos.
     */
    private void iniciarMusicaFondo() {
        try {
            InputStream audioStream = getClass().getResourceAsStream("/autonoma/deadlycode/sounds/musica_fondo.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(audioStream);
            musicaFondo = AudioSystem.getClip();
            musicaFondo.open(ais);
            FloatControl gainControl = (FloatControl) musicaFondo.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);  // Reduce el volumen
            musicaFondo.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.err.println("Error al cargar música de fondo: " + e.getMessage());
        }
    }

    /**
     * Detiene y cierra la música de fondo si se está reproduciendo.
     */
    private void detenerMusica() {
        if (musicaFondo != null && musicaFondo.isRunning()) {
            musicaFondo.stop();
            musicaFondo.close();
        }
    }

    /**
     * Lee los puntajes desde un archivo y los muestra en la interfaz gráfica.
     * 
     * @throws IOException si ocurre un error al leer el archivo
     */
    public void mostrarPuntajes() throws IOException {
        String ruta = "puntajes.txt";
        CampoDeBatalla campoPuntajes = new CampoDeBatalla(ruta);
        ArrayList<Integer> puntajes = campoPuntajes.leerPuntajes();
        StringBuilder sb = new StringBuilder();
        for (int p : puntajes) {
            sb.append(p).append("<br>");
        }
        this.Puntajelbl.setText("<html><div style='font-size:14px'>" + sb.toString() + "</div></html>");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPuntaje = new javax.swing.JPanel();
        TituloPuntajelbl = new javax.swing.JLabel();
        Puntajelbl = new javax.swing.JLabel();
        PuntajeGuioneslbl = new javax.swing.JLabel();
        PanelComando = new javax.swing.JPanel();
        TituloComandolbl = new javax.swing.JLabel();
        ComandoGuioneslbl = new javax.swing.JLabel();
        TituloComandolbl1 = new javax.swing.JLabel();
        TituloComandolbl2 = new javax.swing.JLabel();
        TituloComandolbl3 = new javax.swing.JLabel();
        TituloComandolbl4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        PanelJugar = new javax.swing.JPanel();
        Jugartxt = new javax.swing.JLabel();
        PanelSalir = new javax.swing.JPanel();
        Salirtxt = new javax.swing.JLabel();
        TheDeadlyCodeTitulolbl = new javax.swing.JLabel();
        Llamaslbl = new javax.swing.JLabel();
        Llamaslbl1 = new javax.swing.JLabel();
        Llamaslbl2 = new javax.swing.JLabel();
        Llamaslbl3 = new javax.swing.JLabel();
        Menulbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelPuntaje.setBackground(new java.awt.Color(51, 0, 51));
        PanelPuntaje.setForeground(new java.awt.Color(0, 153, 0));
        PanelPuntaje.setPreferredSize(new java.awt.Dimension(200, 353));

        TituloPuntajelbl.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        TituloPuntajelbl.setForeground(new java.awt.Color(255, 255, 255));
        TituloPuntajelbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TituloPuntajelbl.setText("PUNTAJES");
        TituloPuntajelbl.setToolTipText("");

        Puntajelbl.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Puntajelbl.setForeground(new java.awt.Color(255, 255, 255));
        Puntajelbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        PuntajeGuioneslbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PuntajeGuioneslbl.setForeground(new java.awt.Color(255, 255, 255));
        PuntajeGuioneslbl.setText("------------------------------------");
        PuntajeGuioneslbl.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout PanelPuntajeLayout = new javax.swing.GroupLayout(PanelPuntaje);
        PanelPuntaje.setLayout(PanelPuntajeLayout);
        PanelPuntajeLayout.setHorizontalGroup(
            PanelPuntajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PuntajeGuioneslbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPuntajeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TituloPuntajelbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPuntajeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Puntajelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
        PanelPuntajeLayout.setVerticalGroup(
            PanelPuntajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPuntajeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TituloPuntajelbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PuntajeGuioneslbl, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Puntajelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(489, Short.MAX_VALUE))
        );

        PanelComando.setBackground(new java.awt.Color(51, 0, 51));
        PanelComando.setForeground(new java.awt.Color(0, 153, 0));
        PanelComando.setPreferredSize(new java.awt.Dimension(200, 353));

        TituloComandolbl.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        TituloComandolbl.setForeground(new java.awt.Color(255, 255, 255));
        TituloComandolbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TituloComandolbl.setText("COMANDOS");

        ComandoGuioneslbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ComandoGuioneslbl.setForeground(new java.awt.Color(255, 255, 255));
        ComandoGuioneslbl.setText("------------------------------------");
        ComandoGuioneslbl.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        TituloComandolbl1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        TituloComandolbl1.setForeground(new java.awt.Color(255, 255, 255));
        TituloComandolbl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TituloComandolbl1.setText("Z --> ATACAR");

        TituloComandolbl2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        TituloComandolbl2.setForeground(new java.awt.Color(255, 255, 255));
        TituloComandolbl2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TituloComandolbl2.setText("H -> CURACIÓN");

        TituloComandolbl3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        TituloComandolbl3.setForeground(new java.awt.Color(255, 255, 255));
        TituloComandolbl3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TituloComandolbl3.setText("Q -> SALIR");

        TituloComandolbl4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        TituloComandolbl4.setForeground(new java.awt.Color(255, 255, 255));
        TituloComandolbl4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TituloComandolbl4.setText("X -> PODER ESP");

        javax.swing.GroupLayout PanelComandoLayout = new javax.swing.GroupLayout(PanelComando);
        PanelComando.setLayout(PanelComandoLayout);
        PanelComandoLayout.setHorizontalGroup(
            PanelComandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ComandoGuioneslbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
            .addComponent(TituloComandolbl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelComandoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelComandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TituloComandolbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TituloComandolbl2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TituloComandolbl3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TituloComandolbl4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelComandoLayout.setVerticalGroup(
            PanelComandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelComandoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TituloComandolbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComandoGuioneslbl, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(TituloComandolbl1)
                .addGap(29, 29, 29)
                .addComponent(TituloComandolbl2)
                .addGap(30, 30, 30)
                .addComponent(TituloComandolbl4)
                .addGap(30, 30, 30)
                .addComponent(TituloComandolbl3)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 700));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelJugar.setBackground(new java.awt.Color(0, 153, 153));
        PanelJugar.setBorder(new javax.swing.border.MatteBorder(null));
        PanelJugar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelJugarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PanelJugarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PanelJugarMouseExited(evt);
            }
        });

        Jugartxt.setBackground(new java.awt.Color(0, 0, 0));
        Jugartxt.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        Jugartxt.setText("JUGAR");

        javax.swing.GroupLayout PanelJugarLayout = new javax.swing.GroupLayout(PanelJugar);
        PanelJugar.setLayout(PanelJugarLayout);
        PanelJugarLayout.setHorizontalGroup(
            PanelJugarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
            .addGroup(PanelJugarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelJugarLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Jugartxt)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        PanelJugarLayout.setVerticalGroup(
            PanelJugarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
            .addGroup(PanelJugarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelJugarLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Jugartxt)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel2.add(PanelJugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 530, 300, 50));

        PanelSalir.setBackground(new java.awt.Color(0, 153, 153));
        PanelSalir.setBorder(new javax.swing.border.MatteBorder(null));
        PanelSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelSalirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PanelSalirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PanelSalirMouseExited(evt);
            }
        });

        Salirtxt.setBackground(new java.awt.Color(0, 0, 0));
        Salirtxt.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        Salirtxt.setText("SALIR");

        javax.swing.GroupLayout PanelSalirLayout = new javax.swing.GroupLayout(PanelSalir);
        PanelSalir.setLayout(PanelSalirLayout);
        PanelSalirLayout.setHorizontalGroup(
            PanelSalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
            .addGroup(PanelSalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelSalirLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Salirtxt)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        PanelSalirLayout.setVerticalGroup(
            PanelSalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
            .addGroup(PanelSalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelSalirLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Salirtxt)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel2.add(PanelSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 600, 300, 50));

        TheDeadlyCodeTitulolbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autonoma/deadlycode/images/TheDeadlyCodeTitulo.png"))); // NOI18N
        jPanel2.add(TheDeadlyCodeTitulolbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, 374, 254));

        Llamaslbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autonoma/deadlycode/images/Flames.png"))); // NOI18N
        jPanel2.add(Llamaslbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 410, -1, 60));

        Llamaslbl1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autonoma/deadlycode/images/Flames.png"))); // NOI18N
        jPanel2.add(Llamaslbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 510, -1, 60));

        Llamaslbl2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autonoma/deadlycode/images/Flames.png"))); // NOI18N
        jPanel2.add(Llamaslbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, -1, 60));

        Llamaslbl3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autonoma/deadlycode/images/Flames.png"))); // NOI18N
        jPanel2.add(Llamaslbl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 150, -1, 60));

        Menulbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autonoma/deadlycode/images/MenuPrincipal.jpg"))); // NOI18N
        Menulbl.setPreferredSize(new java.awt.Dimension(1200, 700));
        jPanel2.add(Menulbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 770));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1098, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PanelPuntaje, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(PanelComando, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelComando, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelPuntaje, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Cambia el color del panel "Jugar" al pasar el mouse.
     */
    private void PanelJugarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelJugarMouseEntered
        PanelJugar.setBackground(new java.awt.Color(255, 0, 0));
    }//GEN-LAST:event_PanelJugarMouseEntered
    /**
     * Restaura el color del panel "Jugar" cuando el mouse sale del área.
     */
    private void PanelJugarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelJugarMouseExited
        PanelJugar.setBackground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_PanelJugarMouseExited
/**
     * Cambia el color del panel "Salir" al pasar el mouse.
     */
    private void PanelSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelSalirMouseEntered
        PanelSalir.setBackground(new java.awt.Color(255, 0, 0));
    }//GEN-LAST:event_PanelSalirMouseEntered
    /**
     * Restaura el color del panel "Salir" cuando el mouse sale del área.
     */
    private void PanelSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelSalirMouseExited
        PanelSalir.setBackground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_PanelSalirMouseExited
    /**
     * Maneja el evento de clic en el panel "Jugar". Reproduce un sonido
     * y luego abre la ventana del mundo del juego.
     */
    private void PanelJugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelJugarMouseClicked

         try {
        InputStream audioStream = getClass().getResourceAsStream("/autonoma/deadlycode/sounds/voice_Timmy.wav");
        if (audioStream == null) {
            JOptionPane.showMessageDialog(this, "Archivo no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        final Clip clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(audioStream));
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-15.0f);
        clip.addLineListener(new LineListener() {
            @Override
            public void update(LineEvent event) {
                if (event.getType() == LineEvent.Type.STOP) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            VentanaPrincipal.this.dispose();
                            detenerMusica();
                            JugadorCartman jugador = new JugadorCartman(30, 130, 90, 100, java.awt.Color.RED); // 👈
                            VentanaMundo ventana = new VentanaMundo(VentanaPrincipal.this, true, jugador); // 👈
                            ventana.setLocationRelativeTo(null);
                            ventana.setVisible(true);
                        }
                    });
                    clip.close();
                }
            }
        });
        clip.start();
    } catch (Exception e) {
        e.printStackTrace();
        dispose();
        JugadorCartman jugador = new JugadorCartman(30, 130, 90, 100, java.awt.Color.RED); // 👈
        new VentanaMundo(null, true, jugador).setVisible(true); // 👈
    }
    }//GEN-LAST:event_PanelJugarMouseClicked
                                  
    /**
     * Maneja el evento de clic en el panel "Salir". Reproduce un sonido
     * y luego cierra la aplicación.
     */
    private void PanelSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelSalirMouseClicked
        try {
            InputStream audioStream = getClass().getResourceAsStream("/autonoma/deadlycode/sounds/voicy_Errrrrr.wav");
            if (audioStream == null) {
                JOptionPane.showMessageDialog(this, "Archivo no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(audioStream));
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f);
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                VentanaPrincipal.this.dispose();
                            }
                        });
                        clip.close();
                    }
                }
            });
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
            dispose();
        }
    }//GEN-LAST:event_PanelSalirMouseClicked
    // Variables de la interfaz gráfica generadas por el editor de GUI
    // (NetBeans u otro IDE con diseñador visual)
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ComandoGuioneslbl;
    private javax.swing.JLabel Jugartxt;
    private javax.swing.JLabel Llamaslbl;
    private javax.swing.JLabel Llamaslbl1;
    private javax.swing.JLabel Llamaslbl2;
    private javax.swing.JLabel Llamaslbl3;
    private javax.swing.JLabel Menulbl;
    private javax.swing.JPanel PanelComando;
    private javax.swing.JPanel PanelJugar;
    private javax.swing.JPanel PanelPuntaje;
    private javax.swing.JPanel PanelSalir;
    private javax.swing.JLabel PuntajeGuioneslbl;
    private javax.swing.JLabel Puntajelbl;
    private javax.swing.JLabel Salirtxt;
    private javax.swing.JLabel TheDeadlyCodeTitulolbl;
    private javax.swing.JLabel TituloComandolbl;
    private javax.swing.JLabel TituloComandolbl1;
    private javax.swing.JLabel TituloComandolbl2;
    private javax.swing.JLabel TituloComandolbl3;
    private javax.swing.JLabel TituloComandolbl4;
    private javax.swing.JLabel TituloPuntajelbl;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
