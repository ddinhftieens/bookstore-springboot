package com.ddinhftieens.demo_crud.DAO;

import com.ddinhftieens.demo_crud.Model.CustomerDTO;
import java.util.List;

public interface CustomerDAO {
    int checklogin(String user,String pass);
    int checkregister_update(String user,String email,String idcard, String phone,String type);
    void add(CustomerDTO customerDTO);
    void edit(CustomerDTO customerDTO);
    void delete(int ID);
    CustomerDTO getByID(int ID);
    CustomerDTO getByUserName(String name);
    List<CustomerDTO> getByName(String name);
    List<CustomerDTO> getAll();
    int changepass(String old,String newp,String user);
}
