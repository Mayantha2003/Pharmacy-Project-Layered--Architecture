package lk.ijse.pharamacymanagementlayerdsystem.bo.custom;

import lk.ijse.pharamacymanagementlayerdsystem.bo.SuperBO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.ExpiredMedicineDTO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.LowStockDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface DashBoardBO extends SuperBO {

    public double getTotalRevenue(String filter) throws SQLException, ClassNotFoundException;

    public int getOrderCount(String filter) throws SQLException, ClassNotFoundException;

    public int getCustomerCount() throws SQLException, ClassNotFoundException;

    public Map<String, Double> getChartData(String filter) throws SQLException, ClassNotFoundException;

    public int getExpiredCount() throws SQLException, ClassNotFoundException;

    public double getTotalProfit(String filter) throws SQLException, ClassNotFoundException;

    public int getLowStockCount() throws SQLException, ClassNotFoundException;

    public ArrayList<ExpiredMedicineDTO>getExpiredMedicines() throws SQLException, ClassNotFoundException;

    public ArrayList<LowStockDTO>getLowStockMedicines() throws SQLException, ClassNotFoundException;
}
