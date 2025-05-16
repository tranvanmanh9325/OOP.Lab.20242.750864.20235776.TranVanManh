package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import java.util.ArrayList;

import hust.soict.hedspi.aims.media.Media;
import javax.swing.*;

import hust.soict.hedspi.aims.store.Store;

public class StoreManagerScreen extends JFrame {
	private Store store;
	
	JPanel createNorth()
	{
		JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		north.add(createMenuBar());
		north.add(createHeader());
		return north;
	}
	JMenuBar createMenuBar()
	{
		JMenu menu = new JMenu("Options");
		
		menu.add(new JMenuItem("View Store"));
		
		JMenu smUpdateStore = new JMenu("Update Store");
		smUpdateStore.add(new JMenuItem("Add Book"));
		smUpdateStore.add(new JMenuItem("Add CD"));
		smUpdateStore.add(new JMenuItem("Add DVD"));
		menu.add(smUpdateStore);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuBar.add(menu);
		
		return menuBar;
	}
	JPanel createHeader()
	{
		JPanel header = new JPanel();
		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		
		JLabel title = new JLabel("AIMS");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
		title.setForeground(Color.CYAN);
		
		header.add(Box.createRigidArea(new Dimension(10, 10)));
		header.add(title);
		header.add(Box.createHorizontalGlue());
		header.add(Box.createRigidArea(new Dimension(10, 10)));
		
		return header;
	}
	JPanel createCenter()
	{
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(3, 3, 2, 2));
		
		ArrayList<Media> mediaInStore = store.getItemsInStore();
		for(int i = 0; i < 9; ++i) {
			if (i < mediaInStore.size()) {
		        center.add(new MediaStore(mediaInStore.get(i)));
		    } else {
		        center.add(new JPanel()); 
		    }
		}
		
		return center;
	}
	public StoreManagerScreen(Store store) {
		this.store = store;
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(createNorth(), BorderLayout.NORTH);
		cp.add(createCenter(), BorderLayout.CENTER);
		
		setTitle("Store");
		setSize(2560, 1440);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
