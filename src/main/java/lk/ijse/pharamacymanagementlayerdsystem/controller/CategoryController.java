package lk.ijse.pharamacymanagementlayerdsystem.controller;

import java.net.URL;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.pharamacymanagementlayerdsystem.bo.BOFactory;
import lk.ijse.pharamacymanagementlayerdsystem.bo.custom.CategoryBO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.CategoryDTO;
import lk.ijse.pharamacymanagementlayerdsystem.view.tdm.CategoryTM;

public class CategoryController implements Initializable {

    @FXML
    private TextField cat_ID;

    @FXML
    private TextField category_Name;

    @FXML
    private ComboBox<String> com_Select;


    @FXML
    private TableColumn<CategoryTM,String> cat_Column_ID;

    @FXML
    private TableColumn<CategoryTM,String> cat_Column_Name;

    @FXML
    private TableColumn <CategoryTM,String>cat_Column_Type;

    @FXML
    private TableColumn<CategoryTM, Void> ActionCol;

    @FXML
    private TableView<CategoryTM> category_Table_View;


    private final String CAT_NAME_REGEX = "^[A-Za-z\\s&]{3,50}$";

    CategoryBO categoryBO = (CategoryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CATEGORY);

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        com_Select.setItems(FXCollections.observableArrayList("Medicine", "Item"));

        cat_Column_ID.setCellValueFactory(new PropertyValueFactory<>("category_Id"));
        cat_Column_Name.setCellValueFactory(new PropertyValueFactory<>("category_name"));
        cat_Column_Type.setCellValueFactory(new PropertyValueFactory<>("category_type"));

        category_Table_View.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setDataToFields((CategoryTM) newValue);
            }
        });

        ActionColumn();
        loadAllCategories();
    }

    private void setDataToFields(CategoryTM category) {
        cat_ID.setText(String.valueOf(category.getCategory_Id()));
        category_Name.setText(category.getCategory_name());
        com_Select.setValue(category.getCategory_type());
    }

    private void ActionColumn() {
        ActionCol.setCellFactory(column -> {
            return new TableCell<>() {
                private final Button deleteButton = new Button("🗑 Remove");

                {
                    deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; "
                            + "-fx-font-weight: bold; -fx-cursor: hand; -fx-background-radius: 5;");

                    deleteButton.setOnAction(event -> {
                        CategoryTM selectedCat = getTableView().getItems().get(getIndex());
                        handleActionDelete(selectedCat);
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(deleteButton);
                        setAlignment(javafx.geometry.Pos.CENTER);
                    }
                }
            };
        });
    }

    private void handleActionDelete(CategoryTM category) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Delete");
        confirm.setHeaderText("Delete Category: " + category.getCategory_name());
        confirm.setContentText("Are you sure you want to delete this category?");

        if (confirm.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            try {
                if (categoryBO.isCategoryUsedInProducts(category.getCategory_Id())) {
                    new Alert(Alert.AlertType.ERROR, "Cannot delete! This category is used in products.").show();
                    return;
                }

                boolean result = categoryBO.deleteCategory(category.getCategory_Id());
                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Category Deleted!").show();
                    loadAllCategories();
                    clearFields();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleSaveCategory() {

        try {
            String name = category_Name.getText().trim();
            String type = com_Select.getValue();

            if (name.isEmpty() || !name.matches(CAT_NAME_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Category Name! (Min 3 letters, letters & spaces only)").show();

            } else if (type == null || type.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please select a Type!").show();

            } else {

                CategoryDTO categoryDTO = new CategoryDTO(name, type);

                boolean result = categoryBO.saveCategory(categoryDTO);

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Category Added Successfully!").show();
                    loadAllCategories();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to add category!").show();
                }
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            new Alert(Alert.AlertType.ERROR, "Category with this name and type already exists!").show();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSearchCategory(KeyEvent event) {
        try {

            if (event.getCode() == KeyCode.ENTER) {
                String id = cat_ID.getText().trim();

                if (id.isEmpty()) {
                    new Alert(Alert.AlertType.WARNING, "Please enter Category ID!").show();

                } else {
                    CategoryDTO categorieDTO = categoryBO.searchCategory(Long.parseLong(id));

                    if (categorieDTO != null) {

                        cat_ID.setText(String.valueOf(categorieDTO.getCategory_Id()));
                        category_Name.setText(categorieDTO.getCategory_name());
                        com_Select.setValue(categorieDTO.getCategory_type());

                    } else {
                        new Alert(Alert.AlertType.INFORMATION, "Category Not Found!").show();
                    }
                }
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid ID! Please enter a number.").show();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    @FXML
    private void handleUpdateCategory() {
        try {
            String id = cat_ID.getText().trim();
            String name = category_Name.getText().trim();
            String type = com_Select.getValue();

            if (id.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please select or search a category to update!").show();

            } else if (name.isEmpty() || !name.matches(CAT_NAME_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Category Name!").show();

            } else if (type == null || type.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please select a Type!").show();

            } else {

                CategoryDTO categoryDTO = new CategoryDTO(Long.parseLong(id), name, type);

                boolean result = categoryBO.updateCategory(categoryDTO);

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Category Updated Successfully!").show();
                    loadAllCategories();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Update failed!").show();
                }
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    private void loadAllCategories() {

        try {
            List<CategoryDTO> categoryList = categoryBO.getAllCategory();

            ObservableList<CategoryTM> obList = FXCollections.observableArrayList();

            for (CategoryDTO dto : categoryList) {
                CategoryTM tm = new CategoryTM(
                        dto.getCategory_Id(),
                        dto.getCategory_name(),
                        dto.getCategory_type()
                );
                obList.add(tm);
            }
            category_Table_View.setItems(obList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load Categories!").show();
            e.printStackTrace();
        }
    }

    private void clearFields() {
        cat_ID.clear();
        category_Name.clear();
        com_Select.setValue(null);
    }

}
