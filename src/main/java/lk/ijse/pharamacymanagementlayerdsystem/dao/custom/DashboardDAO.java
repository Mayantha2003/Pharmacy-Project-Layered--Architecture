package lk.ijse.pharamacymanagementlayerdsystem.dao.custom;

import lk.ijse.pharamacymanagementlayerdsystem.dao.SuperDAO;

import java.sql.SQLException;
import java.util.Map;

public interface DashboardDAO extends SuperDAO {

    public double getTotalRevenue(String filter) throws SQLException, ClassNotFoundException;

    public int getOrderCount(String filter) throws SQLException, ClassNotFoundException;

    public int getCustomerCount() throws SQLException, ClassNotFoundException;

    public Map<String, Double> getChartData(String filter) throws SQLException, ClassNotFoundException;

    public int getExpiredCount() throws SQLException, ClassNotFoundException;
}