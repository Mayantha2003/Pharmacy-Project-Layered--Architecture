package lk.ijse.pharamacymanagementlayerdsystem.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.pharamacymanagementlayerdsystem.dao.CrudDAO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Product;

import java.sql.SQLException;

public interface ProductDAO extends CrudDAO<Product> {

    public ObservableList<String> getAllProductsForCombo() throws SQLException, ClassNotFoundException ;

    public long getProductIdFromDisplay(String displayText) throws SQLException, ClassNotFoundException ;
}
