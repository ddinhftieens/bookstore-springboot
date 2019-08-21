package com.ddinhftieens.demo_crud.Service.impl;

import com.ddinhftieens.demo_crud.Model.ProductDTO;
import com.ddinhftieens.demo_crud.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ddinhftieens.demo_crud.DAO.AdminDAO;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

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
}
