package lk.ijse.pharamacymanagementlayerdsystem.dao.custom.impl;

import lk.ijse.pharamacymanagementlayerdsystem.dao.CRUDUtil;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.InvoicePaymentDAO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.InvoicePayment;

import java.sql.SQLException;
import java.util.ArrayList;

public class InvoicePaymentDAOImpl implements InvoicePaymentDAO {

    @Override
    public boolean save(InvoicePayment invoicePayment) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Invoice_Payment (invoice_id, payment_method, amount) "
                + "VALUES (?, ?, ?)";

        return CRUDUtil.execute(sql,
                invoicePayment.getInvoiceId(),
                invoicePayment.getPaymentMethod(),
                invoicePayment.getAmount()
        );
    }






    //Not Use method
    @Override
    public ArrayList<InvoicePayment> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(InvoicePayment customDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public InvoicePayment search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
