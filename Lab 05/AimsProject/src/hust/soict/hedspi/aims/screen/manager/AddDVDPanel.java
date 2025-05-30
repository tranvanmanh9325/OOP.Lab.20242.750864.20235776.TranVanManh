package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.exception.InvalidInputException;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

import javax.naming.LimitExceededException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDVDPanel extends JPanel implements ActionListener {
    private final Store store;
    private final StoreManagerScreen screen;
    private JTextField titleField;
    private JTextField categoryField;
    private JTextField priceField;
    private JTextField directorField;
    private JTextField lengthField;
    private JButton addButton;

    public AddDVDPanel(Store store, StoreManagerScreen screen) {
        this.store = store;
        this.screen = screen;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Title:"), gbc);
        gbc.gridx = 1;
        add(titleField = new JTextField(20), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Category:"), gbc);
        gbc.gridx = 1;
        add(categoryField = new JTextField(20), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Price:"), gbc);
        gbc.gridx = 1;
        add(priceField = new JTextField(20), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Director:"), gbc);
        gbc.gridx = 1;
        add(directorField = new JTextField(20), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Length:"), gbc);
        gbc.gridx = 1;
        add(lengthField = new JTextField(20), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        addButton = new JButton("Add DVD");
        addButton.addActionListener(this);
        add(addButton, gbc);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String title = titleField.getText().trim();
            String category = categoryField.getText().trim();
            String director = directorField.getText().trim();
            float price;
            int length;

            try {
                // Empty field checks
                if (title.isEmpty() || category.isEmpty() || director.isEmpty()) {
                    throw new InvalidInputException("Title, category, and director cannot be empty.");
                }

                // Price validation
                try {
                    price = Float.parseFloat(priceField.getText().trim());
                    if (price < 0) {
                        throw new InvalidInputException("Price must be non-negative.");
                    }
                } catch (NumberFormatException ex) {
                    throw new InvalidInputException("Price must be a valid number.");
                }

                // Length validation
                try {
                    length = Integer.parseInt(lengthField.getText().trim());
                    if (length < 0) {
                        throw new InvalidInputException("Length must be non-negative.");
                    }
                } catch (NumberFormatException ex) {
                    throw new InvalidInputException("Length must be a valid integer.");
                }

                // Create DVD and add
                DigitalVideoDisc dvd = new DigitalVideoDisc(
                        store.getNumberOfMedia() + 1, title, category, price, director, length
                );
                store.addMedia(dvd);
                screen.switchCenterPanel(screen.createCenter());

            } catch (InvalidInputException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } catch (LimitExceededException ex) {
                JOptionPane.showMessageDialog(this, "Exceed Media limits", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
