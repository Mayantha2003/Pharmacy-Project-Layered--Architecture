package lk.ijse.pharamacymanagementlayerdsystem.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.pharamacymanagementlayerdsystem.bo.custom.SupplierBO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.DAOFactory;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.SupplierDAO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.SupplierDTO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(supplierDTO.getSupplierId(), supplierDTO.getSupplierName(),
                supplierDTO.getSupplierAddress(), supplierDTO.getSupplierContactNumber(), supplierDTO.getSupplierEmail()
        ));
    }

    @Override
    public SupplierDTO searchSupplier(Long supplierId) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.search(String.valueOf(supplierId));
            return new SupplierDTO(supplier.getSupplierId(), supplier.getSupplierName(), supplier.getSupplierAddress(),
                    supplier.getSupplierContactNumber(), supplier.getSupplierEmail()
            );
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(supplierDTO.getSupplierId(), supplierDTO.getSupplierName(),
                supplierDTO.getSupplierAddress(), supplierDTO.getSupplierContactNumber(), supplierDTO.getSupplierEmail()
        ));
    }

    @Override
    public boolean deleteSupplier(Long supplierId) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(String.valueOf(supplierId));
    }

    @Override
    public ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> suppliers = supplierDAO.getAll();
        ArrayList<SupplierDTO> supplierDTOS = new ArrayList<>();

        for (Supplier supplier : suppliers) {
            supplierDTOS.add(new SupplierDTO(
                    supplier.getSupplierId(),
                    supplier.getSupplierName(),
                    supplier.getSupplierAddress(),
                    supplier.getSupplierContactNumber(),
                    supplier.getSupplierEmail()
            ));
        }
        return supplierDTOS;
    }

    @Override
    public ObservableList<String> getAllSupplierNames() throws SQLException, ClassNotFoundException {
        List<String> names = supplierDAO.getAllSupplierNames();
        return FXCollections.observableArrayList(names);
    }

    @Override
    public long getSupplierIdByName(String supplierName) throws SQLException, ClassNotFoundException {
        return supplierDAO.getSupplierIdByName(supplierName);
    }
}
