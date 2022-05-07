package dat.startcode.model.entities;

public class BillsOfMaterial {

    private int bomId;
    private int materialId;
    private int orderId;
    private int quantity;
    private String description;

    public BillsOfMaterial(int bomId, int materialId, int orderId, int quantity, String description) {
        this.bomId = bomId;
        this.materialId = materialId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.description = description;
    }

    public int getBomId() {
        return bomId;
    }

    public void setBomId(int bomId) {
        this.bomId = bomId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BillsOfMaterial{" +
                "bomId=" + bomId +
                ", materialId=" + materialId +
                ", orderId=" + orderId +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                '}';
    }
}
