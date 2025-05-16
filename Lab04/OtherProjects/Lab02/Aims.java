
public class Aims {
	public static void main(String[] args) {
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);
		DigitalVideoDisc dvd4 = new DigitalVideoDisc("Harry Potter", "Fantasy", "Chris Columbus", 152, 25.50f);
		DigitalVideoDisc dvd5 = new DigitalVideoDisc("The Avengers", "Action", "Joss Whedon", 143, 27.99f);
		DigitalVideoDisc dvd6 = new DigitalVideoDisc("Titanic", "Romance", "James Cameron", 195, 22.75f);
		
		//Test 12
		Cart cart1 = new Cart();	
		cart1.addDigitalVideoDisc(dvd1);
		cart1.addDigitalVideoDisc(dvd2);
		cart1.addDigitalVideoDisc(dvd3);
		
		System.out.println("Total cost is: " + cart1.totalCost() + "$");
		//End Test 12
		
		//Test 13
		cart1.addDigitalVideoDisc(dvd1);
		cart1.addDigitalVideoDisc(dvd1);
		cart1.addDigitalVideoDisc(dvd1);
		cart1.addDigitalVideoDisc(dvd2);
		cart1.addDigitalVideoDisc(dvd3);
		
		System.out.printf("Before removing:\n");
		cart1.displayCart();
		
		cart1.removeDigitalVideoDisc(dvd2);
		cart1.removeDigitalVideoDisc(dvd1);
		cart1.removeDigitalVideoDisc(dvd1);
		
		System.out.printf("After removing:\n");
		cart1.displayCart();
		//End Test 13
		
		//Test 16
		System.out.println("DVD 1 ID: " + dvd1.getId());
	    System.out.println("DVD 2 ID: " + dvd2.getId());
	    System.out.println("Total DVDs created: " + DigitalVideoDisc.getTotalDVDs());
	}
}
