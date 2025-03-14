// Thêm thư viện
import java.util.*;;

public class DaysOfMonth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String monthInput;
        int year = -1;
        int month = -1;
        String[] monthNames = {
            "January", "Jan.", "Jan", "1", "February", "Feb.", "Feb", "2", 
            "March", "Mar.", "Mar", "3", "April", "Apr.", "Apr", "4",
            "May", "5", "June", "Jun.", "Jun", "6", "July", "Jul.", "Jul", "7",
            "August", "Aug.", "Aug", "8", "September", "Sep.", "Sep", "9",
            "October", "Oct.", "Oct", "10", "November", "Nov.", "Nov", "11",
            "December", "Dec.", "Dec", "12"
        };

        int[] monthDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        while (month == -1) {
            System.out.print("Enter a month: ");
            monthInput = scanner.nextLine().trim();

            for (int i = 0; i < monthNames.length; i++) {
                if (monthNames[i].equalsIgnoreCase(monthInput)) {
                    month = (i / 4) + 1; 
                    break;
                }
            }

            if (month == -1) {
                System.out.println("Invalid month. Please enter again.");
            }
        }
        while (year < 0) {
            System.out.print("Enter a year: ");
            if (scanner.hasNextInt()) {
                year = scanner.nextInt();
                if (year < 0) {
                    System.out.println("Invalid year.");
                    year = -1; 
                }
            } else {
                System.out.println("Invalid input.");
                scanner.next();
            }
        }

        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);

        if (month == 2 && isLeapYear) {
            System.out.println("February " + year + " has 29 days (Leap Year).");
        } else {
            System.out.println("The month " + month + " in " + year + " has " + monthDays[month - 1] + " days.");
        }

        scanner.close();
    }
}
