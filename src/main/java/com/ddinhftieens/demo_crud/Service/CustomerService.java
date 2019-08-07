package com.ddinhftieens.demo_crud.Service;

import com.ddinhftieens.demo_crud.Model.CustomerDTO;
import java.util.List;

public interface CustomerService {
    int checklogin(String user,String pass);
    void add(CustomerDTO customerDTO);
    void edit(CustomerDTO customerDTO);
    void delete(CustomerDTO customerDTO);
    CustomerDTO getByID(int ID);
    CustomerDTO getByUserName(String name);
    List<CustomerDTO> getByName(String name);
    List<CustomerDTO> getAll();
}
