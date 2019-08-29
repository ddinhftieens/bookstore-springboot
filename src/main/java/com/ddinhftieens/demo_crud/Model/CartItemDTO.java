package com.ddinhftieens.demo_crud.Model;

public class CartItemDTO {
    private String IDcode,name;
    private int quantity;
    private float cost,price;
    private String image;

    public CartItemDTO(){}

    public CartItemDTO(String IDcode, String image, int quantity, float cost, float price){
        this.IDcode = IDcode;
        this.image = image;
        this.quantity = quantity;
        this.cost = cost;
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getCost() {
        return cost;
    }

    public String getIDcode() {
        return IDcode;
    }

    public void setIDcode(String IDcode) {
        this.IDcode = IDcode;
    }

    @Override
    public String toString() {
        return IDcode + " " + image + " " + quantity + " " + cost + " " + price;
    }
}
