package com.ddinhftieens.demo_crud.DAO.impl;

import com.ddinhftieens.demo_crud.DAO.HomeDAO;
import com.ddinhftieens.demo_crud.Model.ProductDTO;
import com.ddinhftieens.demo_crud.Model.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Base64;
import java.util.List;
@Repository
@Transactional
public class HomeDAOImpl implements HomeDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataScoure(DataSource dataScoure){
        this.jdbcTemplate = new JdbcTemplate(dataScoure);
    }
    @Override
    public List<ProductDTO> getbyType(String type) {
        String sql = "select * from product where Type = '" + type +"'";
        List<ProductDTO> productDTOList = this.jdbcTemplate.query(sql,new ProductRowMapper());
        for(ProductDTO k:productDTOList){
            k.setImagebase64(Base64.getEncoder().encodeToString(k.getImage()));
            k.setPrice(k.getCost() - (k.getCost()*k.getSale())/100);
        }
        return productDTOList;
    }
}
