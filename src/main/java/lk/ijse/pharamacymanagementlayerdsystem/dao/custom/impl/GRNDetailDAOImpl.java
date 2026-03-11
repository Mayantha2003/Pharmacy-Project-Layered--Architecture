package lk.ijse.pharamacymanagementlayerdsystem.dao.custom.impl;

import lk.ijse.pharamacymanagementlayerdsystem.dao.CRUDUtil;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.GRNDetailsDAO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.GrnLine;

import java.sql.SQLException;
import java.util.ArrayList;

public class GRNDetailDAOImpl implements GRNDetailsDAO {

    @Override
    public boolean save(GrnLine entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO GRN_Details (grn_id, product_id, batch_number, expiry_date, manufacture_date, quantity, cost_price, selling_price) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        return CRUDUtil.execute(sql,
                entity.getGrnId(),
                entity.getProductId(),
                entity.getBatchNumber(),
                entity.getExpiryDate(),
                entity.getManufactureDate(),
                entity.getQuantity(),
                entity.getCostPrice(),
                entity.getSellingPrice());
    }


    //Not use Only Override method
    @Override
    public ArrayList<GrnLine> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(GrnLine customDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public GrnLine search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
