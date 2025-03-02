// Import thư viện
import javax.swing.JOptionPane;

public class ChoosingOptionFix2 {
    public static void main(String[] args) {
        Object[] options = {"I do", "I don't"}; 
        int option = JOptionPane.showOptionDialog(null, "Do you want to change to the first class ticket?",
        "Select an Option", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        JOptionPane.showMessageDialog(null, "You've chosen: " + (option == JOptionPane.YES_OPTION ? "I do" : "I don't"));
        System.exit(0);
    }
}
