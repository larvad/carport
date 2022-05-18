package dat.startcode.model.dto;

public class BomDTO {
    private int orderId;
    private String type;
    private int length;
    private int quantity;
    private String unit;
    private String  description;

    public BomDTO(int orderId, String type, int length, int quantity, String unit, String description) {
        this.orderId = orderId;
        this.type = type;
        this.length = length;
        this.quantity = quantity;
        this.unit = unit;
        this.description = description;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BomDTO{" +
                "orderId=" + orderId +
                ", type='" + type + '\'' +
                ", length=" + length +
                ", quantity=" + quantity +
                ", unit='" + unit + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
