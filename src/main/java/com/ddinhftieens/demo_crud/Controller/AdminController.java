package com.ddinhftieens.demo_crud.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.ddinhftieens.demo_crud.Model.CustomerDTO;
import com.ddinhftieens.demo_crud.Service.CustomerService;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("customerDTO")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CustomerService customerService;

    private List<CustomerDTO> customerDTO;
    @GetMapping("/home")
    public String Adminhome(){
        return "admin/adminHome";
    }
    @GetMapping("/customerinformation")
    public String adminCIF(Model model){
        customerDTO = new ArrayList<>();
        customerDTO = customerService.getAll();
        model.addAttribute("customerDTO",customerDTO);
        return "admin/adminCIF";
    }
    @GetMapping("/product")
    public String adminProduct(){
        return "admin/adminProduct";
    }
    @GetMapping("/orderlist")
    public String order(){
        return "admin/order";
    }
    @GetMapping("/changeinformation")
    public String changepass(){
        return "admin/pass";
    }
}
