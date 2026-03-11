package lk.ijse.pharamacymanagementlayerdsystem.bo.custom.impl;

import lk.ijse.pharamacymanagementlayerdsystem.bo.custom.InvoiceBO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.DAOFactory;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.BatchDAO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.InvoiceDAO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.InvoiceDetailDAO;
import lk.ijse.pharamacymanagementlayerdsystem.dao.custom.InvoicePaymentDAO;
import lk.ijse.pharamacymanagementlayerdsystem.db.DBConnection;
import lk.ijse.pharamacymanagementlayerdsystem.dto.InvoiceDTO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.InvoiceDetailsDTO;
import lk.ijse.pharamacymanagementlayerdsystem.entity.Invoice;
import lk.ijse.pharamacymanagementlayerdsystem.entity.InvoiceDetails;
import lk.ijse.pharamacymanagementlayerdsystem.entity.InvoicePayment;
import lk.ijse.pharamacymanagementlayerdsystem.view.tdm.InvoiceTM;

import java.sql.Connection;
import java.sql.SQLException;

public class InvoiceBOImpl implements InvoiceBO {

    InvoiceDAO invoiceDAO = (InvoiceDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.INVOICE);
    InvoiceDetailDAO invoiceDetailDAO =(InvoiceDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.INVOICEDETAIL);
    InvoicePaymentDAO invoicePaymentDAO = (InvoicePaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.INVOICEPAYMENT);
    BatchDAO batchDAO = (BatchDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BATCH);

    @Override
    public long saveInvoiceWithTransaction(InvoiceDTO invoiceDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);
        try {
            // Step 1: InvoiceDTO → Invoice Entity (DTO → Entity conversion)
            boolean isInvoiceSaved = invoiceDAO.save(new Invoice(
                    invoiceDTO.getInvoiceNumber(),
                    invoiceDTO.getInvoiceDate(),
                    (invoiceDTO.getCustomerId() == 0) ? null : invoiceDTO.getCustomerId(),
                    invoiceDTO.getTotalAmount(),
                    invoiceDTO.getPaidAmount(),
                    invoiceDTO.getCashierId()
            ));

            if (!isInvoiceSaved) {
                connection.rollback();
                return -1;
            }
            long invoiceId = invoiceDAO.getLastId();

            // Step 2: Each InvoiceDetailDTO → InvoiceDetail Entity + Batch stock update
            for (InvoiceTM detailDTO : invoiceDTO.getDetails()) {

                boolean isDetailSaved = invoiceDetailDAO.save(new InvoiceDetails(
                        invoiceId,
                        detailDTO.getBatchId(),
                        detailDTO.getQuantity(),
                        detailDTO.getSellingPrice()
                ));

                boolean isStockUpdated = batchDAO.updateBatchStock(
                        detailDTO.getBatchId(), detailDTO.getQuantity()
                );

                if (!isDetailSaved || !isStockUpdated) {
                    connection.rollback();
                    return -1;
                }
            }

            // Step 3: InvoiceDTO → InvoicePayment Entity
            boolean isPaymentSaved = invoicePaymentDAO.save(new InvoicePayment(
                    invoiceId,
                    invoiceDTO.getPaymentMethod(),
                    invoiceDTO.getPaidAmount()
            ));

            if (!isPaymentSaved) {
                connection.rollback();
                return -1;
            }

            connection.commit();
            return invoiceId;

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public String generateNextInvoiceNumber() throws SQLException, ClassNotFoundException {
        return invoiceDAO.generateNextInvoiceNumber();
    }
}
