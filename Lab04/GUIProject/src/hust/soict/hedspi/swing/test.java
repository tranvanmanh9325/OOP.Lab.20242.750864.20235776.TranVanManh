package hust.soict.hedspi.swing;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class test extends Frame {
    public test() {
        setLayout(new GridLayout(5, 5)); // 5 rows, 5 columns

        
        for (int i = 1; i <= 25; i++) {
            add(new Button("Button " + i));
        }

        setTitle("GridLayout 5x5");
        setSize(400, 400);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0); 
            }
        });
    }
    
    public static void main(String[] args) {
        new test();
    }
    

}

