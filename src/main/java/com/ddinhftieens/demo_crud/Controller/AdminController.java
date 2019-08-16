package com.ddinhftieens.demo_crud.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.ddinhftieens.demo_crud.Model.CustomerDTO;
import com.ddinhftieens.demo_crud.Service.CustomerService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    @Qualifier("demo")
    private String bean;

    private List<CustomerDTO> customerDTO;

    @GetMapping("/home")
    public String adminhome(){
        customerDTO = new ArrayList<>();
        customerDTO = customerService.getAll();
        return "admin/admin-home";
    }
    @GetMapping("/customer-info")
    public String admin_cinfor(Model model,@ModelAttribute("search") String name){
        if(!name.isEmpty()){
            System.out.println("OK");
        }
        model.addAttribute("customerDTO",customerDTO);
        return "admin/admin-customer-info";
    }
    @GetMapping("/product")
    public String adminproduct(){
        return "admin/admin-product";
    }
    @GetMapping("/orderlist")
    public String order(){
        return "admin/order";
    }
    @GetMapping("/changeinformation")
    public String changePass(){
        return "admin/pass";
    }

    @GetMapping("/showcustomer")
    public void showcustomer(@RequestParam("ID") int id){
        System.out.println(id);
    }
    @GetMapping("/delete")
    public String deletecustomer(@RequestParam("ID") int id){
        customerService.delete(id);
        return "redirect:/admin/home?message=xoa tai khoan thanh cong";
    }
}
