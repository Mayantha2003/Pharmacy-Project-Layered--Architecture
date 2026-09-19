package lk.ijse.pharamacymanagementlayerdsystem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvoicePaymentDTO {
    private long paymentId;
    private long invoiceId;
    private LocalDate paymentDate;
    private String paymentMethod;
    private BigDecimal amount;
    private String referenceNumber;
    private String notes;

    public InvoicePaymentDTO() {
    }

    public InvoicePaymentDTO(long invoiceId, LocalDate paymentDate, String paymentMethod, BigDecimal amount,
                             String referenceNumber, String notes) {
        this.invoiceId = invoiceId;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.referenceNumber = referenceNumber;
        this.notes = notes;
    }

    public InvoicePaymentDTO(long paymentId, long invoiceId, LocalDate paymentDate, String paymentMethod,
                             BigDecimal amount, String referenceNumber, String notes) {
        this.paymentId = paymentId;
        this.invoiceId = invoiceId;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.referenceNumber = referenceNumber;
        this.notes = notes;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "InvoicePaymentDTO{" +
                "paymentId=" + paymentId +
                ", invoiceId=" + invoiceId +
                ", paymentDate=" + paymentDate +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", amount=" + amount +
                ", referenceNumber='" + referenceNumber + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
