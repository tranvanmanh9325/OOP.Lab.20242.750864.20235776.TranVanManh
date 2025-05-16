package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;

public class AddBookToStoreScreen extends AddItemToStoreScreen {

    public AddBookToStoreScreen(Store currentStore) {
        super(currentStore);
    }

    @Override
    protected JPanel buildCenterPanel() {
        JPanel bookForm = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel lblTitle = new JLabel("Title:");
        JTextField txtTitle = new JTextField();

        JLabel lblCategory = new JLabel("Category:");
        JTextField txtCategory = new JTextField();

        JLabel lblCost = new JLabel("Cost:");
        JTextField txtCost = new JTextField();

        JButton btnAddBook = new JButton("Add Book");
        btnAddBook.addActionListener((ActionEvent event) -> {
            try {
                String title = txtTitle.getText();
                String category = txtCategory.getText();
                float cost = Float.parseFloat(txtCost.getText());

                int id = currentStore.getItemsInStore().size() + 1;
                Book newBook = new Book(id, title, category, cost);
                currentStore.addMedia(newBook);

                JOptionPane.showMessageDialog(this, "Book successfully added!");
                dispose();
                new StoreManagerScreen(currentStore);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for cost.");
            }
        });

        bookForm.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        bookForm.add(lblTitle);
        bookForm.add(txtTitle);
        bookForm.add(lblCategory);
        bookForm.add(txtCategory);
        bookForm.add(lblCost);
        bookForm.add(txtCost);
        bookForm.add(new JLabel());
        bookForm.add(btnAddBook);

        return bookForm;
    }
}
