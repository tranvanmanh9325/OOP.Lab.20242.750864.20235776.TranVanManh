import java.util.Scanner;

public class Ex2_2_5 {
	public static void main(String[] args) {
		double a, b;
		java.util.Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the first number: ");
		
		a = keyboard.nextDouble();
		System.out.println("Enter the second number: ");
		b = keyboard.nextDouble();
		
		//sum
		System.out.println("The sum of 2 numbers is: " + (a + b));
		
		//difference
		System.out.println("The difference of 2 numbers is: " + (a - b));
		
		//product
		System.out.println("The product of 2 numbers is: " + (a * b));
		
		//quotient
		if(b == 0)
		{
			System.out.println("Cannot divide by zero.");
		}
		else
		{
			System.out.println("The quotient of 2 numbers is: " + (a / b));
		}
		keyboard.close();
		
	}
	
}
