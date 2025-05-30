package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MediaStore extends JPanel {
    private Media media;
    private JFrame frame;

    public MediaStore(Media media) {
        this.media = media;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 15));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel("+" + media.getCost() + "$");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(new PlayButtonListener((Playable) media));
            container.add(playButton);
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void getFrame(JFrame frame) {
        this.frame = frame;
    }

    class PlayButtonListener implements ActionListener {
        private Playable playable;

        public PlayButtonListener(Playable playable) {
            this.playable = playable;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            createAndShowPlayDialog(playable);
        }
    }

    private void createAndShowPlayDialog(Playable playable) {
        JDialog playDialog = new JDialog(frame, "Playing Media", true);
        playDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        playDialog.setLayout(new FlowLayout());

        JLabel playLabel = new JLabel("Now playing: ");
        JTextArea playTextArea = new JTextArea();
        playTextArea.setEditable(false);

        if (playable instanceof DigitalVideoDisc) {
            DigitalVideoDisc dvd = (DigitalVideoDisc) playable;
            playTextArea.setText("Playing DVD: " + dvd.getTitle() + "\nDVD length: " + dvd.getLength() + " minutes");
        } else if (playable instanceof CompactDisc) {
            CompactDisc cd = (CompactDisc) playable;
            StringBuilder sb = new StringBuilder("Playing CD: " + cd.getTitle() + "\nArtist: " + cd.getArtist() + "\nTotal length: " + cd.getLength() + " seconds\nTracks:\n");
            for (Track track : cd.getTracks()) {
                sb.append("- ").append(track.getTitle()).append(" (").append(track.getLength()).append(" sec)\n");
            }
            playTextArea.setText(sb.toString());
        } else if (playable instanceof Track) {
            Track track = (Track) playable;
            playTextArea.setText("Playing track: " + track.getTitle() + "\nTrack length: " + track.getLength() + " seconds");
        } else {
            playTextArea.setText("Cannot play this media type.");
        }

        playDialog.add(playLabel);
        playDialog.add(new JScrollPane(playTextArea)); // Add scroll for potentially long track lists

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> playDialog.dispose());
        playDialog.add(closeButton);

        playDialog.pack();
        playDialog.setLocationRelativeTo(this);
        playDialog.setVisible(true);
    }
}

