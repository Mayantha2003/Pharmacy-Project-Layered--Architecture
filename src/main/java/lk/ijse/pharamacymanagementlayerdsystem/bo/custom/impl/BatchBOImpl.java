package lk.ijse.pharamacymanagementlayerdsystem.bo.custom.impl;

import lk.ijse.pharamacymanagementlayerdsystem.bo.custom.BatchBO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.DAOFactory;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.BatchDAO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.QueryDAO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Batch;

import java.sql.SQLException;
import java.util.List;

public class BatchBOImpl implements BatchBO {

    BatchDAO batchDAO = (BatchDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BATCH);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);


    @Override
    public String generateNextBatchNumber(int rowCount) throws SQLException, ClassNotFoundException {
        return batchDAO.generateNextBatchNumber(rowCount);
    }

    @Override
    public List<String> searchAvailableBatches(String product) throws SQLException, ClassNotFoundException {
        return queryDAO.searchAvailableBatches(product);
    }

    @Override
    public Batch getBatchByDisplay(String display) throws SQLException, ClassNotFoundException {
        return queryDAO.getBatchByDisplay(display);
    }

    @Override
    public List<String> getProductSuggestionsStartingWith(String letter) throws SQLException, ClassNotFoundException {
        return queryDAO.getProductSuggestionsStartingWith(letter);
    }
}
