package controller.windowBeforeGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowBeforeGameStartedController {

    public void startGameButtonPushed(ActionEvent event) throws IOException {

        StackPane gameViewParent = FXMLLoader.load(getClass().getResource("/fxmlFiles/GameWindow2.fxml"));
        Scene gameSceneView = new Scene(gameViewParent);
        Stage gameWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        gameWindow.setScene(gameSceneView);
        gameWindow.show();
    }
}
