package lk.ijse.pharamacymanagementlayerdsystem.dao.custom.impl;

import lk.ijse.pharamacymanagementlayerdsystem.dao.CRUDUtil;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.QueryDAO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Batch;
import lk.ijse.pharamacymanagementlayerdsystem.entity.ProductCategory;
import lk.ijse.pharamacymanagementlayerdsystem.util.DataFilter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    //For Product Join Query
    @Override
    public ArrayList<ProductCategory> getAllProductsWithCategoryName() throws SQLException, ClassNotFoundException {
        String sql = "SELECT p.*, c.name AS cat_name " +
                "FROM Products p " +
                "JOIN Categories c " +
                "ON p.cat_id = c.cat_id ORDER BY p.product_id ASC";
        ResultSet rs = CRUDUtil.execute(sql);

        ArrayList<ProductCategory> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new ProductCategory(
                    rs.getLong("product_id"),
                    rs.getString("code"),
                    rs.getString("name"),
                    rs.getString("pack_size"),
                    rs.getLong("cat_id"),
                    rs.getString("generic_name"),
                    rs.getString("strength"),
                    rs.getString("dosage_form"),
                    rs.getString("company"),
                    rs.getString("cat_name")
            ));
        }
        return list;    }

    @Override
    public ProductCategory searchExactProduct(String searchText) throws SQLException, ClassNotFoundException {

        String sql = "SELECT p.*, c.name AS cat_name "
                + "FROM Products p "
                + "JOIN Categories c ON p.cat_id = c.cat_id "
                + "WHERE p.product_id = ? OR p.code = ?";

        long id = 0;
        try {
            id = Long.parseLong(searchText);
        } catch (NumberFormatException e) {
            id = -1;
        }

        ResultSet rs = CRUDUtil.execute(sql, id, searchText);

        if (rs.next()) {
            Long productID = rs.getLong("product_id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            String packtSize = rs.getString("pack_size");
            Long catId = rs.getLong("cat_id");
            String genaricName = rs.getString("generic_name");
            String strength = rs.getString("strength");
            String dosageForm = rs.getString("dosage_form");
            String company = rs.getString("company");
            String categoryName = rs.getString("cat_name");

            ProductCategory entity = new ProductCategory(productID, code, name, packtSize, catId,
                    genaricName, strength, dosageForm, company, categoryName);

            return entity;
        }
        return null;
    }



    //For Batches join Query
    @Override
    public List<String> searchAvailableBatches(String product) throws SQLException, ClassNotFoundException {
        String sql = "SELECT b.batch_id, b.batch_number, p.name AS product_name, b.expiry_date, b.qty_remaining, b.selling_price "
                + "FROM Batches b "
                + "JOIN Products p ON b.product_id = p.product_id "
                + "WHERE p.name LIKE ? AND b.qty_remaining > 0 "
                + "ORDER BY b.expiry_date ASC";

        ResultSet rs = CRUDUtil.execute(sql, "%" + product + "%");

        List<String> batches = new ArrayList<>();
        while (rs.next()) {
            String display = rs.getString("product_name") + " | Batch: " + rs.getString("batch_number")
                    + " | Expiry: " + rs.getDate("expiry_date")
                    + " | Qty: " + rs.getInt("qty_remaining")
                    + " | Price: Rs. " + String.format("%.2f", rs.getDouble("selling_price"));

            batches.add(display);
        }
        return batches;
    }

    @Override
    public Batch getBatchByDisplay(String display) throws SQLException, ClassNotFoundException {
            String batchNumber = display.split("Batch: ")[1].split(" | ")[0].trim();

            String sql = "SELECT b.*, p.name AS product_name "
                    + "FROM Batches b "
                    + "JOIN Products p ON b.product_id = p.product_id "
                    + "WHERE b.batch_number = ?";

            ResultSet rs = CRUDUtil.execute(sql, batchNumber);

            if (rs.next()) {
                return new Batch(
                        rs.getLong("batch_id"),
                        rs.getString("product_name"),
                        rs.getString("batch_number"),
                        rs.getDate("expiry_date").toLocalDate(),
                        rs.getInt("qty_remaining"),
                        rs.getDouble("selling_price")
                );
            }
            return null;
        }

    @Override
    public List<String> getProductSuggestionsStartingWith(String letter) throws SQLException, ClassNotFoundException {
        String sql = "SELECT DISTINCT p.name "
                + "FROM Products p "
                + "JOIN Batches b ON p.product_id = b.product_id "
                + "WHERE UPPER(p.name) LIKE ? AND b.qty_remaining > 0 "
                + "ORDER BY p.name";

        ResultSet rs = CRUDUtil.execute(sql, letter + "%");

        List<String> suggestions = new ArrayList<>();
        while (rs.next()) {
            suggestions.add(rs.getString("name"));
        }
        return suggestions;    }





    //For DashBoard Join Queries
    @Override
    public double getTotalProfit(String filter) throws SQLException, ClassNotFoundException {
        String condition = filter.equals("Today") ? "= CURDATE()" : ">= " + DataFilter.getDateFilter(filter);

        String sql = "SELECT SUM((id.selling_price - b.cost_price) * id.quantity) "
                + "FROM Invoice_Details id "
                + "JOIN Batches b ON id.batch_id = b.batch_id "
                + "JOIN Invoice i ON id.invoice_id = i.invoice_id "
                + "WHERE i.invoice_date " + condition;

        ResultSet rs = CRUDUtil.execute(sql);
        return rs.next() ? rs.getDouble(1) : 0.0;
    }

    @Override
    public int getLowStockCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM ("
                + "SELECT p.product_id "
                + "FROM Products p "
                + "JOIN Batches b ON p.product_id = b.product_id "
                + "JOIN Suppliers s ON b.supplier_id = s.supplier_id "
                +
                "GROUP BY p.product_id, s.supplier_name "
                + "HAVING SUM(b.qty_remaining) <= 10"
                + ") AS total_low_stock";

        ResultSet rs = CRUDUtil.execute(sql);
        return rs.next() ? rs.getInt(1) : 0;
    }

    @Override
    public ResultSet getExpiredMedicines() throws SQLException, ClassNotFoundException {
        String sql = "SELECT p.name, b.batch_number, b.expiry_date, b.qty_remaining "
                + "FROM Batches b "
                + "JOIN Products p ON b.product_id = p.product_id "
                + "WHERE b.expiry_date <= DATE_ADD(CURDATE(), INTERVAL 30 DAY) "
                + "AND b.qty_remaining > 0 "
                + "ORDER BY b.expiry_date ASC";
        return CRUDUtil.execute(sql);
    }

    @Override
    public ResultSet getLowStockMedicines() throws SQLException, ClassNotFoundException {
        String sql = "SELECT p.name, SUM(b.qty_remaining) AS total_qty "
                + "FROM Batches b "
                + "JOIN Products p ON b.product_id = p.product_id "
                + "GROUP BY p.product_id, p.name "
                + "HAVING total_qty <= 10  "
                + "ORDER BY total_qty ASC";

        return CRUDUtil.execute(sql);
    }
}
