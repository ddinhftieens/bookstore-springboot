package com.ddinhftieens.demo_crud.DAO;
import com.ddinhftieens.demo_crud.Model.ProductDTO;

import java.util.List;

public interface HomeDAO {
    List<ProductDTO> getbyType(String type);
}
