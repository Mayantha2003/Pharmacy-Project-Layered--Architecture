package lk.ijse.pharamacymanagementlayerdsystem.dao.custom.impl;

import lk.ijse.pharamacymanagementlayerdsystem.dao.CRUDUtil;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.InvoiceDAO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Invoice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InvoiceDAOImpl implements InvoiceDAO {

    @Override
    public boolean save(Invoice invoice) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Invoice (invoice_number, invoice_date, customer_id, total_amount, paid_amount, cashier_id) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        return CRUDUtil.execute( sql,
                invoice.getInvoiceNumber(),
                invoice.getInvoiceDate(),
                invoice.getCustomerId() == 0 ? null : invoice.getCustomerId(),
                invoice.getTotalAmount(),
                invoice.getPaidAmount(),
                invoice.getCashierId());
    }

    @Override
    public String generateNextInvoiceNumber() throws SQLException, ClassNotFoundException {
        int currentYear = java.time.LocalDate.now().getYear();

        String sql = "SELECT invoice_number FROM Invoice WHERE invoice_number LIKE ? ORDER BY invoice_id DESC LIMIT 1";
        ResultSet rs = CRUDUtil.execute(sql, "INV-" + currentYear + "-%");

        if (rs.next()) {
            String lastNumber = rs.getString("invoice_number");
            String[] parts = lastNumber.split("-");
            int num = Integer.parseInt(parts[2]);
            return "INV-" + currentYear + "-" + String.format("%03d", num + 1);
        }
        return "INV-" + currentYear + "-001";
    }

    @Override
    public long getLastId() throws SQLException, ClassNotFoundException {
        ResultSet rs =CRUDUtil.execute("SELECT LAST_INSERT_ID()");
        if (rs.next()) return rs.getLong(1);
        return -1;
    }




    //Not Use Method
    @Override
    public ArrayList<Invoice> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Invoice customDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Invoice search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }


}
