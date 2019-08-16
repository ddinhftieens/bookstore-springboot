package com.ddinhftieens.demo_crud.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ddinhftieens.demo_crud.Model.CustomerDTO;
import com.ddinhftieens.demo_crud.Service.CustomerService;

@Controller
public class CustomerController{
    @Value("${logging.message}")
    private String message;

    @Autowired
    private CustomerService customerService;

    private CustomerDTO customerDTO = null;

    @GetMapping("/customer/login")
    public String login(Model model){
        model.addAttribute("customerDTO",new CustomerDTO());
        return "client/login";
    }
    @GetMapping("/home")
    public String home(Model model){
        if(this.customerDTO == null){
            return "home";
        }
        else{
            model.addAttribute("user",this.customerDTO.getIDcard());
            return "home";
        }
    }

    @PostMapping("/home")
    public String checklogin(Model model,@ModelAttribute("customerDTO") CustomerDTO customerDTO){
        this.customerDTO = customerService.getByUserName(customerDTO.getUsername());
        int k = customerService.checklogin(customerDTO.getUsername(),customerDTO.getPassword());
        if(k==1){
            if(customerService.getByUserName(customerDTO.getUsername()).getRole().equals("Admin")){
                return "redirect:/admin/home";
            }
//            model.addAttribute("user",this.customerDTO.getIDcard());
            return "redirect:/home";
        }
        else {
            return "redirect:/customer/login?message="+message;
        }
    }
    @GetMapping("/customer/register")
    public String register(Model model){
        model.addAttribute("customerDTO",new CustomerDTO());
        return "client/register";
    }
    @GetMapping("/customer/information")
    public String customerinformation(Model model){
        model.addAttribute("customerDTO",this.customerDTO);
        return "client/customer-info";
    }
    @PostMapping("/customer/update-information")
    public String updateInformation(Model model,@ModelAttribute("customerDTO") CustomerDTO customerDTO){
        if(customerService.checkregister_update(customerDTO.getUsername(),customerDTO.getEmail(),customerDTO.getIDcard(),customerDTO.getPhone(),this.customerDTO.getUsername())==0){
            this.customerDTO.setAddress(customerDTO.getAddress());
            this.customerDTO.setEmail(customerDTO.getEmail());
            this.customerDTO.setPhone(customerDTO.getPhone());
            customerService.edit(this.customerDTO);
//            model.addAttribute("user",this.customerDTO.getIDcard());
            return "redirect:/home";
        }
        else {
            return "redirect:/customer/information?message=thong tin cap nhat khong hop le";

        }
    }
    @PostMapping("/customer/register")
    public String addcustomer(Model model,@ModelAttribute("customerDTO") CustomerDTO customerDTO){
        if(customerService.checkregister_update(customerDTO.getUsername(),customerDTO.getEmail(),customerDTO.getIDcard(),customerDTO.getPhone(),"register") == 0){
            customerService.add(customerDTO);
            return "redriect:/home";
        }
        else{
            return "redirect:/customer/register?message=thong tin dang ki khong hop le";
        }
    }
    @PostMapping("/customer/change-pass")
    public String changepass(@RequestParam("oldpassword") String old,@RequestParam("newpassword") String newp){
        if(customerService.changepass(old,newp,this.customerDTO.getUsername())==0){
            return "redirect:/customer/information?message=mat khau khong chinh xac";
        }
        else {
            return "redirect:/home?message=thay doi mat khau thanh cong";
        }
    }
}
