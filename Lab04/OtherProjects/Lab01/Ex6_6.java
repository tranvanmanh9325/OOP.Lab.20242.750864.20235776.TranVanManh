import java.util.Scanner;

public class Ex6_6 {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);

		System.out.println("Enter number of rows: ");
		int rows = keyboard.nextInt();
		System.out.println("Enter number of columns: ");
		int cols = keyboard.nextInt();

		if (rows <= 0 || cols <= 0) {
			System.out.println("ERROR DATA");
			return;
		}

		int[][] arr1 = new int[rows][cols];
		int[][] arr2 = new int[rows][cols];
		int[][] arr3 = new int[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				arr3[i][j] = 0;
			}
		}

		System.out.println("Enter data for array 1: ");
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print("arr1[" + i + "][" + j + "]: ");
				arr1[i][j] = keyboard.nextInt();
			}
		}

		System.out.println("Enter data for array 2: ");

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print("arr2[" + i + "][" + j + "]: ");
				arr2[i][j] = keyboard.nextInt();
			}
		}

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				arr3[i][j] += arr1[i][j] + arr2[i][j];
			}
		}

		System.out.println("After add two matrices: ");
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.printf("%-5d",arr3[i][j]);
			}
			System.out.println();
		}

	}
}
