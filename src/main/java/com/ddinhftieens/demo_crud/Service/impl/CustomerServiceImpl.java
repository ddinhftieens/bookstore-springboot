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
    public void add(CustomerDTO customerDTO) {
        customerDAO.add(customerDTO);
    }

    @Override
    public void edit(CustomerDTO customerDTO) {

    }

    @Override
    public void delete(CustomerDTO customerDTO) {

    }

    @Override
    public CustomerDTO getByID(int ID) {
        return null;
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
        return null;
    }
}
