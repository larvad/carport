package dat.startcode.model.dto;

public class StatusDTO {
    int orderID;
    int statusID;

    public StatusDTO(int orderID, int statusID) {
        this.orderID = orderID;
        this.statusID = statusID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }
}
