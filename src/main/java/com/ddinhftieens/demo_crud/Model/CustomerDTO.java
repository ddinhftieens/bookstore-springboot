package com.ddinhftieens.demo_crud.Model;

public class CustomerDTO {

    private int ID;
    private String fristname,lastname,username,password,gender,address,email,IDcard,phone,role,dateofbrith,joindate;

    public CustomerDTO(){

    }
    public CustomerDTO(String fristname, String lastname, String username, String password, String gender, String address, String email, String IDcard, String phone, String role, String dateofbrith,String joindate){
        this.fristname = fristname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.IDcard = IDcard;
        this.phone = phone;
        this.role = role;
        this.dateofbrith = dateofbrith;
        this.joindate = dateofbrith;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFristname() {
        return fristname;
    }

    public int getID() {
        return ID;
    }

    public String getRole() {
        return role;
    }

    public String getGender() {
        return gender;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public String getIDcard() {
        return IDcard;
    }

    public String getPhone() {
        return phone;
    }

    public String getDateofbrith() {
        return dateofbrith;
    }

    public String getEmail() {
        return email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFristname(String fristname) {
        this.fristname = fristname;
    }

    public void setIDcard(String IDcard) {
        this.IDcard = IDcard;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDateofbrith(String dateofbrith) {
        this.dateofbrith = dateofbrith;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJoindate() {
        return joindate;
    }

    public void setJoindate(String joindate) {
        this.joindate = joindate;
    }

    @Override
    public String toString() {
        return fristname +" "+lastname+" "+username+" "+password+" "+gender+" "+dateofbrith+" "+phone+" "+email+" "+address+" "+IDcard+" "+role;
    }
}
