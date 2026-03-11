package lk.ijse.pharamacymanagementlayerdsystem.dao.custom.impl;

import lk.ijse.pharamacymanagementlayerdsystem.dao.CRUDUtil;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.BatchDAO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Batch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class BatchDAOImpl implements BatchDAO {

    @Override
    public boolean save(Batch entity) throws SQLException, ClassNotFoundException {
            String sql = "INSERT INTO Batches (batch_number, product_id, expiry_date, manufacture_date, qty_received, qty_remaining, cost_price, selling_price, supplier_id, received_date) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_DATE)";

            return CRUDUtil.execute(sql,
                    entity.getBatchNumber(),
                    entity.getProductId(),
                    entity.getExpiryDate(),
                    entity.getManufactureDate(),
                    entity.getQtyReceived(),
                    entity.getQtyRemaining(),
                    entity.getCostPrice(),
                    entity.getSellingPrice(),
                    entity.getSupplierId());

    }

    @Override
    public String generateNextBatchNumber(int rowCount) throws SQLException, ClassNotFoundException {
        String sql = "SELECT MAX(batch_id) FROM Batches";
        ResultSet rs = CRUDUtil.execute(sql);

        String datePart = LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
        String prefix = "BT-" + datePart + "-";

        long lastDbId = 0;
        if (rs.next()) {
            lastDbId = rs.getLong(1);
        }

        long nextId = lastDbId + rowCount + 1;

        return prefix + String.format("%04d", nextId);
    }

    @Override
    public boolean updateBatchStock(long batchId, int soldQty) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Batches SET qty_remaining = qty_remaining - ? WHERE batch_id = ? AND qty_remaining >= ?";

        return CRUDUtil.execute(sql, soldQty, batchId, soldQty);
    }



    //Not use Only Override method
    @Override
    public ArrayList<Batch> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Batch customDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Batch search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
