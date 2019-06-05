package controller.gameWindowController;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import routs.Routs;
import routs.RoutsStatus;
import routs.currentRout.CheckRoutIsCompletedAndSetStatus;
import routs.routsGenerators.LongRoutsGenerator;
import routs.routsGenerators.MediumRoutsGenerator;
import routs.routsGenerators.ShortRoutGenerator;
import routs.routsGenerators.routsDistanceLength.RoundDistance;
import routs.routsGenerators.routsProfit.RoundProfit;
import threads.rout.RemainingDistanceThread;
import vehicle.VehiclePosition;
import vehicle.VehicleStatus;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static car.Car.getInstanceOfCar;
import static controller.gasStationController.GasStationController.getInstanceOfGasStationController;
import static routs.currentRout.CurrentRout.getInstanceOfCurrentRout;
import static routs.currentRout.FinishedRoutsList.getInstanceOfFinishedRoutsList;
import static user.User.getInstanceOfUser;

public class GameWindowController implements Initializable {

    private int deliveryPackCount = 1;
    private final Routs routs = new Routs();
    private final RoundDistance roundDistance = new RoundDistance();
    private final RoundProfit roundProfit = new RoundProfit();
    private final CheckRoutIsCompletedAndSetStatus isCompleted = new CheckRoutIsCompletedAndSetStatus();
    private boolean isRoutSelected = false;

    @FXML
    private Label speedLabel, mileageLabel, fuelLabel, moneyLabel, toGoLabel,
            routsTypeLabel, routsDistanceLabel, routsProfitLabel,
            remainingDistanceLabel, profitForRoutLabel;
    @FXML
    private Pane topPane, menuPane, possibleRoutsDetailsPane, currentRoutPane, finishedRoutPane;
    @FXML
    private Button driveToGasStationButton, goToGasStationButton, startEngineButton, stopEngineButton,
            shortRoutButton, mediumRoutButton, longRoutButton, deliveryThePackButton, takeMoneyButton, breakPedal, gasButton;
    @FXML
    private ImageView smoke, reserveIcon, shortRoutImage, mediumRoutImage, longRoutImage;

    private static GameWindowController instance;
    public static synchronized GameWindowController getInstOfGameWindCont() {
        if (instance == null) {
            instance = new GameWindowController();
        }
        return instance;
    }



    public void startEngineButtonPushed() {
        getInstanceOfCar().start();
    }

    public void stopEngineButtonPushed() {
        getInstanceOfCar().stop();
    }

    public void accelerationButtonPushed() {
        getInstanceOfCar().carAccelerateSpeed();
        smoke.setFitHeight(smoke.getX() + getInstanceOfCar().getCurrentSpeed() / 2);
        smoke.setFitWidth(smoke.getY() + getInstanceOfCar().getCurrentSpeed() / 2);
    }

    public void breakPedalButtonPushed() {
        getInstanceOfCar().carReduceSpeed();
        speedLabel.setText(getInstanceOfCar().getCurrentSpeed() + " km/h");
        smoke.setFitHeight(smoke.getX() + getInstanceOfCar().getCurrentSpeed());
        smoke.setFitWidth(smoke.getY() + getInstanceOfCar().getCurrentSpeed());
    }

    public void driveToGasStationButtonPushed(ActionEvent event) throws IOException {
        getInstanceOfGasStationController().setOnFoot(0);
        if (getInstanceOfCar().getStatus() == VehicleStatus.MOVING && getInstanceOfCar().getFuel() > 0) {
            getInstanceOfCar().driveCarToGasStation();
            System.out.println(getInstanceOfCar().getStatus());
        } else {
            getInstanceOfCar().error("Turn on engine or go on foot :D");
        }
        AnchorPane gameViewParent = FXMLLoader.load(getClass().getResource("/fxmlFiles/GasStation1.fxml"));
        Scene gameSceneView = new Scene(gameViewParent);
        Stage gameWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        gameWindow.setScene(gameSceneView);
        gameWindow.show();
    }

