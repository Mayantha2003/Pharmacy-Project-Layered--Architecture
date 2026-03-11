package lk.ijse.pharamacymanagementlayerdsystem.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.pharamacymanagementlayerdsystem.dao.CrudDAO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Supplier;


import java.sql.SQLException;

public interface SupplierDAO extends CrudDAO<Supplier> {

    // GRN form supplier dropdown loading
    public ObservableList<String> getAllSupplierNames() throws SQLException, ClassNotFoundException ;

    // GRN form Supplier getId by Name
    public long getSupplierIdByName(String supplierName) throws SQLException, ClassNotFoundException ;
}
