package com.ddinhftieens.demo_crud.Service;
import com.ddinhftieens.demo_crud.Model.OrderDTO;
import com.ddinhftieens.demo_crud.Model.ProductDTO;

import java.util.List;

public interface AdminService {
    void addproduct(ProductDTO productDTO);
    List<ProductDTO> getAll();
    List<ProductDTO> getAlltype(int type);
    ProductDTO getIDcode(String IDcode);
    void deleteproduct(String IDcode);
    void updateproduct(ProductDTO productDTO);
    List<OrderDTO> getAllorder();
    OrderDTO getByID(int ID);
    void updatestatus(String status, int ID);
}
