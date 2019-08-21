package com.ddinhftieens.demo_crud.Model;

import org.springframework.web.multipart.MultipartFile;

public class ProductDTO {
    private byte[] image;
    private String IDcode;
    private int numberpage,sale,quantity,typee;
    private float cost,price;
    private String name,author,dateofissue,translator,description,datecreated;
    private String imagebase64;
    public ProductDTO(){

    }
    public ProductDTO(String IDcode,String name,String author,String dateofissue,int numberpage,String translator, String description,float cost,int sale,byte[] image,String datecreated,int quantity,int typee){
        this.IDcode = IDcode;
        this.name = name;
        this.author = author;
        this.dateofissue = dateofissue;
        this.numberpage = numberpage;
        this.translator = translator;
        this.description = description;
        this.cost = cost;
        this.sale = sale;
        this.image = image;
        this.datecreated = datecreated;
        this.quantity = quantity;
        this.typee = typee;

    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setNumberpage(int numberpage) {
        this.numberpage = numberpage;
    }

    public String getIDcode() {
        return IDcode;
    }

    public void setIDcode(String IDcode) {
        this.IDcode = IDcode;
    }

    public byte[] getImage() {
        return image;
    }

    public float getCost() {
        return cost;
    }

    public int getNumberpage() {
        return numberpage;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setDateofissue(String dateofissue) {
        this.dateofissue = dateofissue;
    }

    public String getTranslator() {
        return translator;
    }

    public String getDateofissue() {
        return dateofissue;
    }

    public String getAuthor() {
        return author;
    }

    public String getDatecreated() {
        return datecreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDatecreated(String datecreated) {
        this.datecreated = datecreated;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTypee() {
        return typee;
    }

    public void setTypee(int typee) {
        this.typee = typee;
    }

    public String getImagebase64() {
        return imagebase64;
    }

    public void setImagebase64(String imagebase64) {
        this.imagebase64 = imagebase64;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return IDcode + " " + name + " " + author + " " + dateofissue + " " + numberpage + " " + translator + " " + description + " " + cost + " " + sale + " " + image + " " + datecreated + " " + quantity;
    }
}
