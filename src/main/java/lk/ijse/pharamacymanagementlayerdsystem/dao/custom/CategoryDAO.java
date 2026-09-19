package lk.ijse.pharamacymanagementlayerdsystem.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.pharamacymanagementlayerdsystem.dao.CrudDAO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Category;

import java.sql.SQLException;

public interface CategoryDAO extends CrudDAO<Category> {

    public boolean isCategoryUsedInProducts(Long category_Id) throws SQLException, ClassNotFoundException;

    public String getCategoryDisplayName(long catId) throws SQLException, ClassNotFoundException;

    public ObservableList<String> getAllCategoriesForCombo() throws SQLException, ClassNotFoundException;

    public String getCatTypeFromDisplay(String displayText) throws SQLException, ClassNotFoundException;

    public long getCatIdFromDisplay(String displayText) throws SQLException, ClassNotFoundException;
}
