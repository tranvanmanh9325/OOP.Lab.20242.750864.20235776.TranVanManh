package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.exception.DuplicateAuthorException;
import hust.soict.hedspi.aims.exception.InvalidInputException;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;

import javax.naming.LimitExceededException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddBookPannel extends JPanel implements ActionListener {
    private final Store store;
    private final StoreManagerScreen screen;
    private JTextField titleField;
    private JTextField categoryField;
    private JTextField priceField;
    private JTextField numAuthorsField;
    private List<JTextField> authorFields = new ArrayList<>();
    private JButton addButton;

    public AddBookPannel(Store store, StoreManagerScreen screen) {
        this.store = store;
        this.screen = screen;
        setLayout(new GridLayout(6, 2, 5, 5));

        add(new JLabel("Title:"));
        titleField = new JTextField(20);
        add(titleField);

        add(new JLabel("Category:"));
        categoryField = new JTextField(20);
        add(categoryField);

        add(new JLabel("Price:"));
        priceField = new JTextField(20);
        add(priceField);

        add(new JLabel("Number of Authors:"));
        numAuthorsField = new JTextField(5);
        numAuthorsField.addActionListener(e -> updateAuthorFields());
        add(numAuthorsField);

        add(new JLabel("Authors:"));
        JPanel authorsPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        add(authorsPanel);

        addButton = new JButton("Add Book");
        addButton.addActionListener(this);
        add(new JLabel(""));
        add(addButton);
    }

    private void updateAuthorFields() {
        try {
            int numAuthors = Integer.parseInt(numAuthorsField.getText());
            JPanel authorsPanel = (JPanel) getComponent(9);
            authorsPanel.removeAll();
            authorFields.clear();

            for (int i = 0; i < numAuthors; i++) {
                JTextField authorField = new JTextField(20);
                authorsPanel.add(new JLabel("Author " + (i + 1) + ":"));
                authorsPanel.add(authorField);
                authorFields.add(authorField);
            }

            revalidate();
            repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for authors.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == addButton) {
            String title = titleField.getText().trim();
            String category = categoryField.getText().trim();
            float price;
            int numAuthors;
            List<String> authors = new ArrayList<>();

            try {
                // Validate empty title or category
                if (title.isEmpty() || category.isEmpty()) {
                    throw new InvalidInputException("Title and category cannot be empty.");
                }

                // Validate price
                try {
                    price = Float.parseFloat(priceField.getText().trim());
                    if (price < 0) {
                        throw new InvalidInputException("Price cannot be negative.");
                    }
                } catch (NumberFormatException e) {
                    throw new InvalidInputException("Price must be a valid number.");
                }

                // Validate number of authors
                try {
                    numAuthors = Integer.parseInt(numAuthorsField.getText().trim());
                    if (numAuthors <= 0) {
                        throw new InvalidInputException("Number of authors must be greater than 0.");
                    }
                } catch (NumberFormatException e) {
                    throw new InvalidInputException("Number of authors must be a valid integer.");
                }

                Book book = new Book(store.getNumberOfMedia() + 1, title, category, price, authors);

                for (JTextField authorField : authorFields) {
                    String authorName = authorField.getText().trim();
                    if (authorName.isEmpty()) {
                        throw new InvalidInputException("Author name cannot be empty.");
                    }
                    book.addAuthor(authorName);
                }

                store.addMedia(book);
                screen.switchCenterPanel(screen.createCenter());

            } catch (InvalidInputException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } catch (LimitExceededException ex) {
                JOptionPane.showMessageDialog(this, "ExceedMediaLimit", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (DuplicateAuthorException ex) {
                JOptionPane.showMessageDialog(this, "Author Duplicated", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
