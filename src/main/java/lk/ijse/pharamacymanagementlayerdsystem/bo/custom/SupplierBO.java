package lk.ijse.pharamacymanagementlayerdsystem.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.pharamacymanagementlayerdsystem.bo.SuperBO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.List;

public interface SupplierBO extends SuperBO {

    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    public SupplierDTO searchSupplier(Long supplierId) throws SQLException, ClassNotFoundException;

    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    public boolean deleteSupplier(Long supplierId) throws SQLException, ClassNotFoundException;

    public List<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException;

    // GRN form supplier dropdown loading
    public ObservableList<String> getAllSupplierNames() throws SQLException, ClassNotFoundException;

    // GRN form Supplier getId by Name
    public long getSupplierIdByName(String supplierName) throws SQLException, ClassNotFoundException;
}