    public void toGasStationOnFootButtonPushed(ActionEvent event) throws IOException {

        getInstanceOfGasStationController().setOnFoot(1);

        getInstanceOfCar().setStatus(VehicleStatus.STOPPED);
        getInstanceOfCar().setPosition(VehiclePosition.GAS_STATION);

        AnchorPane gameViewParent = FXMLLoader.load(getClass().getResource("/fxmlFiles/GasStation1.fxml"));
        Scene gameSceneView = new Scene(gameViewParent);
        Stage gameWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        gameWindow.setScene(gameSceneView);
        gameWindow.show();
    }

    public void showAndHideRoutsButtons() {

        possibleRoutsDetailsPane.setOpacity(0);

        if (getDeliveryPackCount() == 1) {
            shortRoutImage.setOpacity(1);
            shortRoutButton.setDisable(false);
            shortRoutButton.setOpacity(1);

            mediumRoutImage.setOpacity(1);
            mediumRoutButton.setDisable(false);
            mediumRoutButton.setOpacity(1);

            longRoutImage.setOpacity(1);
            longRoutButton.setDisable(false);
            longRoutButton.setOpacity(1);

            setDeliveryPackCount(0);
        } else if (getDeliveryPackCount() == 0) {
            shortRoutImage.setOpacity(0);
            shortRoutButton.setDisable(true);
            shortRoutButton.setOpacity(0);

            mediumRoutImage.setOpacity(0);
            mediumRoutButton.setDisable(true);
            mediumRoutButton.setOpacity(0);

            longRoutImage.setOpacity(0);
            longRoutButton.setDisable(true);
            longRoutButton.setOpacity(0);

            setDeliveryPackCount(1);
        }
    }

    public void createShortRoutButtonPushed() {
        ShortRoutGenerator shortRoutGenerator = new ShortRoutGenerator();
        shortRoutGenerator.shortRoutGenerator();
        opacitySetterForPossibleRoutDetailPane();
    }

    public void createMediumRoutButtonPushed() {
        MediumRoutsGenerator mediumRoutsGenerator = new MediumRoutsGenerator();
        mediumRoutsGenerator.mediumRoutGenerator();
        opacitySetterForPossibleRoutDetailPane();
    }

    public void createLongRoutButtonPushed() {
        LongRoutsGenerator longRoutsGenerator = new LongRoutsGenerator();
        longRoutsGenerator.longRoutGenerator();
        opacitySetterForPossibleRoutDetailPane();
    }

    private void opacitySetterForPossibleRoutDetailPane() {
        possibleRoutsDetailsPane.setStyle("-fx-border-color: #000000");
        possibleRoutsDetailsPane.setStyle("-fx-background-color: #80dfff");
        possibleRoutsDetailsPane.setOpacity(1);
        routsTypeLabel.setText(String.valueOf(getInstOfGameWindCont().getRouts().getRoutsDistanceOptions()));
        routsDistanceLabel.setText(roundDistance.roundDistance() + " km");
        routsProfitLabel.setText(roundProfit.roundProfit(getInstOfGameWindCont().getRouts().getProfitForRouts()) + " $");
    }

    private void opacitySetterForCurrentRoutPane() {
        currentRoutPane.setOpacity(1);
        currentRoutPane.setStyle("-fx-border-color: #000000");
        currentRoutPane.setStyle("-fx-background-color: #d1e0e0");
    }

    public void acceptRoutButtonPushed() {
        isCompleted.checkRoutIsCompleteAndSetStatus();

        setRoutSelected(true);

        getInstanceOfCurrentRout().setRemainingDistance(getInstOfGameWindCont().getRouts().getDistance());
        getInstanceOfCurrentRout().setProfit(getInstOfGameWindCont().getRouts().getProfitForRouts());
        getInstanceOfCurrentRout().getCurrentRoutList().removeAll(getInstanceOfCurrentRout().getCurrentRoutList());
        getInstanceOfCurrentRout().getCurrentRoutList().add(getInstOfGameWindCont().getRouts());

        opacitySetterForCurrentRoutPane();
        possibleRoutsDetailsPane.setOpacity(0);

        RemainingDistanceThread thread = new RemainingDistanceThread();
        Thread distanceThread = new Thread(thread);
        distanceThread.start();

        profitForRoutLabel.setText(roundProfit.roundProfit(getRouts().getProfitForRouts()) + " $");
    }

