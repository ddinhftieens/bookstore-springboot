package com.ddinhftieens.demo_crud.DAO.impl;

import com.ddinhftieens.demo_crud.DAO.AdminDAO;
import com.ddinhftieens.demo_crud.Model.OrderDTO;
import com.ddinhftieens.demo_crud.Model.OrderRowMapper;
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
public class AdminDAOImpl implements AdminDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataScoure(DataSource dataScoure){
        this.jdbcTemplate = new JdbcTemplate(dataScoure);
    }
    @Override
    public void addproduct(ProductDTO productDTO) {
        String sql = "insert into product (Name, Author, DateofIssue, NumberPage, Translator, Description, Cost, Sale, Image, DateCreated, Quantity, Type, IDcode) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql,new Object[]{productDTO.getName(),productDTO.getAuthor(),productDTO.getDateofissue(),productDTO.getNumberpage(),productDTO.getTranslator(),productDTO.getDescription(),productDTO.getCost(),productDTO.getSale(),productDTO.getImage(),productDTO.getDatecreated(),productDTO.getQuantity(),productDTO.getTypee(),productDTO.getIDcode()});
    }

    @Override
    public List<ProductDTO> getAll() {
        String sql = "select * from product";
        List<ProductDTO> productDTOList = this.jdbcTemplate.query(sql, new ProductRowMapper());
        for(ProductDTO i:productDTOList){
            i.setImagebase64(Base64.getEncoder().encodeToString(i.getImage()));
            i.setPrice(i.getCost() - (i.getCost()*i.getSale())/100);
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> getAlltype(int type) {
        String sql = "select * from product where Type like "+ "'" + type + "%'";
        List<ProductDTO> productDTOList = this.jdbcTemplate.query(sql, new ProductRowMapper());
        for(ProductDTO i:productDTOList){
            i.setImagebase64(Base64.getEncoder().encodeToString(i.getImage()));
            i.setPrice(i.getCost() - (i.getCost()*i.getSale())/100);
        }
        return productDTOList;
    }

    @Override
    public ProductDTO getIDcode(String IDcode) {
        String sql = "select * from product where IDcode = '" + IDcode + "'";
        ProductDTO productDTO = (ProductDTO) this.jdbcTemplate.queryForObject(sql,new ProductRowMapper());
        productDTO.setImagebase64(Base64.getEncoder().encodeToString(productDTO.getImage()));
        productDTO.setPrice(productDTO.getCost() - (productDTO.getCost()*productDTO.getSale())/100);
        return productDTO;
    }

    @Override
    public void deleteproduct(String IDcode) {
        String sql = "delete from product where IDcode = ?";
        this.jdbcTemplate.update(sql,new Object[]{IDcode});
    }

    @Override
    public void updateproduct(ProductDTO productDTO) {
        String sql = "update product set Cost = ?, Sale = ?, Quantity = ? where IDcode = ?";
        this.jdbcTemplate.update(sql,new Object[]{productDTO.getCost(),productDTO.getSale(),productDTO.getQuantity(),productDTO.getIDcode()});
    }

    @Override
    public List<OrderDTO> getAllorder() {
        String sql = "select * from listorder";
        List<OrderDTO> orderDTOList = this.jdbcTemplate.query(sql,new OrderRowMapper());
        return orderDTOList;
    }

    @Override
    public OrderDTO getByID(int ID) {
        String sql = "select * from listorder where ID = " + ID;
        return (OrderDTO) this.jdbcTemplate.queryForObject(sql,new OrderRowMapper());
    }

    @Override
    public void updatestatus(String status, int ID) {
        String sql = "update listorder set Status = ? where ID = ?";
        this.jdbcTemplate.update(sql,new Object[]{status,ID});
    }
}
