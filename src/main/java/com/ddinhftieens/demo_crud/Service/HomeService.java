package com.ddinhftieens.demo_crud.Service;
import com.ddinhftieens.demo_crud.Model.OrderDTO;
import com.ddinhftieens.demo_crud.Model.ProductDTO;

import java.util.List;

public interface HomeService {
    List<ProductDTO> getbyType(String type);
    void orderr(OrderDTO orderDTO);
    void updatequantity(String IDcode,int number);
    List<OrderDTO> getorderbyIDuser(int id);
}
