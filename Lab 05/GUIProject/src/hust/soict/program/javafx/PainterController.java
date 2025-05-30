package hust.soict.program.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class PainterController {
    private boolean isDrawing = true;

    @FXML
    private Pane drawingAreaPane ;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        if(isDrawing) {
            Circle newCircle = new Circle(event.getX(), event.getY(), 4, Color.BLACK);
            drawingAreaPane.getChildren().add(newCircle);
        } else {
            Circle newCircle = new Circle(event.getX(), event.getY(), 6, Color.WHITE);
            drawingAreaPane.getChildren().add(newCircle);
        }
    }

    @FXML
    void drawing(ActionEvent event) {
        isDrawing = true;
    }

    @FXML
    void erasing(ActionEvent event) {
        isDrawing = false;
    }
}
