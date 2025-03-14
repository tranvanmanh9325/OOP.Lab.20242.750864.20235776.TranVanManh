// Thêm thư viện
import java.util.*;;

public class HeightOfStars {
    public static void main(String[] args) {
        // Tạo scanner
        Scanner scanner = new Scanner(System.in);

        // Khai báo biến n là chiều cao của sao
        System.out.print("Hight Of N Stars: ");
        int n = scanner.nextInt();
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n-i; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < (2 * i - 1); k++) {
                System.out.print("*");
            }
            System.out.println();
        }
        scanner.close();
    }
}
