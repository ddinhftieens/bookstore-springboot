package com.ddinhftieens.demo_crud.DAO;
import com.ddinhftieens.demo_crud.Model.ProductDTO;

import java.util.List;

public interface AdminDAO {
    void addproduct(ProductDTO productDTO);
    List<ProductDTO> getAll();
    ProductDTO getIDcode(String IDcode);
    void deleteproduct(String IDcode);
    void updateproduct(String IDcode);
}
