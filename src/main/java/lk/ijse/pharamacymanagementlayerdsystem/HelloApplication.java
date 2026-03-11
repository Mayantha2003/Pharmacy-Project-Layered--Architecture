package lk.ijse.pharamacymanagementlayerdsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Scene scene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        scene = new Scene(loadFXML("Login"),800,600);
        primaryStage.setMaximized(false);
        primaryStage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("/assests/image/pharmacy.png")));
        primaryStage.setTitle("Pharmacy Management System - Login");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));

        if ("AdminLayout".equals(fxml)) {
            primaryStage.hide();

            primaryStage.setMaximized(true);
            primaryStage.centerOnScreen();
            primaryStage.show();
            primaryStage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("/assests/image/pharmacy.png")));
            primaryStage.setTitle("Pharmacy Management System - Dashboard");
            primaryStage.toFront();
        } else {
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);
            primaryStage.setMaximized(false);
            primaryStage.centerOnScreen();
        }
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }


}
