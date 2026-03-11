package lk.ijse.pharamacymanagementlayerdsystem.dao.custom.impl;

import lk.ijse.pharamacymanagementlayerdsystem.dao.CRUDUtil;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.DashboardDAO;
import lk.ijse.pharamacymanagementlayerdsystem.util.DataFilter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DashboardDAOImpl implements DashboardDAO {

    @Override
    public double getTotalRevenue(String filter) throws SQLException, ClassNotFoundException {
        String condition = filter.equals("Today") ? "= CURDATE()" : ">= " + DataFilter.getDateFilter(filter);
        String sql = "SELECT SUM(total_amount) FROM Invoice WHERE invoice_date " + condition;
        ResultSet rs = CRUDUtil.execute(sql);
        return rs.next() ? rs.getDouble(1) : 0.0;
    }

    @Override
    public int getOrderCount(String filter) throws SQLException, ClassNotFoundException {
        String condition = filter.equals("Today") ? "= CURDATE()" : ">= " + DataFilter.getDateFilter(filter);
        String sql = "SELECT COUNT(*) FROM Invoice WHERE invoice_date " + condition;
        ResultSet rs = CRUDUtil.execute(sql);
        return rs.next() ? rs.getInt(1) : 0;
    }

    @Override
    public int getCustomerCount() throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtil.execute("SELECT COUNT(*) FROM Customers");
        return rs.next() ? rs.getInt(1) : 0;
    }

    @Override
    public Map<String, Double> getChartData(String filter) throws SQLException, ClassNotFoundException {
        String condition = ">= " + DataFilter.getDateFilter(filter);
        String sql = "SELECT invoice_date, SUM(total_amount) FROM Invoice "
                + "WHERE invoice_date " + condition + " GROUP BY invoice_date ORDER BY invoice_date ASC";

        ResultSet rs = CRUDUtil.execute(sql);
        Map<String, Double> data = new LinkedHashMap<>();
        while (rs.next()) {
            data.put(rs.getString(1), rs.getDouble(2));
        }
        return data;
    }

    @Override
    public int getExpiredCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM Batches WHERE expiry_date <= DATE_ADD(CURDATE(), INTERVAL 30 DAY) AND qty_remaining > 0";
        ResultSet rs = CRUDUtil.execute(sql);
        return rs.next() ? rs.getInt(1) : 0;
    }
}
