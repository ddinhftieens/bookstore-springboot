package com.ddinhftieens.demo_crud.Service;
import com.ddinhftieens.demo_crud.Model.ProductDTO;

import java.util.List;

public interface AdminService {
    void addproduct(ProductDTO productDTO);
    List<ProductDTO> getAll();
    ProductDTO getIDcode(String IDcode);
    void deleteproduct(String IDcode);
    void updateproduct(String IDcode);
}
