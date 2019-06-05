import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import threads.car.FuelUsageThread;
import threads.car.MileageThread;
import threads.car.ToGoOnFuelMileageThread;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/fxmlFiles/WindowBeforeGameStarted1.fxml"));
        BorderPane borderPane = loader.load();
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();

        FuelUsageThread fuelUsage = new FuelUsageThread();
        Thread fuelUsageThread = new Thread(fuelUsage);
        fuelUsageThread.start();

        MileageThread mileageThread = new MileageThread();
        Thread mt = new Thread(mileageThread);
        mt.start();

        ToGoOnFuelMileageThread toGoOnFuelMileageThread = new ToGoOnFuelMileageThread();
        Thread toGoMileageThread = new Thread(toGoOnFuelMileageThread);
        toGoMileageThread.start();
    }
}
