package lk.ijse.pharamacymanagementlayerdsystem.bo.custom.impl;

import lk.ijse.pharamacymanagementlayerdsystem.bo.custom.DashBoardBO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.DAOFactory;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.DashboardDAO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.QueryDAO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.ExpiredMedicineDTO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.LowStockDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class DashboardBOImpl implements DashBoardBO {

    DashboardDAO dashboardDAO = (DashboardDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DASHBOARD);
    QueryDAO queryDAO = (QueryDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public double getTotalRevenue(String filter) throws SQLException, ClassNotFoundException {
        return dashboardDAO.getTotalRevenue(filter);
    }

    @Override
    public int getOrderCount(String filter) throws SQLException, ClassNotFoundException {
        return dashboardDAO.getOrderCount(filter);
    }

    @Override
    public int getCustomerCount() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getCustomerCount();
    }

    @Override
    public Map<String, Double> getChartData(String filter) throws SQLException, ClassNotFoundException {
        return dashboardDAO.getChartData(filter);
    }

    @Override
    public int getExpiredCount() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getExpiredCount();
    }

    @Override
    public double getTotalProfit(String filter) throws SQLException, ClassNotFoundException {
        return queryDAO.getTotalProfit(filter);
    }

    @Override
    public int getLowStockCount() throws SQLException, ClassNotFoundException {
        return queryDAO.getLowStockCount();
    }

    @Override
    public ArrayList<ExpiredMedicineDTO> getExpiredMedicines() throws SQLException, ClassNotFoundException {

        ResultSet rs = queryDAO.getExpiredMedicines();
        ArrayList<ExpiredMedicineDTO> expiredList = new ArrayList<>();

        while (rs.next()) {
            expiredList.add(new ExpiredMedicineDTO(
                    rs.getString("medicine_name"),
                    rs.getDate("expiry_date").toString(),
                    rs.getInt("qty")
            ));
        }
        return expiredList;
    }

    @Override
    public ArrayList<LowStockDTO> getLowStockMedicines() throws SQLException, ClassNotFoundException {
        ResultSet rs = queryDAO.getLowStockMedicines();
        ArrayList<LowStockDTO> lowStockList = new ArrayList<>();

        while (rs.next()) {
            lowStockList.add(new LowStockDTO(
                    rs.getString("medicine_name"),
                    rs.getInt("qty_remaining")
            ));
        }
        return lowStockList;
    }
}
