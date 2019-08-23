package com.ddinhftieens.demo_crud.Service.impl;

import com.ddinhftieens.demo_crud.DAO.HomeDAO;
import com.ddinhftieens.demo_crud.Model.ProductDTO;
import com.ddinhftieens.demo_crud.Service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class HomeServiceImpl implements HomeService {
    @Autowired
    private HomeDAO homeDAO;

    @Override
    public List<ProductDTO> getbyType(String type) {
        return homeDAO.getbyType(type);
    }
}
