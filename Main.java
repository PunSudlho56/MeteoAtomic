import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {
    public Main() {
        setTitle("Meteo");
        setSize(1000, 563);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel bck = new JPanel();
        bck.setBackground(Color.BLACK);

        add(bck);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}