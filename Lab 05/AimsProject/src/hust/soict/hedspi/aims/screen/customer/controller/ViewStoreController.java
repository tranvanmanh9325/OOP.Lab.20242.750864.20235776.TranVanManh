package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.screen.customer.ViewCustomerScreen;
import hust.soict.hedspi.aims.screen.manager.StoreManagerScreen;
import hust.soict.hedspi.aims.store.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewStoreController {
    @FXML
    private GridPane gridPane;

    @FXML
    private Button switchToManagerButton;

    private Cart cart ;
    private Store store ;

    public ViewStoreController(Store store, Cart cart) {
        this.cart = cart;
        this.store = store;
    }

    @FXML
    public void initialize(){
        final String ITEM_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Item.fxml";
        int column = 0;
        int row = 1;
        for( int i=0; i<store.getItemsInStore().size(); i++){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(ViewStoreController.class.getResource(ITEM_FXML_FILE_PATH));
                ItemController itemController = new ItemController(cart, store);
                fxmlLoader.setController(itemController);
                AnchorPane anchorPane = new AnchorPane();
                anchorPane = fxmlLoader.load();
                itemController.setData(store.getItemsInStore().get(i));

                if(column == 3 ){
                    column = 0;
                    row++;
                }
                column++;
                gridPane.add(anchorPane, column, row);

                GridPane.setMargin(gridPane, new Insets(20, 10, 10, 10));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void btnViewCartPressed(ActionEvent event) {
        try {
            final String CART_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Cart.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(CART_FXML_FILE_PATH));
            fxmlLoader.setController(new CartController(cart, this));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Cart");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSwitchToManager() {
        Stage stage = (Stage) switchToManagerButton.getScene().getWindow();
        stage.close();

        javax.swing.SwingUtilities.invokeLater(() -> {
            new StoreManagerScreen(ViewCustomerScreen.sharedStore, ViewCustomerScreen.sharedCart);
        });
    }
}
