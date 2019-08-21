package com.ddinhftieens.demo_crud.Model;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(resultSet.getString(2));
        productDTO.setAuthor(resultSet.getString(3));
        productDTO.setDateofissue(resultSet.getString(4));
        productDTO.setNumberpage(resultSet.getInt(5));
        productDTO.setTranslator(resultSet.getString(6));
        productDTO.setDescription(resultSet.getString(7));
        productDTO.setCost(resultSet.getFloat(8));
        productDTO.setSale(resultSet.getInt(9));
        productDTO.setImage(resultSet.getBytes(10));
        productDTO.setDatecreated(resultSet.getString(11));
        productDTO.setQuantity(resultSet.getInt(12));
        productDTO.setTypee(resultSet.getInt(13));
        productDTO.setIDcode(resultSet.getString(14));
        return productDTO;
    }
}
