package com.ddinhftieens.demo_crud;
import com.ddinhftieens.demo_crud.Service.AdminService;
import com.ddinhftieens.demo_crud.Model.ProductDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpSession;
import java.util.List;

@SpringBootApplication
public class DemoCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoCrudApplication.class, args);
    }

    @Autowired
    private AdminService adminService;

    @Bean(name = "demo")
    public String getString(){
        return "demoBean";
    }

    @Bean(name = "listproduct")
    public List<ProductDTO> productDTOList(){
        return adminService.getAll();
    }

//    @Bean(name = "getproduct")
//    public ProductDTO productDTO(String IDcode){
//        return adminService.getIDcode(IDcode);
//    }
}
