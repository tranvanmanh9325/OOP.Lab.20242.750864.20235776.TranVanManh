package hust.soict.hedspi.test.cart;

import java.util.Scanner;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;

public class CartTest {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
    	// Create a new cart
        Cart cart = new Cart();

        // Create a new dvd objects and add them to the cart 
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(
            "The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        cart.addMedia(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc(
            "Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        cart.addMedia(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc(
            "Aladin", "Animation", 18.99f);
        cart.addMedia(dvd3);

        // Test the print method
        cart.print();

        // To-do: Test the search method here
        System.out.println("\nSearch by ID:");
        int id;
        id = kb.nextInt();
        cart.searchById(id);
        
        kb.nextLine();

        System.out.println("\nSearch by Title:");
        String title = new String();
        title = kb.nextLine();
        cart.searchByTitle(title);
    }
}
