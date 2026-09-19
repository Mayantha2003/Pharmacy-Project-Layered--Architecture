package lk.ijse.pharamacymanagementlayerdsystem.entity;

import lk.ijse.pharamacymanagementlayerdsystem.dto.GrnLineDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Grn {
    private long grnId;
    private String grnNumber;
    private long supplierId;
    private String supplierName;
    private LocalDate grnDate;
    private double totalAmount;
    private String notes;
    private long receivedBy;

    private List<GrnLineDTO> lines = new ArrayList<>();

    public Grn() {
    }

    public Grn(String grnNumber, long supplierId, String supplierName, LocalDate grnDate, double totalAmount,
               String notes, long receivedBy, List<GrnLineDTO> lines) {
        this.grnNumber = grnNumber;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.grnDate = grnDate;
        this.totalAmount = totalAmount;
        this.notes = notes;
        this.receivedBy = receivedBy;
        this.lines = lines;
    }

    public Grn(long grnId, String grnNumber, long supplierId, String supplierName, LocalDate grnDate,
               double totalAmount, String notes, long receivedBy, List<GrnLineDTO> lines) {
        this.grnId = grnId;
        this.grnNumber = grnNumber;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.grnDate = grnDate;
        this.totalAmount = totalAmount;
        this.notes = notes;
        this.receivedBy = receivedBy;
        this.lines = lines;
    }

    public Grn(String grnNumber, long supplierId, LocalDate grnDate, double totalAmount, String notes, long receivedBy) {
        this.grnNumber = grnNumber;
        this.supplierId =supplierId;
        this.grnDate = grnDate;
        this.totalAmount = totalAmount;
        this.notes = notes;
        this.receivedBy = receivedBy;
    }

    public long getGrnId() {
        return grnId;
    }

    public void setGrnId(long grnId) {
        this.grnId = grnId;
    }

    public String getGrnNumber() {
        return grnNumber;
    }

    public void setGrnNumber(String grnNumber) {
        this.grnNumber = grnNumber;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public LocalDate getGrnDate() {
        return grnDate;
    }

    public void setGrnDate(LocalDate grnDate) {
        this.grnDate = grnDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(long receivedBy) {
        this.receivedBy = receivedBy;
    }

    public List<GrnLineDTO> getLines() {
        return lines;
    }

    public void setLines(List<GrnLineDTO> lines) {
        this.lines = lines;
    }
    public void addLine(GrnLineDTO line) {
        this.lines.add(line);
        this.totalAmount += line.getLineTotal();
    }

    @Override
    public String toString() {
        return "Grn{" +
                "grnId=" + grnId +
                ", grnNumber='" + grnNumber + '\'' +
                ", supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", grnDate=" + grnDate +
                ", totalAmount=" + totalAmount +
                ", notes='" + notes + '\'' +
                ", receivedBy=" + receivedBy +
                ", lines=" + lines +
                '}';
    }
}


