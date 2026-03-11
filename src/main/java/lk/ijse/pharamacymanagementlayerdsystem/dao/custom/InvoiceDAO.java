package lk.ijse.pharamacymanagementlayerdsystem.dao.custom;

import lk.ijse.pharamacymanagementlayerdsystem.dao.CrudDAO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Invoice;

import java.sql.SQLException;

public interface InvoiceDAO extends CrudDAO<Invoice> {

    public String generateNextInvoiceNumber() throws SQLException, ClassNotFoundException;

    public long getLastId() throws SQLException, ClassNotFoundException ;

    }