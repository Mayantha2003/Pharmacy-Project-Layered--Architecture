package lk.ijse.pharamacymanagementlayerdsystem.bo.custom;

import lk.ijse.pharamacymanagementlayerdsystem.bo.SuperBO;
import lk.ijse.pharamacymanagementlayerdsystem.dto.InvoiceDTO;

import java.sql.SQLException;

public interface InvoiceBO extends SuperBO {

   public long saveInvoiceWithTransaction(InvoiceDTO invoiceDTO) throws SQLException, ClassNotFoundException;

   public String generateNextInvoiceNumber() throws SQLException, ClassNotFoundException;

}

