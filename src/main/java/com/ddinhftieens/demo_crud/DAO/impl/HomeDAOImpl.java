package com.ddinhftieens.demo_crud.DAO.impl;

import com.ddinhftieens.demo_crud.DAO.HomeDAO;
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

    @Override
    public void orderr(OrderDTO orderDTO) {
        String sql = "insert into listorder(IDUser, IDCode, Name, Address, Phone, Quantity, Cost, Description, Timetransport, Datecreated, Status) values (?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql,new Object[]{orderDTO.getIDuser(),orderDTO.getIDcode(),orderDTO.getName(),orderDTO.getAddress(),orderDTO.getPhone(),orderDTO.getQuantity(),orderDTO.getCost(),orderDTO.getNote(),orderDTO.getTime(),orderDTO.getDatecreated(),orderDTO.getStatus()});
    }

    @Override
    public void upadtequantity(String IDcode, int number) {
        String sql = "update product set Quantity = ? where IDcode = ?";
        int quantity = this.jdbcTemplate.queryForObject("select Quantity from product where IDcode = ?",new Object[]{IDcode}, Integer.class);
        this.jdbcTemplate.update(sql,new Object[]{quantity+number,IDcode});
    }

    @Override
    public List<OrderDTO> getorderbyIDuser(int id) {
        String sql = "select * from listorder where IDUser = " + id;
        List<OrderDTO> orderDTOList = this.jdbcTemplate.query(sql,new OrderRowMapper());
        return orderDTOList;
    }
}
