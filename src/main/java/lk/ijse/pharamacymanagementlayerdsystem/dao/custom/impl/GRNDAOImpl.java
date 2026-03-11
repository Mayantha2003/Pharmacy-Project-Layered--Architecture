package lk.ijse.pharamacymanagementlayerdsystem.dao.custom.impl;

import lk.ijse.pharamacymanagementlayerdsystem.dao.CRUDUtil;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.GRNDAO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Grn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GRNDAOImpl implements GRNDAO {

    @Override
    public boolean save(Grn entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO GRN (grn_number, supplier_id, grn_date, total_amount,notes, received_by) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        return CRUDUtil.execute(sql,
                entity.getGrnNumber(),
                entity.getSupplierId(),
                entity.getGrnDate(),
                entity.getTotalAmount(),
                entity.getNotes(),
                entity.getReceivedBy());
    }

    @Override
    public String generateNextGrnNumber() throws SQLException, ClassNotFoundException {
        String sql = "SELECT grn_number FROM GRN ORDER BY grn_id DESC LIMIT 1";
        ResultSet rs = CRUDUtil.execute(sql);

        if (rs.next()) {
            String lastNumber = rs.getString("grn_number");
            try {
                int num = Integer.parseInt(lastNumber.split("-")[2]);
                return "GRN-" + LocalDate.now().getYear() + "-" + String.format("%03d", num + 1);
            } catch (Exception e) {
                return "GRN-" + LocalDate.now().getYear() + "-001";
            }
        }
        return "GRN-" + LocalDate.now().getYear() + "-001";
    }

    @Override
    public long getLastId() throws SQLException, ClassNotFoundException {
        String keySql = "SELECT LAST_INSERT_ID()";
        ResultSet rs = CRUDUtil.execute(keySql);
        if (rs.next()) {
            return rs.getLong(1);
        }
        return -1;
    }



   //Not use Only Override method
    @Override
    public ArrayList<Grn> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Grn customDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Grn search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
