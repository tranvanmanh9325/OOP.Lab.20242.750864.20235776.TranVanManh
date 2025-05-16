package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.store.Store;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {

    public AddCompactDiscToStoreScreen(Store currentStore) {
        super(currentStore);
    }

    @Override
    protected JPanel buildCenterPanel() {
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));

        JLabel lblTitle = new JLabel("Title:");
        JTextField txtTitle = new JTextField();

        JLabel lblCategory = new JLabel("Category:");
        JTextField txtCategory = new JTextField();

        JLabel lblCost = new JLabel("Cost:");
        JTextField txtCost = new JTextField();

        JLabel lblDirector = new JLabel("Director:");
        JTextField txtDirector = new JTextField();

        JLabel lblLength = new JLabel("Length:");
        JTextField txtLength = new JTextField();

        JLabel lblArtist = new JLabel("Artist:");
        JTextField txtArtist = new JTextField();

        JButton btnAddCd = new JButton("Add CD");
        btnAddCd.addActionListener((ActionEvent evt) -> {
            try {
                String title = txtTitle.getText();
                String category = txtCategory.getText();
                float cost = Float.parseFloat(txtCost.getText());
                String director = txtDirector.getText();
                int length = Integer.parseInt(txtLength.getText());
                String artist = txtArtist.getText();
                
                int id = currentStore.getItemsInStore().size() + 1;
                CompactDisc newCd = new CompactDisc(id, title, category, cost, director, length, artist);
                currentStore.addMedia(newCd);

                JOptionPane.showMessageDialog(this, "CD added to store.");
                dispose();
                new StoreManagerScreen(currentStore);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for Length and Cost.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        formPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        formPanel.add(lblTitle);
        formPanel.add(txtTitle);

        formPanel.add(lblCategory);
        formPanel.add(txtCategory);

        formPanel.add(lblCost);
        formPanel.add(txtCost);

        formPanel.add(lblDirector);
        formPanel.add(txtDirector);

        formPanel.add(lblLength);
        formPanel.add(txtLength);

        formPanel.add(lblArtist);
        formPanel.add(txtArtist);

        formPanel.add(new JLabel());
        formPanel.add(btnAddCd);

        return formPanel;
    }
}
