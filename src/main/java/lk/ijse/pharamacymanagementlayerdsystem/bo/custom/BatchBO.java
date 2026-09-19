package lk.ijse.pharamacymanagementlayerdsystem.bo.custom;

import lk.ijse.pharamacymanagementlayerdsystem.bo.SuperBO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.CRUDUtil;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Batch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface BatchBO extends SuperBO {

    public String generateNextBatchNumber(int rowCount) throws SQLException, ClassNotFoundException ;

    public List<String> searchAvailableBatches(String product) throws SQLException, ClassNotFoundException ;

    public Batch getBatchByDisplay(String display) throws SQLException, ClassNotFoundException ;

    public List<String> getProductSuggestionsStartingWith(String letter) throws SQLException, ClassNotFoundException;

}
