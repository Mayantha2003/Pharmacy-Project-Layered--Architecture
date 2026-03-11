package lk.ijse.pharamacymanagementlayerdsystem.dto;

public class DashboardStatsDTO {
    private double totalRevenue;
    private double totalProfit;
    private int    orderCount;
    private int    customerCount;
    private int    expiredCount;
    private int    lowStockCount;

    public DashboardStatsDTO() {}

    public DashboardStatsDTO(double totalRevenue, double totalProfit, int orderCount,
                             int customerCount, int expiredCount, int lowStockCount) {
        this.totalRevenue  = totalRevenue;
        this.totalProfit   = totalProfit;
        this.orderCount    = orderCount;
        this.customerCount = customerCount;
        this.expiredCount  = expiredCount;
        this.lowStockCount = lowStockCount;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    public int getExpiredCount() {
        return expiredCount;
    }

    public void setExpiredCount(int expiredCount) {
        this.expiredCount = expiredCount;
    }

    public int getLowStockCount() {
        return lowStockCount;
    }

    public void setLowStockCount(int lowStockCount) {
        this.lowStockCount = lowStockCount;
    }

    @Override
    public String toString() {
        return "DashboardStatsDTO{" +
                "totalRevenue=" + totalRevenue +
                ", totalProfit=" + totalProfit +
                ", orderCount=" + orderCount +
                ", customerCount=" + customerCount +
                ", expiredCount=" + expiredCount +
                ", lowStockCount=" + lowStockCount +
                '}';
    }
}

