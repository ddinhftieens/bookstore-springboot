package com.ddinhftieens.demo_crud.Service.impl;

import com.ddinhftieens.demo_crud.Model.CustomerDTO;
import com.ddinhftieens.demo_crud.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import com.ddinhftieens.demo_crud.DAO.CustomerDAO;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public int checklogin(String user, String pass) {
        return customerDAO.checklogin(user,pass);
    }

    @Override
    public int checkregister_update(String user, String email, String idcard, String phone,String type) {
        return customerDAO.checkregister_update(user, email, idcard, phone,type);
    }

    @Override
    public void add(CustomerDTO customerDTO) {
        customerDAO.add(customerDTO);
    }

    @Override
    public void edit(CustomerDTO customerDTO) {
        customerDAO.edit(customerDTO);
    }

    @Override
    public void delete(int ID) {
        customerDAO.delete(ID);
    }

    @Override
    public CustomerDTO getByID(int ID) {
        return customerDAO.getByID(ID);
    }

    @Override
    public CustomerDTO getByUserName(String name) {
        return customerDAO.getByUserName(name);
    }

    @Override
    public List<CustomerDTO> getByName(String name) {
        return null;
    }

    @Override
    public List<CustomerDTO> getAll() {
        return customerDAO.getAll();
    }

    @Override
    public int changepass(String old, String newp,String user) {
        return customerDAO.changepass(old,newp,user);
    }
}
