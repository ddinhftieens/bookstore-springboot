package com.ddinhftieens.demo_crud.Model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setID(resultSet.getInt(1));
        orderDTO.setIDuser(resultSet.getInt(2));
        orderDTO.setIDcode(resultSet.getString(3));
        orderDTO.setName(resultSet.getString(4));
        orderDTO.setAddress(resultSet.getString(5));
        orderDTO.setPhone(resultSet.getString(6));
        orderDTO.setQuantity(resultSet.getString(7));
        orderDTO.setCost(resultSet.getString(8));
        orderDTO.setNote(resultSet.getString(9));
        orderDTO.setTime(resultSet.getString(10));
        orderDTO.setDatecreated(resultSet.getString(11));
        orderDTO.setStatus(resultSet.getString(12));
        return orderDTO;
    }
}
