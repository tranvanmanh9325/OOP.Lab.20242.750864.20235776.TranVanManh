
public class Cart {

		public static final int MAX_NUMBERS_ORDERED = 20;
		private DigitalVideoDisc itemsOrdered[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
		private int qtyOrdered = 0;
		public void addDigitalVideoDisc(DigitalVideoDisc disc)
		{
			if(qtyOrdered < MAX_NUMBERS_ORDERED)
			{
				itemsOrdered[qtyOrdered++] = disc;
			}
			else
			{
				System.out.println("The cart is full!");
			}
		}
		
		//Add DVD List
		public void addDigitalVideoDisc(DigitalVideoDisc[] dvdList) {
		    for (DigitalVideoDisc dvd : dvdList) {
		        addDigitalVideoDisc(dvd);
		    }
		}
		
		public void addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
		    addDigitalVideoDisc(dvd1);
		    addDigitalVideoDisc(dvd2);
		}

		
		public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
		    int index = -1;

		    for (int i = 0; i < qtyOrdered; i++) {
		        if (itemsOrdered[i].equals(disc)) { 
		            index = i;
		            break;
		        }
		    }

		
		    if (index != -1) {
		        for (int i = index; i < qtyOrdered - 1; i++) {
		            itemsOrdered[i] = itemsOrdered[i + 1];
		        }
		        itemsOrdered[qtyOrdered - 1] = null; 
		        qtyOrdered--; 
		        System.out.println(disc.getTitle() + " removed from the cart.");
		    } else {
		        System.out.println("Item not found in cart.");
		    }
		}
		
		public void displayCart() {
	        if (qtyOrdered == 0) {
	            System.out.println("The cart is empty.");
	        } else {
	            System.out.println("Current items in the cart:");
	            for (int i = 0; i < qtyOrdered; i++) {
	                System.out.println((i + 1) + ". " + itemsOrdered[i].getTitle() + " - " + itemsOrdered[i].getCost() + "$");
	            }
	        }
	    }

		
		public float totalCost()
		{
			float res = 0;
			for(int i = 0; i < qtyOrdered; ++i) {
				res += itemsOrdered[i].getCost();
			}
			return res;
		}
}
