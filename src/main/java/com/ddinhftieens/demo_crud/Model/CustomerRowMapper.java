package com.ddinhftieens.demo_crud.Model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setID(resultSet.getInt(1));
        customerDTO.setFristname(resultSet.getString(2));
        customerDTO.setLastname(resultSet.getString(3));
        customerDTO.setUsername(resultSet.getString(4));
        customerDTO.setPassword(resultSet.getString(5));
        customerDTO.setGender(resultSet.getString(6));
        customerDTO.setAddress(resultSet.getString(7));
        customerDTO.setEmail(resultSet.getString(8));
        customerDTO.setIDcard(resultSet.getString(9));
        customerDTO.setPhone(resultSet.getString(10));
        customerDTO.setRole(resultSet.getString(11));
        customerDTO.setDateofbrith(resultSet.getString(12));
        customerDTO.setJoindate(resultSet.getString(13));
        return customerDTO;
    }
}
