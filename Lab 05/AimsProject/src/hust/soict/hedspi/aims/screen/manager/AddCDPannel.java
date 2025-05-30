package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.exception.InvalidInputException;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.store.Store;

import javax.naming.LimitExceededException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddCDPannel extends JPanel implements ActionListener {
    private final Store store;
    private final StoreManagerScreen screen;
    private JTextField titleField;
    private JTextField categoryField;
    private JTextField priceField;
    private JTextField artistField;
    private JTextField directorField;
    private JTextField lengthField;
    private JTextField numTracksField;
    private JPanel tracksPanelContainer;
    private JButton addButton;

    public AddCDPannel(Store store, StoreManagerScreen screen) {
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
        add(new JLabel("Artist:"), gbc);
        gbc.gridx = 1;
        add(artistField = new JTextField(20), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Director:"), gbc);
        gbc.gridx = 1;
        add(directorField = new JTextField(20), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Length:"), gbc);
        gbc.gridx = 1;
        add(lengthField = new JTextField(20), gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("Number of Tracks:"), gbc);
        gbc.gridx = 1;
        add(numTracksField = new JTextField(5), gbc);
        numTracksField.addActionListener(e -> updateTrackFields());

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        tracksPanelContainer = new JPanel(new GridLayout(0, 1, 5, 5));
        JScrollPane tracksScrollPane = new JScrollPane(tracksPanelContainer);
        add(new JLabel("Tracks:"), gbc);
        gbc.gridy = 8;
        add(tracksScrollPane, gbc);
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;

        gbc.gridx = 1;
        gbc.gridy = 9;
        addButton = new JButton("Add CD");
        addButton.addActionListener(this);
        add(addButton, gbc);
    }

    private void updateTrackFields() {
        try {
            int numTracks = Integer.parseInt(numTracksField.getText());
            tracksPanelContainer.removeAll();

            for (int i = 0; i < numTracks; i++) {
                JPanel trackPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
                JTextField titleField = new JTextField(15);
                JTextField lengthField = new JTextField(5);
                trackPanel.add(new JLabel("Track " + (i + 1) + " Title:"));
                trackPanel.add(titleField);
                trackPanel.add(new JLabel("Length:"));
                trackPanel.add(lengthField);
                tracksPanelContainer.add(trackPanel);
            }
            tracksPanelContainer.revalidate();
            tracksPanelContainer.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid track number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String title = titleField.getText().trim();
            String category = categoryField.getText().trim();
            String artist = artistField.getText().trim();
            String director = directorField.getText().trim();
            float price;
            int length;
            List<Track> tracks = new ArrayList<>();

            try {
                // Check empty fields
                if (title.isEmpty() || category.isEmpty() || artist.isEmpty() || director.isEmpty()) {
                    throw new InvalidInputException("Title, category, artist, and director cannot be empty.");
                }

                // Validate price
                try {
                    price = Float.parseFloat(priceField.getText().trim());
                    if (price < 0) throw new InvalidInputException("Price must be non-negative.");
                } catch (NumberFormatException ex) {
                    throw new InvalidInputException("Price must be a valid number.");
                }

                // Validate length
                try {
                    length = Integer.parseInt(lengthField.getText().trim());
                    if (length < 0) throw new InvalidInputException("Length must be non-negative.");
                } catch (NumberFormatException ex) {
                    throw new InvalidInputException("Length must be a valid integer.");
                }

                // Validate number of tracks
                int numTracks;
                try {
                    numTracks = Integer.parseInt(numTracksField.getText().trim());
                    if (numTracks <= 0) throw new InvalidInputException("Number of tracks must be positive.");
                } catch (NumberFormatException ex) {
                    throw new InvalidInputException("Number of tracks must be a valid integer.");
                }

                if (tracksPanelContainer.getComponentCount() != numTracks) {
                    throw new InvalidInputException("Mismatch between track number and track fields.");
                }

                for (Component comp : tracksPanelContainer.getComponents()) {
                    if (comp instanceof JPanel trackPanel) {
                        JTextField trackTitleField = (JTextField) trackPanel.getComponent(1);
                        JTextField trackLengthField = (JTextField) trackPanel.getComponent(3);

                        String trackTitle = trackTitleField.getText().trim();
                        if (trackTitle.isEmpty()) {
                            throw new InvalidInputException("Track title cannot be empty.");
                        }

                        int trackLength;
                        try {
                            trackLength = Integer.parseInt(trackLengthField.getText().trim());
                            if (trackLength < 0) throw new InvalidInputException("Track length must be non-negative.");
                        } catch (NumberFormatException ex) {
                            throw new InvalidInputException("Track length must be a valid integer.");
                        }

                        tracks.add(new Track(trackTitle, trackLength));
                    }
                }

                CompactDisc cd = new CompactDisc(
                        store.getNumberOfMedia() + 1, title, category, price, artist, tracks
                );
                store.addMedia(cd);
                screen.switchCenterPanel(screen.createCenter());

            } catch (InvalidInputException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } catch (LimitExceededException ex) {
                JOptionPane.showMessageDialog(this, "Exceed Media limits", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}