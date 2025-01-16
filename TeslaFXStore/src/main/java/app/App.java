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

    public App() {
    }

    @Override
    public void start(Stage primaryStage) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/view/HomeSinSesion.fxml"));

            Pane ventana = (Pane) loader.load();

            Scene scene = new Scene(ventana);
            scene.setFill(Color.TRANSPARENT);

            primaryStage.setTitle("Tesla Shop");
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
