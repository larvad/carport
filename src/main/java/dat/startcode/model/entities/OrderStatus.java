package dat.startcode.model.entities;

public class OrderStatus {

    private int statusId;
    private String name;

    public OrderStatus(int statusId, String name) {
        this.statusId = statusId;
        this.name = name;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "statusId=" + statusId +
                ", name='" + name + '\'' +
                '}';
    }
}
