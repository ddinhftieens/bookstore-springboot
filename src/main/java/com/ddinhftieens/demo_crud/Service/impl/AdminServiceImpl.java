package com.ddinhftieens.demo_crud.Service.impl;

import com.ddinhftieens.demo_crud.DAO.CustomerDAO;
import com.ddinhftieens.demo_crud.Model.CustomerDTO;
import com.ddinhftieens.demo_crud.Model.OrderDTO;
import com.ddinhftieens.demo_crud.Model.ProductDTO;
import com.ddinhftieens.demo_crud.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ddinhftieens.demo_crud.DAO.AdminDAO;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService,UserDetailsService{

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private AdminDAO adminDAO;

    @Override
    public void addproduct(ProductDTO productDTO) {
        adminDAO.addproduct(productDTO);
    }

    @Override
    public List<ProductDTO> getAll() {
        return adminDAO.getAll();
    }

    @Override
    public List<ProductDTO> getAlltype(int type) {
        return adminDAO.getAlltype(type);
    }

    @Override
    public ProductDTO getIDcode(String IDcode) {
        return adminDAO.getIDcode(IDcode);
    }

    @Override
    public void deleteproduct(String IDcode) {
        adminDAO.deleteproduct(IDcode);
    }

    @Override
    public void updateproduct(ProductDTO productDTO) {
        adminDAO.updateproduct(productDTO);
    }

    @Override
    public List<OrderDTO> getAllorder() {
        return adminDAO.getAllorder();
    }

    @Override
    public OrderDTO getByID(int ID) {
        return adminDAO.getByID(ID);
    }

    @Override
    public void updatestatus(String status, int ID) {
        adminDAO.updatestatus(status, ID);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        CustomerDTO customerDTO = customerDAO.getByUserName(s);
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(customerDTO.getRole());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        return new User(s, customerDTO.getPassword(), authorities);
    }
}
