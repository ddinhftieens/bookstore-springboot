package com.ddinhftieens.demo_crud.Service.impl;

import com.ddinhftieens.demo_crud.DAO.HomeDAO;
import com.ddinhftieens.demo_crud.Model.OrderDTO;
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

    @Override
    public void orderr(OrderDTO orderDTO) {
        homeDAO.orderr(orderDTO);
    }

    @Override
    public void updatequantity(String IDcode, int number) {
        homeDAO.upadtequantity(IDcode, number);
    }

    @Override
    public List<OrderDTO> getorderbyIDuser(int id) {
        return homeDAO.getorderbyIDuser(id);
    }
}
