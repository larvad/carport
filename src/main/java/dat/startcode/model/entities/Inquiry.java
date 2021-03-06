package dat.startcode.model.entities;

import java.sql.Timestamp;
import java.util.Objects;

public class Inquiry {

    private int inquiryId;
    private int carpWidth;
    private int carpLength;
    private String roofType;
    private int roofSlope;
    private int shedWidth;
    private int shedLength;
    private Timestamp timestamp;


    public Inquiry(int inquiryId, int carpWidth, int carpLength, String roofType, int slope, int shedWidth, int shedLength, Timestamp timestamp) {
        this.inquiryId = inquiryId;
        this.carpWidth = carpWidth;
        this.carpLength = carpLength;
        this.roofType = roofType;
        this.roofSlope = slope;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
        this.timestamp = timestamp;
    }

    public Inquiry(int carpWidth, int carpLength, String roofType) {
        this.carpWidth = carpWidth;
        this.carpLength = carpLength;
        this.roofType = roofType;
    }


    public Inquiry(int inquiryId, int carpWidth, int carpLength, String roofType, int roofSlope, int shedWidth, int shedLength) {
        this.inquiryId = inquiryId;
        this.carpWidth = carpWidth;
        this.carpLength = carpLength;
        this.roofType = roofType;
        this.roofSlope = roofSlope;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
        this.timestamp = null;
    }

    public int getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(int inquiryId) {
        this.inquiryId = inquiryId;
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
                "requestId=" + inquiryId +
                ", carpWidth=" + carpWidth +
                ", carpLength=" + carpLength +
                ", roofType='" + roofType + '\'' +
                ", slope=" + roofSlope +
                ", shedWidth=" + shedWidth +
                ", shedLength=" + shedLength +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Inquiry)) return false;
        Inquiry inquiry = (Inquiry) o;
        return getCarpWidth() == inquiry.getCarpWidth() && getCarpLength() == inquiry.getCarpLength() && getRoofType().equals(inquiry.getRoofType()) && getRoofSlope() == inquiry.getRoofSlope() &&
                getShedWidth() == inquiry.getShedWidth() && getShedLength() == inquiry.getShedLength();
    }

//    @Override
//    public int hashCode()
//    {
//        return Objects.hash(getUsername(), getPassword(), getRoleId());
//    }
}
