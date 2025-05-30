package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ItemController {

    private Cart cart;
    private Store store;
    private Media media;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlay;

    @FXML
    private Label lblCost;

    @FXML
    private Label lblTitle;

    public ItemController(Cart cart, Store store) {
        this.cart = cart;
        this.store = store;
    }

    public void setData(Media media) {
        this.media = media;
        lblTitle.setText(media.getTitle());
        lblCost.setText(media.getCost()+" $");
        if(media instanceof Playable){
            btnPlay.setVisible(true);
        } else {
            btnPlay.setVisible(false);
            HBox.setMargin(btnAddToCart, new Insets(0,0,0,100));
        }
    }

    @FXML
    void btnAddToCartClicked(ActionEvent event) {
        cart.addMedia(media);
    }

    @FXML
    void btnPlayClicked(ActionEvent event) {
        showPlayDialog((Playable) media);
    }

    private void showPlayDialog(Playable playable) {
        Stage playStage = new Stage();
        playStage.initModality(Modality.APPLICATION_MODAL);
        playStage.setTitle("Playing Media");

        VBox dialogVBox = new VBox(10);
        dialogVBox.setPadding(new Insets(10));

        Label playLabel = new Label("Now playing:");
        TextArea playTextArea = new TextArea();
        playTextArea.setEditable(false);
        playTextArea.setWrapText(true);

        String content = "";
        try {
            if (playable instanceof DigitalVideoDisc) {
                DigitalVideoDisc dvd = (DigitalVideoDisc) playable;
                content = "Playing DVD: " + dvd.getTitle() + "\nDVD length: " + dvd.getLength() + " minutes";
                dvd.play();
            } else if (playable instanceof CompactDisc) {
                CompactDisc cd = (CompactDisc) playable;
                StringBuilder sb = new StringBuilder("Playing CD: " + cd.getTitle() + "\nArtist: " + cd.getArtist() + "\nTotal length: " + cd.getLength() + " seconds\nTracks:\n");
                if (cd.getTracks() != null) {
                    for (Track track : cd.getTracks()) {
                        sb.append("- ").append(track.getTitle()).append(" (").append(track.getLength()).append(" sec)\n");
                    }
                }
                cd.play();
                content = sb.toString();
            } else if (playable instanceof Track) {
                Track track = (Track) playable;
                content = "Playing track: " + track.getTitle() + "\nTrack length: " + track.getLength() + " seconds";
                track.play();
            } else {
                content = "Cannot play this media type.";
            }
        } catch (Exception e) {
            content = "Error playing media: " + e.getMessage();
            e.printStackTrace();
        }
        playTextArea.setText(content);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> playStage.close());

        dialogVBox.getChildren().addAll(playLabel, playTextArea, closeButton);

        Scene playScene = new Scene(dialogVBox, 400, 300);
        playStage.setScene(playScene);
        playStage.showAndWait();
    }

}
