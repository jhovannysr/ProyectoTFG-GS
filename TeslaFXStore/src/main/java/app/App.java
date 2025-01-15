package app;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Aplicación de tienda Tesla con interfaz orientado a cliente y administrador
 *
 * @author Henry Jhovanny Aucapiña Tapia
 */
public class App extends Application {

    // Constructor por defecto vacío
    public App() {
    }

    @Override
    public void start(Stage primaryStage) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/view/HomeSinSesion.fxml"));

            Pane ventana = (Pane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(ventana);
            scene.setFill(Color.TRANSPARENT);

            // Para quitar la barra principal del Layout por defecto
//            primaryStage.setMaximized(true);
            primaryStage.setTitle("Tesla Shop");
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * The main entry point for the JavaFX application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    //            loader.setLocation(App.class.getResource("/view/Login.fxml"));
//            loader.setLocation(App.class.getResource("/view/CrearCuenta.fxml"));
//            loader.setLocation(App.class.getResource("/view/HomeConSesion.fxml"));
//            loader.setLocation(App.class.getResource("/view/MisDatos.fxml"));
//            loader.setLocation(App.class.getResource("/view/VehicleModelS.fxml"));
//            loader.setLocation(App.class.getResource("/view/VehicleModelX.fxml"));
//            loader.setLocation(App.class.getResource("/view/VehicleModelY.fxml"));
//            loader.setLocation(App.class.getResource("/view/VehicleModel3.fxml"));
//            loader.setLocation(App.class.getResource("/view/AdministratorMode.fxml"));
}
