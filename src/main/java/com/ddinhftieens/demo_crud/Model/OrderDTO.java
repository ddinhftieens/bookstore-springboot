package com.ddinhftieens.demo_crud.Model;

public class OrderDTO {
    private int IDuser,ID;
    private String IDcode,address,phone,name,datecreated,note,status;
    private String cost,quantity,time;
    private float price;

    public OrderDTO(){}

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public int getID() {
        return ID;
    }
    public String getIDcode() {
        return IDcode;
    }

    public void setIDuser(int ID) {
        this.IDuser = ID;
    }

    public int getIDuser() {
        return IDuser;
    }

    public void setIDcode(String IDcode) {
        this.IDcode = IDcode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDatecreated(String datecreated) {
        this.datecreated = datecreated;
    }

    public String getDatecreated() {
        return datecreated;
    }

    public String getPhone() {
        return phone;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
