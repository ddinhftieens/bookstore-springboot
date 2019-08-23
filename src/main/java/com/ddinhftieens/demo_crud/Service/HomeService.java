package com.ddinhftieens.demo_crud.Service;
import com.ddinhftieens.demo_crud.Model.ProductDTO;

import java.util.List;

public interface HomeService {
    List<ProductDTO> getbyType(String type);
}