    private void ifRoutSelectedDisableSMLButtonsSetter() {
        if (isRoutSelected()) {
            shortRoutButton.setDisable(true);
            mediumRoutButton.setDisable(true);
            longRoutButton.setDisable(true);
        } else {
            shortRoutButton.setDisable(false);
            mediumRoutButton.setDisable(false);
            longRoutButton.setDisable(false);
        }
    }

    private void showFinishedRoutPane() {
        if (getInstOfGameWindCont().getRouts().getRoutsStatus() == RoutsStatus.COMPLETED) {
            finishedRoutPane.setOpacity(1);
            takeMoneyButton.setDisable(false);
        }
    }

    public void takeMoneyButtonPushed() {
        getInstanceOfUser().setMoney(getInstanceOfUser().getMoney() + getInstanceOfCurrentRout().getProfit());
        getInstanceOfCurrentRout().setProfit(0);
        getInstanceOfFinishedRoutsList().getFinishedRoutsList().add(getInstanceOfCurrentRout().getCurrentRoutList().get(0));
        getInstanceOfCurrentRout().getCurrentRoutList().removeAll(getInstanceOfCurrentRout().getCurrentRoutList());
        currentRoutPane.setOpacity(0);
        takeMoneyButton.setDisable(true);
        setRoutSelected(false);
    }

    private void closeFinishedRoutPane() {
        if (!isRoutSelected) {
            finishedRoutPane.setOpacity(0);
        }
    }

