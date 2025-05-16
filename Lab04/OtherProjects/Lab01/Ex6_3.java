import java.util.Scanner;

public class Ex6_3 {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the height of the triangle (n): ");
        int n = keyboard.nextInt();
        if(n <= 0)
        {
        	System.out.println("Cannot Print The Triangle");
        }
        for (int i = 1; i <= n; i++) 
        {
            // Print Space
            for (int j = 1; j <= n - i; j++) 
            {
                System.out.print(" ");
            }
            // Print '*'
            for (int j = 1; j <= (2 * i - 1); j++) 
            {
                System.out.print("*");
            }
            System.out.println("\n"); // next line
        }
    }
}
