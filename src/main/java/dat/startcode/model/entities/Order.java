package dat.startcode.model.entities;

import java.sql.Timestamp;

public class Order {

    private int orderId;
    private int userId;
    private int requestId;
    private String drawing;
    private double costPrice;
    private double finalPrice;
    private int status;
    private Timestamp timestamp;

    public Order(int orderId, int userId, int requestId, String drawing, double costPrice, double finalPrice, int status, Timestamp timestamp) {
        this.orderId = orderId;
        this.userId = userId;
        this.requestId = requestId;
        this.drawing = drawing;
        this.costPrice = costPrice;
        this.finalPrice = finalPrice;
        this.status = status;
        this.timestamp = timestamp;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getDrawing() {
        return drawing;
    }

    public void setDrawing(String drawing) {
        this.drawing = drawing;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", requestId=" + requestId +
                ", drawing='" + drawing + '\'' +
                ", costPrice=" + costPrice +
                ", finalPrice=" + finalPrice +
                ", status=" + status +
                ", timestamp=" + timestamp +
                '}';
    }
}
