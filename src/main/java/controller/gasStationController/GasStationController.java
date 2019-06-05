package controller.gasStationController;

import javafx.animation.PauseTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import vehicle.VehiclePosition;
import vehicle.VehicleStatus;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static car.Car.getInstanceOfCar;
import static gasStation.GasStation.getInstanceOfGasStation;
import static user.User.getInstanceOfUser;

public class GasStationController implements Initializable {

    private int onFoot;

    @FXML
    Button fillButton, backOnStreetButton;

    @FXML
    Label moneyLabel, fuelPriceLabel, timeToEndThreadLabel;

    private static GasStationController instance;
    public static synchronized GasStationController getInstanceOfGasStationController() {
        if (instance == null) {
            instance = new GasStationController();
        }
        return instance;
    }

    private void remainingTimeToGoOnGasStationOnFoot() {
        Task task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                fillButton.setDisable(true);
                backOnStreetButton.setDisable(true);
                final int max = 21;
                int sec = 20;
                for (int i = 1; i <= max; i++) {
                    sec--;
                    if (isCancelled()) {
                        break;
                    } else if (i == 17) {
                        fillButton.setDisable(false);
                        backOnStreetButton.setDisable(false);
                        timeToEndThreadLabel.setOpacity(0);
                    }
                    updateMessage("Remaining time " + sec + " seconds");
                    System.out.println("index = " + i);
                    Thread.sleep(1000);
                }
                return null;
            }

        };
        timeToEndThreadLabel.textProperty().bind(task.messageProperty());
        new Thread(task).start();
    }

    public void fillFuelButtonPushed(){
        getInstanceOfGasStation().fillFuel();
    }

    public void backOnTheStreetPushed(ActionEvent event) throws IOException {

        getInstanceOfCar().setStatus(VehicleStatus.MOVING);
        getInstanceOfCar().setPosition(VehiclePosition.STREET);

        StackPane gameViewParent = FXMLLoader.load(getClass().getResource("/fxmlFiles/GameWindow2.fxml"));
        Scene gameSceneView = new Scene(gameViewParent);
        Stage gameWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        gameWindow.setScene(gameSceneView);
        gameWindow.show();
    }

    private int getOnFoot() {
        return onFoot;
    }

    public void setOnFoot(int onFoot) {
        this.onFoot = onFoot;
    }

    public void initialize(URL location, ResourceBundle resources) {

        if (getInstanceOfGasStationController().getOnFoot() == 1) {
            remainingTimeToGoOnGasStationOnFoot();
        }

        PauseTransition moneyPrinter = new PauseTransition(Duration.seconds(1));
        moneyPrinter.setOnFinished((e) -> {
            double moneyRound = getInstanceOfUser().getMoney() * 100;
            moneyRound = Math.round(moneyRound);
            moneyRound /= 100;
            moneyLabel.setText(String.valueOf(moneyRound));
            moneyPrinter.playFromStart();

            double fuelPriceRound = getInstanceOfGasStation().getCurrentPrice() * 100;
            fuelPriceRound = Math.round(fuelPriceRound);
            fuelPriceRound /= 100;
            fuelPriceLabel.setText(String.valueOf(fuelPriceRound));
        });
        moneyPrinter.play();

        getInstanceOfGasStation().randomPrice(getInstanceOfGasStation().getMinPrice(), getInstanceOfGasStation().getMaxPrice());

        final Tooltip fuelToolTip = new Tooltip();
        fuelToolTip.setText("Click to refuel and pay");
        fillButton.setTooltip(fuelToolTip);

        final Tooltip backOnStreetTip = new Tooltip();
        backOnStreetTip.setText("Click to go back to the road");
        backOnStreetButton.setTooltip(backOnStreetTip);

        final Tooltip fuelPriceToolTop = new Tooltip();
        fuelPriceToolTop.setText("Remember that the price is different each time you are at the station");
        fuelPriceLabel.setTooltip(fuelPriceToolTop);
    }
}
