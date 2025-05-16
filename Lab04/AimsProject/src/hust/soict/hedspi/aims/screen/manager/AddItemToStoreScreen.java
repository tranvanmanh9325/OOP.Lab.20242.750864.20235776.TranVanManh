package hust.soict.hedspi.aims.screen.manager;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import hust.soict.hedspi.aims.store.Store;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store currentStore;

    public AddItemToStoreScreen(Store currentStore) {
        this.currentStore = currentStore;
        setTitle("Add Item");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setupLayout();

        setVisible(true);
    }

    private void setupLayout() {
        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());
        mainContainer.add(buildNorthPanel(), BorderLayout.NORTH);
        mainContainer.add(buildCenterPanel(), BorderLayout.CENTER);
    }

    private JPanel buildNorthPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        StoreManagerScreen managerUI = new StoreManagerScreen(currentStore);
        topPanel.add(managerUI.createMenuBar());
        topPanel.add(managerUI.createHeader());

        return topPanel;
    }

    protected abstract JPanel buildCenterPanel(); // Implement in subclasses
}
