package hust.soict.hedspi.aims.screen.customer;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.screen.customer.controller.ViewStoreController;
import hust.soict.hedspi.aims.store.Store;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewCustomerScreen extends Application {
    public static Store sharedStore;
    public static Cart sharedCart;

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String STORE_FXML_FILE_PATH = "/src/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
        ViewStoreController viewStoreController = new ViewStoreController(sharedStore, sharedCart);
        fxmlLoader.setController(viewStoreController);
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Store");
        primaryStage.setScene(new Scene(root, 1150, 800));
        primaryStage.show();
    }

    public static void main(String[] args) {
        sharedStore = new Store();
        sharedCart = new Cart();
        launch(args);
    }

    public static void launchScreen() {
        Platform.runLater(() -> {
            try {
                ViewCustomerScreen screen = new ViewCustomerScreen();
                Stage stage = new Stage();
                screen.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}

