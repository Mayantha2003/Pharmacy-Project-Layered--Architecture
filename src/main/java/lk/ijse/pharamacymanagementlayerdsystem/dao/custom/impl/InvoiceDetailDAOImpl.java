package lk.ijse.pharamacymanagementlayerdsystem.dao.custom.impl;

import lk.ijse.pharamacymanagementlayerdsystem.dao.CRUDUtil;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.InvoiceDetailDAO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.InvoiceDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class InvoiceDetailDAOImpl implements InvoiceDetailDAO {

    @Override
    public boolean save(InvoiceDetails details) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Invoice_Details (invoice_id, batch_id, quantity, selling_price) "
                + "VALUES (?, ?, ?, ?)";

        return CRUDUtil.execute(sql,
                details.getInvoiceId(),
                details.getBatchId(),
                details.getQuantity(),
                details.getSellingPrice());
    }




    //Not Use Method
    @Override
    public ArrayList<InvoiceDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
    @Override
    public boolean update(InvoiceDetails customDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public InvoiceDetails search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
