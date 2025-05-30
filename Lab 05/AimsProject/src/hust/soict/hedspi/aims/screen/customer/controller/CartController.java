package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class CartController {

    private Cart cart;
    private ViewStoreController viewStoreController;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private TableColumn<Media, Integer> colMediaId;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private Label costLabel;

    @FXML
    private ToggleGroup filterCategory;

    @FXML
    private TextField filterTextField;
    @FXML
    private RadioButton radioBtnFilterId;
    @FXML
    private RadioButton radioBtnFilterTitle;

    private FilteredList<Media> filteredList;

    public CartController ( Cart cart, ViewStoreController viewStoreController) {
        this.cart = cart;
        this.viewStoreController = viewStoreController;
    }

    @FXML
    public void initialize() {
        colMediaId.setCellValueFactory(new PropertyValueFactory<Media, Integer>("id"));
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));

        if(cart.getItemsOrdered() != null){
            filteredList = new FilteredList<>(cart.getItemsOrdered(), p -> true);
            tblMedia.setItems(filteredList);
            updateTotalCostLabel();
        }

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Media>() {
            @Override
            public void changed(ObservableValue<? extends Media> observableValue, Media oldValue, Media newValue) {
                updateButtonBar(newValue);
            }
        });

        if (cart.getItemsOrdered() instanceof javafx.collections.ObservableList) {
            cart.getItemsOrdered().addListener((ListChangeListener<Media>) change -> {
                updateTotalCostLabel();
            });
        }

        filterTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filterMedia();
            }
        });

        filterCategory.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                filterMedia();
            }
        });
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
        if (selectedMedia instanceof Playable) {
            Playable playable = (Playable) selectedMedia;
            showPlayDialog(playable);
        }
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null) {
            cart.removeMedia(media);
            tblMedia.setItems(cart.getItemsOrdered());
        }
    }

    @FXML
    void btnViewStorePressed(ActionEvent event) {
        try {
            final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
            fxmlLoader.setController(viewStoreController);
            Parent root = fxmlLoader.load();
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Store");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void updateButtonBar(Media media) {
        if(media==null){
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
        } else {
            btnRemove.setVisible(true);
            if(media instanceof Playable){
                btnPlay.setVisible(true);
            } else {
                btnPlay.setVisible(false);
            }
        }
    }

    @FXML
    void placeOrderPressed(ActionEvent event) {
        if (cart.getItemsOrdered().isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Order Information", "Cart is Empty", "There are no items in your cart to place an order.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Place Order Confirmation");
        alert.setHeaderText("Confirm your order?");
        alert.setContentText("Total cost: " + String.format("%.2f $", cart.totalCost()) + "\nAre you sure you want to place this order?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            cart.emptyCart();
            tblMedia.setItems(cart.getItemsOrdered());

            showAlert(Alert.AlertType.INFORMATION, "Order Placed", "Order Successful!", "Your order has been placed successfully!");

            btnViewStorePressed(event);
        } else {
            showAlert(Alert.AlertType.INFORMATION, "Order Cancelled", "Order Not Placed", "Your order has been cancelled.");
        }
    }

    @FXML
    void filterMedia() {
        String searchText = filterTextField.getText().toLowerCase();
        Toggle selectedToggle = filterCategory.getSelectedToggle();

        filteredList.setPredicate(media -> {
            if (searchText == null || searchText.isEmpty()) {
                return true;
            }

            if (selectedToggle == radioBtnFilterId) {
                return String.valueOf(media.getId()).contains(searchText);
            } else if (selectedToggle == radioBtnFilterTitle) {
                return media.getTitle().toLowerCase().contains(searchText);
            }
            return false;
        });
    }

    private void updateTotalCostLabel() {
        if (costLabel != null) {
            costLabel.setText(String.format("%.2f $", cart.totalCost()));
        }
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

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}