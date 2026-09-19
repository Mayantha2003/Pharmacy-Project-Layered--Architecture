package lk.ijse.pharamacymanagementlayerdsystem.dto;

import lk.ijse.pharamacymanagementlayerdsystem.view.tdm.GRNLineTM;

import java.time.LocalDate;

public class GrnLineDTO extends GRNLineTM {
    private long grnDetailId;
    private long grnId;
    private long productId;
    private String productName;
    private String batchNumber;
    private LocalDate expiryDate;
    private LocalDate manufactureDate;
    private int quantity;
    private double costPrice;
    private double sellingPrice;
    private double lineTotal;

    public GrnLineDTO() {
    }

    public GrnLineDTO(long grnId,long productId, String productName, LocalDate expiryDate, String batchNumber,
                      LocalDate manufactureDate, int quantity, double sellingPrice, double costPrice,
                      double lineTotal) {
        this.grnId = grnId;
        this.productId = productId;
        this.productName = productName;
        this.expiryDate = expiryDate;
        this.batchNumber = batchNumber;
        this.manufactureDate = manufactureDate;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.costPrice = costPrice;
        this.lineTotal = lineTotal;
    }

    public GrnLineDTO(long grnDetailId,long grnId,long productId, String productName, String batchNumber, LocalDate expiryDate,
                      LocalDate manufactureDate, int quantity, double costPrice, double sellingPrice,
                      double lineTotal) {
        this.grnDetailId = grnDetailId;
        this.grnId = grnId;
        this.productId = productId;
        this.productName = productName;
        this.batchNumber = batchNumber;
        this.expiryDate = expiryDate;
        this.manufactureDate = manufactureDate;
        this.quantity = quantity;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.lineTotal = lineTotal;
    }

    public long getGrnDetailId() {
        return grnDetailId;
    }

    public void setGrnDetailId(long grnDetailId) {
        this.grnDetailId = grnDetailId;
    }

    public long getGrnId() {
        return grnId;
    }

    public void setGrnId(long grnId) {
        this.grnId = grnId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(double lineTotal) {
        this.lineTotal = lineTotal;
    }

    public void calculateLineTotal() {
        this.lineTotal = this.quantity * this.costPrice;
    }

    @Override
    public String toString() {
        return "GrnLineDTO{" +
                "grnDetailId=" + grnDetailId +
                ", grnId=" + grnId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", batchNumber='" + batchNumber + '\'' +
                ", expiryDate=" + expiryDate +
                ", manufactureDate=" + manufactureDate +
                ", quantity=" + quantity +
                ", costPrice=" + costPrice +
                ", sellingPrice=" + sellingPrice +
                ", lineTotal=" + lineTotal +
                '}';
    }
}
