package lk.ijse.pharamacymanagementlayerdsystem.dto;

import lk.ijse.pharamacymanagementlayerdsystem.view.tdm.GRNLineTM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GrnDTO {
    private long grnId;
    private String grnNumber;
    private long supplierId;
    private String supplierName;
    private LocalDate grnDate;
    private double totalAmount;
    private String notes;
    private long receivedBy;

    private List<GRNLineTM> lines = new ArrayList<>();

    public GrnDTO() {
    }

    public GrnDTO(String grnNumber, long supplierId, String supplierName, LocalDate grnDate, String notes,
                  double totalAmount, long receivedBy, List<GRNLineTM> lines) {
        this.grnNumber = grnNumber;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.grnDate = grnDate;
        this.notes = notes;
        this.totalAmount = totalAmount;
        this.receivedBy = receivedBy;
        this.lines = lines;
    }

    public GrnDTO(long grnId, String grnNumber, long supplierId, String supplierName, LocalDate grnDate,
                  double totalAmount, String notes, long receivedBy, List<GRNLineTM> lines) {
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

    public List<GRNLineTM> getLines() {
        return lines;
    }

    public void setLines(List<GRNLineTM> lines) {
        this.lines = lines;
    }
    public void addLine(GRNLineTM line) {
        this.lines.add(line);
        this.totalAmount += line.getLineTotal();
    }

    @Override
    public String toString() {
        return "GrnDTO{" +
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