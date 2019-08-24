package com.ddinhftieens.demo_crud.Model;

public class OrderDTO {
    private int ID,quantity;
    private String IDcode,address,phone,name,datecreated;
    private float cost,price;

    public OrderDTO(){}

    public String getIDcode() {
        return IDcode;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
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

    public void setDatecreated(String datecreated) {
        this.datecreated = datecreated;
    }

    public String getDatecreated() {
        return datecreated;
    }

    public String getPhone() {
        return phone;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
