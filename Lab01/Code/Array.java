// Thêm thư viện
import java.util.Arrays;
import java.util.Scanner;

public class Array {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();
        int[] myArray = new int[n];
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            myArray[i] = scanner.nextInt();
        }
        Arrays.sort(myArray);
        int sum = 0;
        for (int num : myArray) {
            sum += num;
        }
        double average = (double) sum / myArray.length;
        System.out.println("Sorted Array: " + Arrays.toString(myArray));
        System.out.println("Sum of elements: " + sum);
        System.out.println("Average value: " + average);

        scanner.close();
    }
}