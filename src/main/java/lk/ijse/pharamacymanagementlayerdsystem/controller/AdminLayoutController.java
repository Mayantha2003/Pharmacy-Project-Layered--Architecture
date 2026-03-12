package lk.ijse.pharamacymanagementlayerdsystem.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import lk.ijse.pharamacymanagementlayerdsystem.HelloApplication;
import lk.ijse.pharamacymanagementlayerdsystem.util.SessionManager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AdminLayoutController implements Initializable {
    @FXML
    private StackPane main_Form;
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnCustomer;
    @FXML
    private Button btnInvoice;
    @FXML
    private Button btnProduct;
    @FXML
    private Button btnSupplier;
    @FXML
    private Button btnUser;
    @FXML
    private Button btnCategorie;
    @FXML
    private Button btnGRN;
    @FXML
    private Button logoutButton;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initClock();
        try {
            clickDashboradNav();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (SessionManager.currentUser != null) {
            String role = SessionManager.currentUser.getRole();

            if (role.equalsIgnoreCase("Owner")) {
                try {
                    clickDashboradNav();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    clickInvoiceNav();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                btnUser.setVisible(false);
                btnUser.setManaged(false);

                btnDashboard.setVisible(false);
                btnDashboard.setManaged(false);

                btnSupplier.setVisible(false);
                btnSupplier.setManaged(false);
            }
        }
    }

    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            lblTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            lblDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }), new KeyFrame(Duration.seconds(1)));

        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }

    @FXML
    private void clickCustomerNav() throws IOException {
        main_Form.getChildren().setAll(HelloApplication.loadFXML("Customer"));
    }

    @FXML
    private void clickDashboradNav() throws IOException {
        main_Form.getChildren().setAll(HelloApplication.loadFXML("Dashborad"));
    }

    @FXML
    private void clickInvoiceNav() throws IOException {
        main_Form.getChildren().setAll(HelloApplication.loadFXML("Invoice"));
    }

    @FXML
    private void clickProductNav() throws IOException {
        main_Form.getChildren().setAll(HelloApplication.loadFXML("Product"));
    }

    @FXML
    private void clickSupplierNav() throws IOException {
        main_Form.getChildren().setAll(HelloApplication.loadFXML("Supplier"));
    }

    @FXML
    private void clickUserNav() throws IOException {
        main_Form.getChildren().setAll(HelloApplication.loadFXML("User"));
    }

    @FXML
    private void clickCategoryNav() throws IOException {
        main_Form.getChildren().setAll(HelloApplication.loadFXML("Category"));
    }

    @FXML
    private void clickGRNNav() throws IOException {
        main_Form.getChildren().setAll(HelloApplication.loadFXML("GRN"));
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to logout?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                try {
                    HelloApplication.setRoot("Login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
