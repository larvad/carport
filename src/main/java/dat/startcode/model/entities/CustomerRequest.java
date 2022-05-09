package dat.startcode.model.entities;

import java.sql.Timestamp;

public class CustomerRequest {

    private int requestId;
    private int carpWidth;
    private int carpLength;
    private String roofType;
    private int roofSlope;
    private int shedWidth;
    private int shedLength;
    private Timestamp timestamp;

    public CustomerRequest(int requestId, int carpWidth, int carpLength, String roofType, int slope, int shedWidth, int shedLength, Timestamp timestamp) {
        this.requestId = requestId;
        this.carpWidth = carpWidth;
        this.carpLength = carpLength;
        this.roofType = roofType;
        this.roofSlope = slope;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
        this.timestamp = timestamp;
    }

    public CustomerRequest(int carpWidth, int carpLength, String roofType) {
        this.carpWidth = carpWidth;
        this.carpLength = carpLength;
        this.roofType = roofType;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getCarpWidth() {
        return carpWidth;
    }

    public void setCarpWidth(int carpWidth) {
        this.carpWidth = carpWidth;
    }

    public int getCarpLength() {
        return carpLength;
    }

    public void setCarpLength(int carpLength) {
        this.carpLength = carpLength;
    }

    public String getRoofType() {
        return roofType;
    }

    public void setRoofType(String roofType) {
        this.roofType = roofType;
    }

    public int getRoofSlope() {
        return roofSlope;
    }

    public void setRoofSlope(int roofSlope) {
        this.roofSlope = roofSlope;
    }

    public int getShedWidth() {
        return shedWidth;
    }

    public void setShedWidth(int shedWidth) {
        this.shedWidth = shedWidth;
    }

    public int getShedLength() {
        return shedLength;
    }

    public void setShedLength(int shedLength) {
        this.shedLength = shedLength;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestId=" + requestId +
                ", carpWidth=" + carpWidth +
                ", carpLength=" + carpLength +
                ", roofType='" + roofType + '\'' +
                ", slope=" + roofSlope +
                ", shedWidth=" + shedWidth +
                ", shedLength=" + shedLength +
                ", timestamp=" + timestamp +
                '}';
    }
}
