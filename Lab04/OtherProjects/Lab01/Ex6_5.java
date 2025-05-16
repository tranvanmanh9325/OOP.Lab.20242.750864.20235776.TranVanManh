import java.util.Scanner;

public class Ex6_5 {
	public static void print(int[] arr, int size)
	{
		for(int i = 0; i < size; i++)
		{
			System.out.print(arr[i] + "  ");
		}
		System.out.println();
	}
	
	public static void sort(int[] arr, int size)
	{
		int temp;
		for(int i = 0; i < size; i++)
		{
			for(int j = i+1; j < size; j++)
			{
				if(arr[i] > arr[j])
				{
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter a size of array: ");
		
		int size = keyboard.nextInt();
		
		int[] arr = new int[size];
		
		int sum = 0;
		double average = 0;
		
		System.out.print("Enter elements of a array: ");
		System.out.println();
		
		for(int i = 0; i < size; i++)
		{
			System.out.print("arr["+ i +"]: ");
			arr[i] = keyboard.nextInt();
			sum += arr[i];
		}
		System.out.print("Before sort: ");
		
		print(arr, size);
		
		average = (double)sum / size;
		
		sort(arr, size);
		
		System.out.print("After sort: ");
		
		print(arr, size);
		
		System.out.print("The sum of array: " + sum);
		System.out.println();
		System.out.printf("The average of array: %.2f\n", average);
		
	}
}
