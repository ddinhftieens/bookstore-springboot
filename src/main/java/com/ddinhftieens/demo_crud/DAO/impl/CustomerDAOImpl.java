package com.ddinhftieens.demo_crud.DAO.impl;

import com.ddinhftieens.demo_crud.DAO.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.ddinhftieens.demo_crud.Model.CustomerDTO;
import com.ddinhftieens.demo_crud.Model.CustomerRowMapper;

import javax.sql.DataSource;
import java.util.List;
@Repository
@Transactional
public class CustomerDAOImpl implements CustomerDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataScoure(DataSource dataScoure){
        this.jdbcTemplate = new JdbcTemplate(dataScoure);
    }

    @Override
    public int checklogin(String user, String pass) {
        String sql = "select count(0) from customer where UserName = ? and PassWord = ?";
        return this.jdbcTemplate.queryForObject(sql,new Object[]{user,pass},Integer.class);
    }

    @Override
    public void add(CustomerDTO customerDTO) {
        String sql = "insert into customer (FristName, LastName, UserName, PassWord, Gender, Address, Email, IDcard, Phone, Role, DateofBrith) values (?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql,new Object[]{customerDTO.getFristname(),customerDTO.getLastname(),customerDTO.getUsername(),customerDTO.getPassword(),customerDTO.getGender(),customerDTO.getAddress(),customerDTO.getEmail(),customerDTO.getIDcard(),customerDTO.getPhone(),"Customer",customerDTO.getDateofbrith()});
    }

    @Override
    public void edit(CustomerDTO customerDTO) {
        String sql = "update customer set ... where...";
        this.jdbcTemplate.update(sql,new Object[]{});
    }

    @Override
    public void delete(CustomerDTO customerDTO) {
        String sql = "delete from customer where ...";
        this.jdbcTemplate.update(sql,new Object[]{});
    }

    @Override
    public CustomerDTO getByID(int ID) {
        return null;
    }

    @Override
    public CustomerDTO getByUserName(String name) {
        String sql = "select * from customer where UserName = '" + name + "'";
        CustomerDTO customerDTO = (CustomerDTO) this.jdbcTemplate.queryForObject(sql,new CustomerRowMapper());
        return customerDTO;
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