    private void disableButtons() {
        if (getInstanceOfCurrentRout().getRemainingDistance() <= 0 && getInstanceOfCurrentRout().getProfit() > 0) {
            deliveryThePackButton.setDisable(true);
            driveToGasStationButton.setDisable(true);
            shortRoutButton.setDisable(true);
            mediumRoutButton.setDisable(true);
            longRoutButton.setDisable(true);
            stopEngineButton.setDisable(true);
            startEngineButton.setDisable(true);
            breakPedal.setDisable(true);
            gasButton.setDisable(true);
            goToGasStationButton.setDisable(true);
            getInstanceOfCar().setCurrentSpeed(0);
            getInstanceOfCar().setStatus(VehicleStatus.STOPPED);
        }
        else {
            deliveryThePackButton.setDisable(false);
            shortRoutButton.setDisable(false);
            mediumRoutButton.setDisable(false);
            longRoutButton.setDisable(false);
            stopEngineButton.setDisable(false);
            startEngineButton.setDisable(false);
            breakPedal.setDisable(false);
            gasButton.setDisable(false);
            goToGasStationButton.setDisable(false);
            driveToGasStationButton.setDisable(false);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////                                                                ///////////////////////////////
    /////////////////////                      PAUSE TRANSACTIONS METHODS                ///////////////////////////////
    /////////////////////                                                                ///////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void changeToGasStationButtonsIfNoFuelPT() {
        PauseTransition changeButtons = new PauseTransition(Duration.millis(10));
        changeButtons.setOnFinished((e) -> {

            if (getInstanceOfCar().getFuel() >= 0) {
                driveToGasStationButton.setOpacity(1);
                driveToGasStationButton.setDisable(false);
                driveToGasStationButton.setLayoutX(1074);
                driveToGasStationButton.setLayoutY(282);

                goToGasStationButton.setOpacity(0);
                goToGasStationButton.setDisable(true);
                goToGasStationButton.setLayoutX(1074);
                goToGasStationButton.setLayoutY(329);
            }
            if (getInstanceOfCar().getFuel() <= 0) {
                driveToGasStationButton.setOpacity(0);
                driveToGasStationButton.setDisable(true);
                driveToGasStationButton.setLayoutX(1074);
                driveToGasStationButton.setLayoutY(329);

                goToGasStationButton.setOpacity(1);
                goToGasStationButton.setDisable(false);
                goToGasStationButton.setLayoutX(1074);
                goToGasStationButton.setLayoutY(282);
            }
            changeButtons.playFromStart();
        });
        changeButtons.play();
    }

    private void changeStartStopButtonsPT() {
        PauseTransition startStopButtonsDetailsPT = new PauseTransition(Duration.millis(10));
        startStopButtonsDetailsPT.setOnFinished((e) -> {

            if (getInstanceOfCar().getStatus() == VehicleStatus.STOPPED) {

                stopEngineButton.setOpacity(0);
                stopEngineButton.setDisable(true);
                stopEngineButton.setLayoutX(744);
                stopEngineButton.setLayoutY(314);

                startEngineButton.setOpacity(1);
                startEngineButton.setDisable(false);
                startEngineButton.setLayoutX(744);
                startEngineButton.setLayoutY(396);
            }

            if (getInstanceOfCar().getStatus() == VehicleStatus.MOVING) {

                stopEngineButton.setOpacity(1);
                stopEngineButton.setDisable(false);
                stopEngineButton.setLayoutX(744);
                stopEngineButton.setLayoutY(396);

                startEngineButton.setOpacity(0);
                startEngineButton.setDisable(true);
                startEngineButton.setLayoutX(744);
                startEngineButton.setLayoutY(314);
            }
            startStopButtonsDetailsPT.playFromStart();
        });
        startStopButtonsDetailsPT.play();
    }

    private void turnOfEngineIfNoFuelPT() {
        PauseTransition turnOf = new PauseTransition(Duration.millis(10));
        turnOf.setOnFinished(e -> {
            if (getInstanceOfCar().getFuel() <= 0) {
                smoke.setFitHeight(1);
                smoke.setFitWidth(1);
            }
            getInstanceOfCar().turnOfCarIfNoFuel();
            turnOf.playFromStart();
        });
        turnOf.play();
    }

    private void speedPrinterPT() {
        PauseTransition speedPrinter = new PauseTransition(Duration.millis(100));
        speedPrinter.setOnFinished(event -> {
            speedLabel.setText(getInstanceOfCar().getCurrentSpeed() + " km/h");
            speedPrinter.playFromStart();
        });
        speedPrinter.play();
    }

    private void mileagePrinterPT() {
        PauseTransition mileagePrinter = new PauseTransition(Duration.millis(100));
        mileagePrinter.setOnFinished((e) -> {
            double carMileageRound = getInstanceOfCar().getCarMileage() * 1000;
            carMileageRound = Math.round(carMileageRound);
            carMileageRound /= 1000;
            mileageLabel.setText(carMileageRound + " km");
            mileagePrinter.playFromStart();
        });
        mileagePrinter.play();
    }

    private void fuelPrinterAndColorSetterPT() {
        PauseTransition fuelPT = new PauseTransition(Duration.millis(100));
        fuelPT.setOnFinished((e) -> {
            double carFuelRound = getInstanceOfCar().getFuel() * 100;
            carFuelRound = Math.round(carFuelRound);
            carFuelRound /= 100;

            if (getInstanceOfCar().getFuel() <= 0) {
                fuelLabel.setText(String.valueOf(0));
            } else {
                fuelLabel.setText(String.valueOf(carFuelRound));
            }

            if (carFuelRound >= 27)
                fuelLabel.setTextFill(Color.GREEN);
            else if (carFuelRound >= 5)
                fuelLabel.setTextFill(Color.DARKORANGE);
            else fuelLabel.setTextFill(Color.RED);
            fuelPT.playFromStart();
        });
        fuelPT.play();
    }

    private void showReserveIconIfFuelLessThanFife() {
        PauseTransition reservePT = new PauseTransition(Duration.millis(100));
        reservePT.setOnFinished((e) -> {
            getInstanceOfCar().carReserve();
            if (getInstanceOfCar().isReserve()) {
                reserveIcon.setOpacity(1);
            } else {
                reserveIcon.setOpacity(0);
            }
            reservePT.playFromStart();
        });
        reservePT.play();
    }

    private void rangePrinterPT() {
        PauseTransition rangePrinter = new PauseTransition(Duration.millis(100));
        rangePrinter.setOnFinished((e) -> {
            double toGoRound = getInstanceOfCar().getRange() * 1000;
            toGoRound = Math.round(toGoRound);
            toGoRound /= 1000;
            toGoLabel.setText((int) toGoRound + " km range");
            rangePrinter.playFromStart();
        });
        rangePrinter.play();
    }

    private void moneyPrinterPT() {
        PauseTransition moneyPrinter = new PauseTransition(Duration.millis(100));
        moneyPrinter.setOnFinished((e) -> {
            double moneyRound = getInstanceOfUser().getMoney() * 100;
            moneyRound = Math.round(moneyRound);
            moneyRound /= 100;
            moneyLabel.setText(String.valueOf(moneyRound));

            moneyPrinter.playFromStart();
        });
        moneyPrinter.play();
    }

    private void remainingDistancePrinterPT() {
        PauseTransition remainingDistancePT = new PauseTransition(Duration.millis(100));
        remainingDistancePT.setOnFinished((e) -> {
            if (getInstanceOfCurrentRout().getRemainingDistance() < 0) {
                getInstanceOfCurrentRout().setRemainingDistance(0);
                remainingDistanceLabel.setText(getInstanceOfCurrentRout().getRemainingDistance() + "km");
            }
            remainingDistanceLabel.setText(getInstanceOfCurrentRout().getRemainingDistance() + " km");
            remainingDistancePT.playFromStart();
        });
        remainingDistancePT.play();
    }

    private void profitPrinterPT() {
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(100));
        pauseTransition.setOnFinished(event -> profitForRoutLabel.setText(getInstanceOfCurrentRout().getProfit() + " $"));
    }

