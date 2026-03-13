package lk.ijse.pharamacymanagementlayerdsystem.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.pharamacymanagementlayerdsystem.bo.BOFactory;
import lk.ijse.pharamacymanagementlayerdsystem.bo.custom.DashBoardBO;
import lk.ijse.pharamacymanagementlayerdsystem.db.DBConnection;
import lk.ijse.pharamacymanagementlayerdsystem.dto.ExpiredMedicineDTO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.LowStockDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class DashboradController implements Initializable {
    @FXML
    private Label lblCustomers, lblExpier, lblOrders, lblProfit, lblRevenue, lblStock;
    @FXML
    private ComboBox<String> cmbFilter;
    @FXML
    private AreaChart<String, Number> salesChart;

    DashBoardBO dashBoardBO = (DashBoardBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DASHBOARD);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblStock.setOnMouseClicked(event -> showLowStockPopUp());
        lblStock.setStyle("-fx-cursor: hand;");
        lblExpier.setOnMouseClicked(event -> showExpiredPopUp());
        lblExpier.setStyle("-fx-cursor: hand;");

        cmbFilter.setItems(FXCollections.observableArrayList("Today", "Weekly", "Monthly"));
        cmbFilter.setValue("Today");

        loadDashboardData("Today");

        cmbFilter.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                loadDashboardData(newValue);
            }
        });

        Platform.runLater(this::checkExpiriesAndAlert);
    }

    private void checkExpiriesAndAlert() {
        try {
            int expiredCount = dashBoardBO.getExpiredCount();
            if (expiredCount > 0) {
                showSideAlert(expiredCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void showSideAlert(int count) {
        System.out.print("\007");
        System.out.flush();

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Pharmacy System Notification");
        alert.setHeaderText("Stock Expiry Warning!");
        alert.setContentText("There are " + count + " items expiring soon.\nClick 'View' or this will close in 5s.");

        ButtonType btnView = new ButtonType("View List");
        alert.getButtonTypes().setAll(btnView, ButtonType.CLOSE);

        alert.setOnShowing(event -> {
            Screen screen = Screen.getPrimary();
            double screenWidth = screen.getVisualBounds().getWidth();
            double screenHeight = screen.getVisualBounds().getHeight();
            alert.setX(screenWidth - 460);
            alert.setY(screenHeight - 250);
        });

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            if (alert.isShowing()) {
                alert.close();
            }
        }));
        timeline.play();

        alert.showAndWait().ifPresent(response -> {
            if (response == btnView) {
                showExpiredPopUp();
            }
        });
    }

    private void loadDashboardData(String filter) {
        try {
            lblRevenue.setText(String.format("Rs. %.2f", dashBoardBO.getTotalRevenue(filter)));
            lblProfit.setText(String.format("Rs. %.2f", dashBoardBO.getTotalProfit(filter)));
            lblOrders.setText(String.valueOf(dashBoardBO.getOrderCount(filter)));
            lblCustomers.setText(String.valueOf(dashBoardBO.getCustomerCount()));

            int expiredCount = dashBoardBO.getExpiredCount();
            int lowStockCount = dashBoardBO.getLowStockCount();

            lblExpier.setText(String.valueOf(expiredCount));
            lblStock.setText(String.valueOf(lowStockCount));

            if (expiredCount > 0) {
                lblExpier.setStyle("-fx-text-fill: #FF3333; -fx-font-weight: bold; -fx-font-size: 18;");
            }
            if (lowStockCount > 0) {
                lblStock.setStyle("-fx-text-fill: #FF8C00; -fx-font-weight: bold; -fx-font-size: 18;");
            }

            updateChart(filter);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateChart(String filter) throws SQLException, ClassNotFoundException {
        salesChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Revenue (" + filter + ")");
        Map<String, Double> data = dashBoardBO.getChartData(filter);
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        salesChart.getData().add(series);
    }

    private void showExpiredPopUp() {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Expired/Expiring Medicines");

        TableView<Map<String, String>> tableView = new TableView<>();
        setupTableColumns(tableView, true);

        loadTableData(tableView, "expired");

        VBox layout = new VBox(10, tableView);
        layout.setStyle("-fx-padding: 20; -fx-background-color: #E3FDFD;");
        popupStage.setScene(new Scene(layout, 600, 400));
        popupStage.show();
    }

    private void showLowStockPopUp() {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Low Stock Warning");

        TableView<Map<String, String>> tableView = new TableView<>();
        setupTableColumns(tableView, false);

        loadTableData(tableView, "lowstock");

        VBox layout = new VBox(10, tableView);
        layout.setStyle("-fx-padding: 20; -fx-background-color: #FFF9E3;");
        popupStage.setScene(new Scene(layout, 500, 400));
        popupStage.show();
    }

    private void setupTableColumns(TableView<Map<String, String>> table, boolean isExpiry) {
        TableColumn<Map<String, String>, String> colName = new TableColumn<>("Medicine");
        colName.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().get("name")));

        TableColumn<Map<String, String>, String> colBatch = new TableColumn<>("Batch");
        colBatch.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().get("batch")));

        table.getColumns().addAll(colName, colBatch);

        if (isExpiry) {
            TableColumn<Map<String, String>, String> colDate = new TableColumn<>("Expiry");
            colDate.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().get("date")));
            table.getColumns().add(colDate);
        }

        TableColumn<Map<String, String>, String> colQty = new TableColumn<>("Qty");
        colQty.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().get("qty")));
        table.getColumns().add(colQty);
    }

    private void loadTableData(TableView<Map<String, String>> table, String type) {
        ObservableList<Map<String, String>> list = FXCollections.observableArrayList();
        try {
            if (type.equals("expired")) {
                for (ExpiredMedicineDTO dto : dashBoardBO.getExpiredMedicines()) {
                    Map<String, String> row = new HashMap<>();
                    row.put("name", dto.getName());
                    row.put("batch", dto.getBatchNumber());
                    row.put("qty", String.valueOf(dto.getQtyRemaining()));
                    row.put("date", dto.getExpiryDate());
                    list.add(row);
                }
            } else {
                for (LowStockDTO dto : dashBoardBO.getLowStockMedicines()) {
                    Map<String, String> row = new HashMap<>();
                    row.put("name", dto.getName());
                    row.put("batch", "All Batches");
                    row.put("qty", String.valueOf(dto.getTotalQty()));
                    list.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        table.setItems(list);
    }

    public void printLowStockReport() {
        try (InputStream inputStream = getClass().getResourceAsStream("/lk/ijse/pharamacymanagementlayerdsystem/assests/report/Low_Stock.jrxml")) {

            Connection conn = DBConnection.getDbConnection().getConnection();

            if (inputStream == null) {
                new Alert(Alert.AlertType.ERROR, "Not Fine the Report File!").show();
                return;
            }

            Map<String, Object> parameters = new HashMap<>();
            JasperReport jr = JasperCompileManager.compileReport(inputStream);
            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, conn);

            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.setTitle("Pharmacy Management System - Low Stock Summary");
            viewer.setVisible(true);
            viewer.toFront();

        } catch (SQLException | JRException | IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "ERROR: " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


}
