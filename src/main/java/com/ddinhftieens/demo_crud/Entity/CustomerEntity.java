package com.ddinhftieens.demo_crud.Entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @Column(name = "ID",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @Column(name = "FristName", nullable = false)
    private String fristname;
    @Column(name = "LastName",nullable = false)
    private String lastname;
    @Column(name = "UserName",nullable = false,unique = true)
    private String username;
    @Column(name = "PassWord",nullable = false)
    private String password;
    @Column(name = "Gender",nullable = false)
    private String gender;
    @Column(name = "Address",nullable = false)
    private String address;
    @Column(name = "Email",nullable = false)
    private String email;
    @Column(name = "IDcard",nullable = false,unique = true)
    private String idcard;
    @Column(name = "Phone",nullable = false,unique = true)
    private String phone;
    @Column(name = "Role",nullable = false)
    private String role;
    @Column(name = "DateofBrith",nullable = false)
    private String dateofbrith;

    public CustomerEntity(){

    }
    public CustomerEntity(String fristname, String lastname, String username, String password, String gender, String address, String email, String IDcard, String phone, String role, String dateofbrith){
        this.fristname = fristname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.idcard = IDcard;
        this.phone = phone;
        this.role = role;
        this.dateofbrith = dateofbrith;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public int getID() {
        return ID;
    }

    public String getFristname() {
        return fristname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getDateofbrith() {
        return dateofbrith;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getRole() {
        return role;
    }

    public String getIdcard() {
        return idcard;
    }

}
