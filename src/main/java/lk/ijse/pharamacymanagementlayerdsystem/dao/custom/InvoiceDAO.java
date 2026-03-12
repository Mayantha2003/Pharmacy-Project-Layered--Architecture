package lk.ijse.pharamacymanagementlayerdsystem.dao.custom;

import lk.ijse.pharamacymanagementlayerdsystem.dao.CRUDUtil;
import lk.ijse.pharamacymanagementlayerdsystem.dao.CrudDAO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Invoice;
import lk.ijse.pharamacymanagementlayerdsystem.util.DataFilter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public interface InvoiceDAO extends CrudDAO<Invoice> {

    public String generateNextInvoiceNumber() throws SQLException, ClassNotFoundException;

    public long getLastId() throws SQLException, ClassNotFoundException ;

    public double getTotalRevenue(String filter) throws SQLException, ClassNotFoundException ;

    public int getOrderCount(String filter) throws SQLException, ClassNotFoundException ;

    public Map<String, Double> getChartData(String filter) throws SQLException, ClassNotFoundException ;

    }