package com.stone.auto.test.interfaces.entity;

public class OrderInfo {
    long id;
    int driver_id;
    String order_id;
    int order_status;
    String city_code;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }
    @Override
    public String toString() {

        return "OrderInfo{" +
                "id=" + id +
                ", driver_id='" + driver_id + '\'' +
                ", order_id='" + order_id + '\'' +
                ", order_status='" + order_status + '\'' +
                ", city_code='" + city_code + '\'' +
                '}';
    }


}
