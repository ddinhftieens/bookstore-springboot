package com.ddinhftieens.demo_crud.DAO;
import com.ddinhftieens.demo_crud.Model.OrderDTO;
import com.ddinhftieens.demo_crud.Model.ProductDTO;

import java.util.List;

public interface HomeDAO {
    List<ProductDTO> getbyType(String type);
    void orderr(OrderDTO orderDTO);
    void upadtequantity(String IDcode,int number);
    List<OrderDTO> getorderbyIDuser(int id);
}
