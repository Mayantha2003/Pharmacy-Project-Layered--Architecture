package lk.ijse.pharamacymanagementlayerdsystem.bo.custom.impl;

import lk.ijse.pharamacymanagementlayerdsystem.bo.custom.DashBoardBO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.DAOFactory;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.BatchDAO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.CustomerDAO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.InvoiceDAO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.QueryDAO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.ExpiredMedicineDTO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.LowStockDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DashboardBOImpl implements DashBoardBO {

    BatchDAO batchDAO = (BatchDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BATCH);
    QueryDAO queryDAO = (QueryDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    CustomerDAO customerBO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    InvoiceDAO invoiceBO =(InvoiceDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.INVOICE);

    @Override
    public double getTotalRevenue(String filter) throws SQLException, ClassNotFoundException {
        return invoiceBO.getTotalRevenue(filter);
    }

    @Override
    public int getOrderCount(String filter) throws SQLException, ClassNotFoundException {
        return invoiceBO.getOrderCount(filter);
    }

    @Override
    public int getCustomerCount() throws SQLException, ClassNotFoundException {
        return customerBO.getCustomerCount();
    }

    @Override
    public Map<String, Double> getChartData(String filter) throws SQLException, ClassNotFoundException {
        return invoiceBO.getChartData(filter);
    }

    @Override
    public int getExpiredCount() throws SQLException, ClassNotFoundException {
        return batchDAO.getExpiredCount();
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
    public List<ExpiredMedicineDTO> getExpiredMedicines() throws SQLException, ClassNotFoundException {

        ResultSet rs = queryDAO.getExpiredMedicines();
        List<ExpiredMedicineDTO> expiredList = new ArrayList<>();

        while (rs.next()) {
            expiredList.add(new ExpiredMedicineDTO(
                    rs.getString("name"),
                    rs.getString("batch_number"),
                    rs.getDate("expiry_date").toString(),
                    rs.getInt("qty_remaining")
            ));
        }
        return expiredList;
    }

    @Override
    public List<LowStockDTO> getLowStockMedicines() throws SQLException, ClassNotFoundException {
        ResultSet rs = queryDAO.getLowStockMedicines();
        List<LowStockDTO> lowStockList = new ArrayList<>();

        while (rs.next()) {
            lowStockList.add(new LowStockDTO(
                    rs.getString("name"),
                    rs.getInt("total_qty")
            ));
        }
        return lowStockList;
    }
}