    private void disableSetterForSMLButtonsPT() {
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(10));
        pauseTransition.setOnFinished(event -> {
            ifRoutSelectedDisableSMLButtonsSetter();
            pauseTransition.playFromStart();
        });
        pauseTransition.play();
    }

    private void showFinishedRoutPanePT() {
        finishedRoutPane.setStyle("-fx-background-color: #ffbf80");
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(10));
        pauseTransition.setOnFinished(event -> {
            if (getInstanceOfCurrentRout().getRemainingDistance() <= 0)
                showFinishedRoutPane();
            pauseTransition.playFromStart();
        });
        pauseTransition.play();
    }

    private void closeFinishedRoutPanePT() {
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(10));
        pauseTransition.setOnFinished(event -> {
            closeFinishedRoutPane();
            pauseTransition.playFromStart();
        });
        pauseTransition.play();
    }

    private void showCurrentRoutPanePT() {
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(10));
        pauseTransition.setOnFinished(event -> {
            if (getInstanceOfCurrentRout().getCurrentRoutList().size() != 0)
                opacitySetterForCurrentRoutPane();
            pauseTransition.playFromStart();
        });
        pauseTransition.play();
    }

    private void disableButtonsWhileRoutIsCompletedPT(){
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(10));
        pauseTransition.setOnFinished(event -> {
            disableButtons();
            pauseTransition.playFromStart();
        });
        pauseTransition.play();
    }

    private int getDeliveryPackCount() {
        return deliveryPackCount;
    }

    private void setDeliveryPackCount(int deliveryPackCount) {
        this.deliveryPackCount = deliveryPackCount;
    }

    public Routs getRouts() {
        return routs;
    }

    private boolean isRoutSelected() {
        return isRoutSelected;
    }

    private void setRoutSelected(boolean routSelected) {
        isRoutSelected = routSelected;
    }

    public void initialize(URL location, ResourceBundle resources) {

        topPane.setStyle("-fx-background-color: #80dfff");
        menuPane.setStyle("-fx-background-color: #80dfff");

        mileagePrinterPT();
        remainingDistancePrinterPT();
        moneyPrinterPT();
        rangePrinterPT();
        changeToGasStationButtonsIfNoFuelPT();
        changeStartStopButtonsPT();
        fuelPrinterAndColorSetterPT();
        turnOfEngineIfNoFuelPT();
        speedPrinterPT();
        profitPrinterPT();
        disableSetterForSMLButtonsPT();
        showFinishedRoutPanePT();
        closeFinishedRoutPanePT();
        showCurrentRoutPanePT();
        disableButtonsWhileRoutIsCompletedPT();

        showReserveIconIfFuelLessThanFife();

        final Tooltip fuelLabelToolTip = new Tooltip();
        fuelLabelToolTip.setText("Burning is dependent on your speed! \n If the fuel level is too low,\n the reserve icon will inform you about it");
        fuelLabel.setTooltip(fuelLabelToolTip);

        final Tooltip toGoToolTip = new Tooltip();
        toGoToolTip.setText("The range of your car is visible here.\n Remember! \nThe faster you drive the less you \n are able to pass!");
        toGoLabel.setTooltip(toGoToolTip);
    }
}
