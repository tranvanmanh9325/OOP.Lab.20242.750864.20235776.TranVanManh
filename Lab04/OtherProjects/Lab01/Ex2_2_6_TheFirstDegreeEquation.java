import java.util.Scanner;

public class Ex2_2_6_TheFirstDegreeEquation {
	public static void main(String[] args) {
		/*Giai phuong trinh ax + b = 0
		 * Neu a = 0
		 * 		b = 0 => pt vo so nghiem
		 * 		b != 0 => pt vo nghiem
		 * Neu a != 0 
		 * 		Pt co nghiem duy nhat: x = -b / a
		 */
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Phuong trinh bac nhat(ax + b = 0): 1\n" + "He phuong trinh bac nhat(a11x1 + a12x2 = b1  a21x1 + a22x2 = b2): 2\n" + "Phuong trinh bac hai(ax^2 + bx + c = 0): 3\n" + "Vui long chon thao tac: ");
		int thaoTac;
		thaoTac = keyboard.nextInt();
		if(thaoTac == 1)
		{
			System.out.println("Nhap a: ");
			double a = keyboard.nextDouble();
			System.out.println("Nhap b: ");
			double b = keyboard.nextDouble();
			System.out.println();
			if(a == 0)
			{
				//a = 0
				if(b == 0)
				{
					// a = 0 && b = 0
					System.out.println("Phuong trinh vo so nghiem");
				}
				else
				{
					// a = 0 && b != 0
					System.out.println("Phuong trinh vo nghiem");
				}
			}
			else
			{
				double x = -b/a;
				System.out.println("Nghiem cua phuong trinh la: x = " + x);
			}
		}
		else if(thaoTac == 2)
		{
			//Dung phuong phap Cramer
			System.out.println("Nhap he so cua phuong trinh thu nhat a11, a12, b1: ");
			double a11 = keyboard.nextDouble();
			double a12 = keyboard.nextDouble();
			double b1 = keyboard.nextDouble();
			
			System.out.println("Nhap he so cua phuong trinh thu hai a21, a22, b2: ");
			double a21 = keyboard.nextDouble();
			double a22 = keyboard.nextDouble();
			double b2 = keyboard.nextDouble();
			//Tinh dinh thuc
			double D = a11*a22 - a12*a21;
			double Dx = b1*a22 - b2*a12;
			double Dy = b2*a11 - b1*a21;
			
			if(D != 0)
			{
				double x1 = Dx/D;
				double x2 = Dy/D;
				System.out.println("He co nghiem duy nhat: ");
				System.out.println("x1 = " + x1);
				System.out.println("x2 = " + x2);
			}
			else
			{
				if(Dx == 0 && Dy == 0)
				{
					System.out.println("He vo so nghiem");
				}
				else
				{
					System.out.println("He vo nghiem");
				}
			}
			
		}
		else if(thaoTac == 3)
		{
			System.out.println("Nhap a: ");
			double a = keyboard.nextDouble();
			System.out.println("Nhap b: ");
			double b = keyboard.nextDouble();
			System.out.println("Nhap c: ");
			double c = keyboard.nextDouble();
			if (a == 0) 
			{
                if (b == 0) 
                {
                    if (c == 0) 
                    {
                        System.out.println("Phuong trinh vo so nghiem");
                    } 
                    else 
                    {
                        System.out.println("Phuong trinh vo nghiem");
                    }
                } 
                else
                {
                    double x = -c / b;
                    System.out.println("Nghiem cua phuong trinh la: x = " + x);
                }
            }
			else
			{
				double delta = (b*b) - (4*a*c);
				if(delta > 0)
				{
					double x1 = (-b - Math.sqrt(delta))/(2*a);
					double x2 = (-b + Math.sqrt(delta))/(2*a);
					System.out.println("Phuong trinh co 2 nghiem phan biet:\n" + "x1 = " + x1 +"\n" + "x2 = " + x2 + "\n");
				}
				else if(delta == 0)
				{
					double x1 = (-b)/(2*a);
					System.out.println("Phuong trinh co nghiem kep: x1 = x2 = " + x1 + "\n");
				}
				else
				{
					System.out.println("Phuong trinh vo nghiem");
				}
			}
			
		}
		else
		{
			System.out.println("Gia tri ban nhap khong hop le!");
		}
		
	}
	
}
