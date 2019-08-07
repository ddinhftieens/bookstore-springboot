package com.ddinhftieens.demo_crud.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ddinhftieens.demo_crud.Model.CustomerDTO;
import com.ddinhftieens.demo_crud.Service.CustomerService;

@Controller
@SessionAttributes("customerDTO")
public class CustomerController{
    @Value("${logging.message}")
    private String message;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer/login")
    public String login(Model model){
        model.addAttribute("customerDTO",new CustomerDTO());
        return "client/login";
    }
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @PostMapping("/home")
    public String checklogin(Model model,@ModelAttribute("customerDTO") CustomerDTO customerDTO){
        int k = customerService.checklogin(customerDTO.getUsername(),customerDTO.getPassword());
        if(k==1){
            model.addAttribute("user",customerDTO.getUsername());
            return "home";
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
    public String customerinformation(Model model,@SessionAttribute("customerDTO") CustomerDTO customerDTO){
        model.addAttribute("customerDTO",customerService.getByUserName(customerDTO.getUsername()));
        return "client/customerInformation";
    }
    @PostMapping("/updateInformation")
    public String updateInformation(){
        return "redirect:/customer/home";
    }
    @PostMapping("/customer/register")
    public String addcustomer(@ModelAttribute("customerDTO") CustomerDTO customerDTO){
        customerService.add(customerDTO);
        return "redirect/customer/home";
    }
}
