package lk.ijse.pharamacymanagementlayerdsystem.bo.custom.impl;

import lk.ijse.pharamacymanagementlayerdsystem.bo.custom.GRNBO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.DAOFactory;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.BatchDAO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.GRNDAO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.GRNDetailsDAO;
import lk.ijse.pharamacymanagementlayerdsystem.db.DBConnection;
import lk.ijse.pharamacymanagementlayerdsystem.dto.GrnDTO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.GrnLineDTO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Batch;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Grn;
import lk.ijse.pharamacymanagementlayerdsystem.entity.GrnLine;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class GRNBOImpl implements GRNBO {

    GRNDAO grnDAO = (GRNDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.GRN);
    GRNDetailsDAO grnDetailsDAO = (GRNDetailsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.GRNDETAILS);
    BatchDAO batchDAO = (BatchDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BATCH);

    @Override
    public boolean saveGRNWithTransaction(GrnDTO grnDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);
        try {
            // 1. Save GRN Header (DTO -> Entity conversion)
            boolean isGrnSaved = grnDAO.save(new Grn(
                    grnDTO.getGrnNumber(), grnDTO.getSupplierId(), grnDTO.getGrnDate(),
                    grnDTO.getTotalAmount(), grnDTO.getNotes(), grnDTO.getReceivedBy()
            ));

            if (!isGrnSaved) {
                connection.rollback();
                return false;
            }
            long grnId = grnDAO.getLastId();
            // 3. Save Lines & Batches
            for (GrnLineDTO line : grnDTO.getLines()) {

                boolean isDetailSaved = grnDetailsDAO.save(new GrnLine(
                        grnId, line.getProductId(), line.getBatchNumber(),
                        line.getExpiryDate(), line.getManufactureDate(),
                        line.getQuantity(), line.getCostPrice(), line.getSellingPrice()
                ));

                boolean isBatchSaved = batchDAO.save(new Batch(
                        line.getBatchNumber(), line.getProductId(), "",
                        line.getExpiryDate(), line.getManufactureDate(),
                        line.getQuantity(), line.getQuantity(),
                        line.getCostPrice(), line.getSellingPrice(),
                        grnDTO.getSupplierId(), LocalDate.now()
                ));

                if (!isDetailSaved || !isBatchSaved) {
                    connection.rollback();
                    return false;
                }
            }
            connection.commit();
            return true;

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public String generateNextGrnNumber() throws SQLException, ClassNotFoundException {
        return grnDAO.generateNextGrnNumber();
    }
}
