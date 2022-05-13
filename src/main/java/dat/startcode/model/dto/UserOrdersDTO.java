package dat.startcode.model.dto;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class UserOrdersDTO {
    //    order_id, username, inquiry_id, cost_price, final_price, status_id, timestamp
    private int order_id;
    private String username;
    private int phone_no;
    private int inquiry_id;
    private double cost_price;
    private double final_price;
    private int status_id;
    private LocalDateTime timestamp;

    public UserOrdersDTO(int order_id, String username, int phone_no, int inquiry_id, double cost_price, double final_price, int status_id, LocalDateTime timestamp) {
        this.order_id = order_id;
        this.username = username;
        this.phone_no = phone_no;
        this.inquiry_id = inquiry_id;
        this.cost_price = cost_price;
        this.final_price = final_price;
        this.status_id = status_id;
        this.timestamp = timestamp; //String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getInquiry_id() {
        return inquiry_id;
    }

    public void setInquiry_id(int inquiry_id) {
        this.inquiry_id = inquiry_id;
    }

    public double getCost_price() {
        return cost_price;
    }

    public void setCost_price(double cost_price) {
        this.cost_price = cost_price;
    }

    public double getFinal_price() {
        return final_price;
    }

    public void setFinal_price(double final_price) {
        this.final_price = final_price;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(int phone_no) {
        this.phone_no = phone_no;
    }
//    public LocalDateTime getTimestamp() {
//        return timestamp;
//    }

    public String getTimestamp() { //lille hack til at vise datoen pænt
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String temp = timestamp.format(formatter);
        return temp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
