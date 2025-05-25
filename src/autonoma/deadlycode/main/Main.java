package autonoma.deadlycode.main;

import autonoma.deadlycode.gui.VentanaPrincipal;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            String ruta = "puntajes.txt";
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setSize(1328, 770);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}