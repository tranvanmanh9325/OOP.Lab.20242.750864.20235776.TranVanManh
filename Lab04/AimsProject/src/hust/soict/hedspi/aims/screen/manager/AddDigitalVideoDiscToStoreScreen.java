package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {

    public AddDigitalVideoDiscToStoreScreen(Store currentStore) {
        super(currentStore);
    }

    @Override
    protected JPanel buildCenterPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        JLabel lblTitle = new JLabel("Title:");
        JTextField txtTitle = new JTextField();

        JLabel lblCategory = new JLabel("Category:");
        JTextField txtCategory = new JTextField();

        JLabel lblDirector = new JLabel("Director:");
        JTextField txtDirector = new JTextField();

        JLabel lblLength = new JLabel("Length:");
        JTextField txtLength = new JTextField();

        JLabel lblCost = new JLabel("Cost:");
        JTextField txtCost = new JTextField();

        JButton btnAdd = new JButton("Add DVD");

        btnAdd.addActionListener((ActionEvent evt) -> {
            try {
                String title = txtTitle.getText();
                String category = txtCategory.getText();
                String director = txtDirector.getText();
                int length = Integer.parseInt(txtLength.getText());
                float cost = Float.parseFloat(txtCost.getText());

                DigitalVideoDisc newDisc = new DigitalVideoDisc(title, category, director, length, cost);
                currentStore.addMedia(newDisc);

                dispose();
                new StoreManagerScreen(currentStore);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for Length and Cost.");
            }
        });

        inputPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        inputPanel.add(lblTitle);
        inputPanel.add(txtTitle);

        inputPanel.add(lblCategory);
        inputPanel.add(txtCategory);

        inputPanel.add(lblDirector);
        inputPanel.add(txtDirector);

        inputPanel.add(lblLength);
        inputPanel.add(txtLength);

        inputPanel.add(lblCost);
        inputPanel.add(txtCost);

        inputPanel.add(new JLabel());
        inputPanel.add(btnAdd);

        return inputPanel;
    }
}
