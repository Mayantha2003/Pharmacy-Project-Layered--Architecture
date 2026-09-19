package lk.ijse.pharamacymanagementlayerdsystem.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.pharamacymanagementlayerdsystem.dao.CRUDUtil;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.SupplierDAO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtil.execute("SELECT * FROM Suppliers");
        ArrayList<Supplier> supplierList = new ArrayList<>();

        while (rs.next()) {
            Long supId = rs.getLong("supplier_id");
            String supName = rs.getString("supplier_name");
            String supAddress = rs.getString("address");
            String supContactNumber = rs.getString("contact_number");
            String supEmail = rs.getString("email");

            Supplier entity= new Supplier(supId, supName, supAddress, supContactNumber, supEmail);
            supplierList.add(entity);
        }
        return supplierList;
    }

    @Override
    public boolean save(Supplier supplierDTO) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute(
                "INSERT INTO Suppliers (supplier_name,address,contact_number,email) VALUES (?,?,?,?)",
                supplierDTO.getSupplierName(),
                supplierDTO.getSupplierAddress(),
                supplierDTO.getSupplierContactNumber(),
                supplierDTO.getSupplierEmail());
    }

    @Override
    public boolean update(Supplier supplierDTO) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute(
                "UPDATE Suppliers SET supplier_name=?, address=?, contact_number=?, email=? WHERE supplier_id=?",
                supplierDTO.getSupplierName(),
                supplierDTO.getSupplierAddress(),
                supplierDTO.getSupplierContactNumber(),
                supplierDTO.getSupplierEmail(),
                supplierDTO.getSupplierId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("DELETE FROM Suppliers WHERE supplier_id=?", id);
    }

    @Override
    public Supplier search(String supplierId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtil.execute("SELECT * FROM Suppliers WHERE supplier_id =?", supplierId);
        if (rs.next()) {
            return new Supplier(
                    rs.getLong("supplier_id"),
                    rs.getString("supplier_name"),
                    rs.getString("address"),
                    rs.getString("contact_number"),
                    rs.getString("email")
            );
        }
        return null;
    }

    @Override
    // GRN form supplier dropdown loading
    public ObservableList<String> getAllSupplierNames() throws SQLException, ClassNotFoundException {
        String sql = "SELECT supplier_name FROM Suppliers ORDER BY supplier_name ASC";

        ResultSet rs = CRUDUtil.execute(sql);

        ObservableList<String> list = FXCollections.observableArrayList();
        while (rs.next()) {
            list.add(rs.getString("supplier_name"));
        }
        return list;
    }

    @Override
    // GRN form Supplier getId by Name
    public long getSupplierIdByName(String supplierName) throws SQLException, ClassNotFoundException {
        String sql = "SELECT supplier_id FROM Suppliers WHERE supplier_name = ?";

        ResultSet rs = CRUDUtil.execute(sql, supplierName);

        if (rs.next()) {
            return rs.getLong("supplier_id");
        }
        return -1;
    }
}
