package lk.ijse.pharamacymanagementlayerdsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.pharamacymanagementlayerdsystem.bo.BOFactory;
import lk.ijse.pharamacymanagementlayerdsystem.bo.custom.BatchBO;
import lk.ijse.pharamacymanagementlayerdsystem.bo.custom.GRNBO;
import lk.ijse.pharamacymanagementlayerdsystem.bo.custom.ProductBO;
import lk.ijse.pharamacymanagementlayerdsystem.bo.custom.SupplierBO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.GrnDTO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.GrnLineDTO;
import lk.ijse.pharamacymanagementlayerdsystem.view.tdm.GRNLineTM;

import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GRNController implements Initializable {

    @FXML private ComboBox<String> supplierCombo;
    @FXML private DatePicker grnDatePicker;
    @FXML private TextField grnNumberField;
    @FXML private TextField referenceNumberField;

    @FXML private ComboBox<String> lineProductCombo;
    @FXML private TextField lineBatchNumberField;
    @FXML private DatePicker lineExpiryDatePicker;
    @FXML private DatePicker lineManufactureDatePicker;
    @FXML private TextField lineQtyField;
    @FXML private TextField lineCostPriceField;
    @FXML private TextField lineSellingPriceField;

    @FXML private TableView<GRNLineTM> lineItemsTable;
    @FXML private TableColumn<GRNLineTM, Integer> lineNoCol;
    @FXML private TableColumn<GRNLineTM, String>  lineProductCol;
    @FXML private TableColumn<GRNLineTM, String>  lineBatchCol;
    @FXML private TableColumn<GRNLineTM, LocalDate> lineExpiryCol;
    @FXML private TableColumn<GRNLineTM, LocalDate> lineMfgCol;
    @FXML private TableColumn<GRNLineTM, Integer> lineQtyCol;
    @FXML private TableColumn<GRNLineTM, Double>  lineCostCol;
    @FXML private TableColumn<GRNLineTM, Double>  lineSellCol;
    @FXML private TableColumn<GRNLineTM, Double>  lineTotalCol;
    @FXML private TableColumn<GRNLineTM, Void>    lineActionsCol;

    @FXML private Label totalItemsLabel;
    @FXML private Label totalQtyLabel;
    @FXML private Label totalCostLabel;
    @FXML private Label totalSellingLabel;
    @FXML private Label profitMarginLabel;
    @FXML private Label profitPercentLabel;

    SupplierBO supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER);
    ProductBO  productBO  = (ProductBO)  BOFactory.getInstance().getBO(BOFactory.BOTypes.PRODUCT);
    GRNBO      grnBO      = (GRNBO)      BOFactory.getInstance().getBO(BOFactory.BOTypes.GRN);
    BatchBO    batchBO    = (BatchBO)    BOFactory.getInstance().getBO(BOFactory.BOTypes.BATCH);

    private ObservableList<GRNLineTM> lineList = FXCollections.observableArrayList();

    // ---------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        grnDatePicker.setValue(LocalDate.now());
        grnDatePicker.setDisable(true);
        grnDatePicker.setStyle("-fx-opacity: 1; -fx-text-fill: black; -fx-font-weight: bold;");

        loadSuppliers();
        loadProducts();
        setupTableColumns();
        setupActionsColumn();

        lineItemsTable.setItems(lineList);
        lineList.addListener((ListChangeListener<GRNLineTM>) c -> updateSummary());
        updateSummary();

        generateGrnNumber();
    }

    // ---------------------------------------------------------------
    /** GRN number generate — initialize() සහ clearAll() දෙකෙනුත් call කරනවා */
    private void generateGrnNumber() {
        try {
            grnNumberField.setText(grnBO.generateNextGrnNumber());
        } catch (SQLException e) {
            grnNumberField.setText("GRN-" + LocalDate.now().getYear() + "-001");
            showAlert("Error", "Failed to generate GRN number!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // ---------------------------------------------------------------
    private void loadSuppliers() {
        try {
            supplierCombo.setItems(supplierBO.getAllSupplierNames());
        } catch (SQLException e) {
            showAlert("Error", "Failed to load suppliers!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadProducts() {
        try {
            lineProductCombo.setItems(productBO.getAllProductsForCombo());
        } catch (SQLException | ClassNotFoundException e) {
            showAlert("Error", "Failed to load products!");
        }
    }

    // ---------------------------------------------------------------
    private void setupTableColumns() {
        lineNoCol.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : String.valueOf(getIndex() + 1));
            }
        });

        lineProductCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        lineBatchCol  .setCellValueFactory(new PropertyValueFactory<>("batchNumber"));
        lineExpiryCol .setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        lineMfgCol    .setCellValueFactory(new PropertyValueFactory<>("manufactureDate"));
        lineQtyCol    .setCellValueFactory(new PropertyValueFactory<>("quantity"));
        lineCostCol   .setCellValueFactory(new PropertyValueFactory<>("costPrice"));
        lineSellCol   .setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        lineTotalCol  .setCellValueFactory(new PropertyValueFactory<>("lineTotal"));
    }

    private void setupActionsColumn() {
        lineActionsCol.setCellFactory(param -> new TableCell<>() {
            private final Button removeButton = new Button("🗑 Remove");
            {
                removeButton.setStyle(
                        "-fx-background-color: #e74c3c; -fx-text-fill: white;" +
                                " -fx-font-weight: bold; -fx-padding: 5 10;"
                );
                removeButton.setOnAction(event -> {
                    GRNLineTM lineToRemove = getTableView().getItems().get(getIndex());
                    lineList.remove(lineToRemove);
                    updateSummary();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : removeButton);
            }
        });
    }

    // ---------------------------------------------------------------
    @FXML
    private void handleAddLine() {
        if (!validateLine()) return;

        try {
            long   productId = productBO.getProductIdFromDisplay(lineProductCombo.getValue());
            int    qty       = Integer.parseInt(lineQtyField.getText().trim());
            double cost      = Double.parseDouble(lineCostPriceField.getText().trim());
            double sell      = Double.parseDouble(lineSellingPriceField.getText().trim());

            if (qty <= 0) {
                new Alert(Alert.AlertType.ERROR, "Quantity must be greater than zero!").show();
                return;
            }
            if (cost <= 0 || sell <= 0) {
                new Alert(Alert.AlertType.ERROR, "Price must be a positive value!").show();
                return;
            }
            if (sell < cost) {
                new Alert(Alert.AlertType.WARNING, "Selling Price is lower than Cost Price!").show();
            }

            // DTO — business logic (calculateLineTotal)
            GrnLineDTO line = new GrnLineDTO();
            line.setProductId(productId);
            line.setProductName(lineProductCombo.getValue());
            line.setBatchNumber(lineBatchNumberField.getText().trim());
            line.setExpiryDate(lineExpiryDatePicker.getValue());
            line.setManufactureDate(lineManufactureDatePicker.getValue());
            line.setQuantity(qty);
            line.setCostPrice(cost);
            line.setSellingPrice(sell);
            line.calculateLineTotal();

            // ✅ DTO → TM (table display)
            GRNLineTM tm = new GRNLineTM(
                    line.getProductId(),
                    line.getProductName(),
                    line.getBatchNumber(),
                    line.getExpiryDate(),
                    line.getManufactureDate(),
                    line.getQuantity(),
                    line.getCostPrice(),
                    line.getSellingPrice(),
                    line.getLineTotal()
            );

            lineList.add(tm);
            updateSummary();
            clearLineFields();

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid numbers!").show();
        } catch (Exception e) {
            showAlert("Error", "Failed to add line: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------
    @FXML
    private void handleResetLine() {
        clearLineFields();
    }

    // ---------------------------------------------------------------
    @FXML
    private void handleSaveGRN() {
        if (supplierCombo.getValue() == null) {
            showAlert("Required", "Please select a supplier!");
            return;
        }
        if (lineList.isEmpty()) {
            showAlert("Required", "Please add at least one line item!");
            return;
        }

        try {
            long supplierId = supplierBO.getSupplierIdByName(supplierCombo.getValue());

            // ✅ TM → DTO list (BO layer DTO expect කරනවා)
            List<GrnLineDTO> lineDTOList = new ArrayList<>();
            for (GRNLineTM tm : lineList) {
                GrnLineDTO dto = new GrnLineDTO();
                dto.setProductId(tm.getProductId());
                dto.setProductName(tm.getProductName());
                dto.setBatchNumber(tm.getBatchNumber());
                dto.setExpiryDate(tm.getExpiryDate());
                dto.setManufactureDate(tm.getManufactureDate());
                dto.setQuantity(tm.getQuantity());
                dto.setCostPrice(tm.getCostPrice());
                dto.setSellingPrice(tm.getSellingPrice());
                dto.setLineTotal(tm.getLineTotal());
                lineDTOList.add(dto);
            }

            GrnDTO grn = new GrnDTO();
            grn.setGrnNumber(grnNumberField.getText().trim());
            grn.setSupplierId(supplierId);
            grn.setGrnDate(grnDatePicker.getValue());
            grn.setTotalAmount(lineList.stream().mapToDouble(GRNLineTM::getLineTotal).sum());
            grn.setReceivedBy(getCurrentUserId());

            String reference = referenceNumberField.getText().trim();
            grn.setNotes(reference.isEmpty() ? "" : "Reference (PO/Invoice): " + reference);

            grn.setLines(lineDTOList); // ✅ DTO list → BO

            boolean success = grnBO.saveGRNWithTransaction(grn);

            if (success) {
                showAlert("Success", "GRN saved successfully with " + lineList.size() + " items!");
                clearAll();
            } else {
                showAlert("Failed", "Failed to save GRN!");
            }

        } catch (SQLIntegrityConstraintViolationException i) {
            showAlert("Error", "Save failed: " + i.getMessage());
        } catch (Exception e) {
            showAlert("Error", "Save failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------------
    @FXML
    private void handleResetForm() {
        clearAll();
    }

    // ---------------------------------------------------------------
    private void clearAll() {
        supplierCombo.getSelectionModel().clearSelection();
        grnDatePicker.setValue(LocalDate.now());
        referenceNumberField.clear();
        lineList.clear();
        clearLineFields();
        updateSummary();
        generateGrnNumber(); // ✅ save වෙලා reset වෙනකොට නව GRN number
    }

    private void clearLineFields() {
        lineProductCombo.getSelectionModel().clearSelection();
        lineBatchNumberField.clear();
        lineExpiryDatePicker.setValue(null);
        lineManufactureDatePicker.setValue(null);
        lineQtyField.clear();
        lineCostPriceField.clear();
        lineSellingPriceField.clear();
    }

    // ---------------------------------------------------------------
    private void updateSummary() {
        int    items         = lineList.size();
        int    qty           = lineList.stream().mapToInt(GRNLineTM::getQuantity).sum();
        double costTotal     = lineList.stream().mapToDouble(l -> l.getQuantity() * l.getCostPrice()).sum();
        double sellTotal     = lineList.stream().mapToDouble(l -> l.getQuantity() * l.getSellingPrice()).sum();
        double profit        = sellTotal - costTotal;
        double profitPercent = costTotal > 0 ? (profit / costTotal) * 100 : 0;

        totalItemsLabel  .setText(String.valueOf(items));
        totalQtyLabel    .setText(String.valueOf(qty));
        totalCostLabel   .setText("Rs. " + String.format("%.2f", costTotal));
        totalSellingLabel.setText("Rs. " + String.format("%.2f", sellTotal));
        profitMarginLabel.setText("Rs. " + String.format("%.2f", profit));
        profitPercentLabel.setText(String.format("%.2f", profitPercent) + " %");
    }

    // ---------------------------------------------------------------
    private boolean validateLine() {
        if (lineProductCombo.getValue() == null
                || lineBatchNumberField.getText().trim().isEmpty()
                || lineExpiryDatePicker.getValue() == null
                || lineQtyField.getText().trim().isEmpty()
                || lineCostPriceField.getText().trim().isEmpty()
                || lineSellingPriceField.getText().trim().isEmpty()) {
            showAlert("Required", "Please fill all required fields!");
            return false;
        }
        try {
            Integer.parseInt(lineQtyField.getText().trim());
            Double.parseDouble(lineCostPriceField.getText().trim());
            Double.parseDouble(lineSellingPriceField.getText().trim());
        } catch (NumberFormatException e) {
            showAlert("Invalid", "Quantity, Cost and Selling Price must be valid numbers!");
            return false;
        }
        if (lineExpiryDatePicker.getValue().isBefore(LocalDate.now())) {
            showAlert("Invalid", "Expiry date cannot be in the past!");
            return false;
        }
        return true;
    }

    // ---------------------------------------------------------------
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private long getCurrentUserId() {
        return 1;
    }

    // ---------------------------------------------------------------
    @FXML
    private void handleAutoGenerateBatch(ActionEvent event) {
        try {
            String nextBatch = batchBO.generateNextBatchNumber(lineItemsTable.getItems().size());
            lineBatchNumberField.setText(nextBatch);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Batch Number Error: " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}