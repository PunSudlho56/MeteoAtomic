import javax.swing.*;

public class Main extends JFrame {
    public Main() {
        setTitle("Meteo");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String text = JOptionPane.showInputDialog(this,"Enter the meteo you need.");
        int number = 5;
        try{
            number = Integer.parseInt(text);
            if (number < 1){
                number = 5;
            }
        }catch(Exception e){}

        Scene scene = new Scene(number);
        add(scene);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}