package com.ddinhftieens.demo_crud.DAO.impl;

import com.ddinhftieens.demo_crud.DAO.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;

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
    public int checkregister_update(String user, String email, String idcard, String phone,String type) {
        String sql="";
        if(type.equals("register")){
            sql = "select count(0) from customer where UserName = ? or Email = ? or IDcard = ? or Phone = ?";
            return this.jdbcTemplate.queryForObject(sql,new Object[]{user,email,idcard,phone},Integer.class);
        }
        else {
            sql = "select count(0) from customer where (UserName = ? or Email = ? or IDcard = ? or Phone = ?)and UserName!=?";
            return this.jdbcTemplate.queryForObject(sql,new Object[]{user,email,idcard,phone,type},Integer.class);
        }
    }

    @Override
    public void add(CustomerDTO customerDTO) {
        String sql = "insert into customer (FristName, LastName, UserName, PassWord, Gender, Address, Email, IDcard, Phone, Role, DateofBrith,JoinDate) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql,new Object[]{customerDTO.getFristname(),customerDTO.getLastname(),customerDTO.getUsername(),customerDTO.getPassword(),customerDTO.getGender(),customerDTO.getAddress(),customerDTO.getEmail(),customerDTO.getIDcard(),customerDTO.getPhone(),"ROLE_USER",customerDTO.getDateofbrith(),customerDTO.getJoindate()});
    }

    @Override
    public void edit(CustomerDTO customerDTO) {
        String sql = "update customer set Address = ?,Email = ?,Phone = ? where UserName = ?";
        this.jdbcTemplate.update(sql,new Object[]{customerDTO.getAddress(),customerDTO.getEmail(),customerDTO.getPhone(),customerDTO.getUsername()});
    }

    @Override
    public void delete(int ID) {
        String sql = "delete from customer where ID = " + ID;
        this.jdbcTemplate.update(sql,new Object[]{});
    }

    @Override
    public CustomerDTO getByID(int ID) {
        String sql = "select * from customer where ID = " + ID;
        return (CustomerDTO) this.jdbcTemplate.queryForObject(sql,new CustomerRowMapper());
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
        String sql = "select * from customer";
        List<CustomerDTO> customerDTOList = this.jdbcTemplate.query(sql,new CustomerRowMapper());
        return customerDTOList;
    }

    @Override
    public int changepass(String old, String newp,String user) {
        String sql = "update customer set PassWord = ? where PassWord = ? and UserName = ?";
        return this.jdbcTemplate.update(sql,new Object[]{newp,old,user});
    }

}
