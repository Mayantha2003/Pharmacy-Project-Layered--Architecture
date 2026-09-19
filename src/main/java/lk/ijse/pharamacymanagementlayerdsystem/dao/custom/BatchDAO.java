package lk.ijse.pharamacymanagementlayerdsystem.dao.custom;

import lk.ijse.pharamacymanagementlayerdsystem.dao.CRUDUtil;
import lk.ijse.pharamacymanagementlayerdsystem.dao.CrudDAO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.BatchDTO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Batch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface BatchDAO extends CrudDAO<Batch> {

    public String generateNextBatchNumber(int rowCount) throws SQLException, ClassNotFoundException;

    public boolean updateBatchStock(long batchId, int soldQty) throws SQLException, ClassNotFoundException;

    public int getExpiredCount() throws SQLException, ClassNotFoundException ;
}
