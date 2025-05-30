package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.screen.customer.ViewCustomerScreen;
import hust.soict.hedspi.aims.store.Store;
import javafx.application.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StoreManagerScreen extends JFrame {
    private final Store store;
    private final Cart cart;
    private JPanel centerPanel;

    public StoreManagerScreen(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);
        centerPanel = createCenter();
        cp.add(centerPanel, BorderLayout.CENTER);

        setTitle("Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenuItem viewStore = new JMenuItem("View store");
        viewStore.addActionListener(e -> switchCenterPanel(createCenter()));
        menu.add(viewStore);

        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem addBook = new JMenuItem("Add Book");
        addBook.addActionListener(new AddBookListener());
        smUpdateStore.add(addBook);
        JMenuItem addCD = new JMenuItem("Add CD");
        addCD.addActionListener(new AddCDListener());
        smUpdateStore.add(addCD);
        JMenuItem addDVD = new JMenuItem("Add DVD");
        addDVD.addActionListener(new AddDVDListener());
        smUpdateStore.add(addDVD);
        menu.add(smUpdateStore);

        JButton switchToCustomerButton = new JButton("Switch to Customer Screen");
        switchToCustomerButton.addActionListener(e -> {
            this.dispose();

            ViewCustomerScreen.sharedStore = this.store;
            ViewCustomerScreen.sharedCart = this.cart;
            Application.launch(ViewCustomerScreen.class);
        });
        menu.add(switchToCustomerButton);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    JPanel createHeader() {
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

    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));

        List<Media> mediaInStore = store.getItemsInStore();
        for (Media media : mediaInStore) {
            MediaStore cell = new MediaStore(media);
            cell.getFrame(this);
            center.add(cell);
        }
        return center;
    }

    public void switchCenterPanel(JPanel newPanel) {
        getContentPane().remove(centerPanel);
        centerPanel = newPanel;
        getContentPane().add(centerPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    class AddBookListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switchCenterPanel(new AddBookPannel(store, StoreManagerScreen.this));
        }
    }

    class AddCDListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switchCenterPanel(new AddCDPannel(store, StoreManagerScreen.this));
        }
    }

    class AddDVDListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switchCenterPanel(new AddDVDPanel(store, StoreManagerScreen.this));
        }
    }
}