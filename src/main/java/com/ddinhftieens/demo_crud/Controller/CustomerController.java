package com.ddinhftieens.demo_crud.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ddinhftieens.demo_crud.Model.CustomerDTO;
import com.ddinhftieens.demo_crud.Service.CustomerService;

import java.util.Calendar;

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
        int k = customerService.checklogin(customerDTO.getUsername(),customerDTO.getPassword());
        if(k==1){
            this.customerDTO = customerService.getByUserName(customerDTO.getUsername());
            if(customerService.getByUserName(customerDTO.getUsername()).getRole().equals("Admin")){
                return "redirect:/admin/home";
            }
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
            return "redirect:/home?message=cập nhật thông tin thành công";
        }
        else {
            return "redirect:/customer/information?message=thông tin cập nhật không hợp lệ";

        }
    }
    @PostMapping("/customer/register")
    public String addcustomer(Model model,@ModelAttribute("customerDTO") CustomerDTO customerDTO){
        if(customerService.checkregister_update(customerDTO.getUsername(),customerDTO.getEmail(),customerDTO.getIDcard(),customerDTO.getPhone(),"register") == 0){
            Calendar calendar = Calendar.getInstance();
            String joindate = "" +(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.DATE)+"/"+(calendar.get(Calendar.YEAR))+" "+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);
            customerDTO.setJoindate(joindate);
            customerService.add(customerDTO);
            return "redirect:/home?message=đăng kí thành công";
        }
        else{
            return "redirect:/customer/register?message=thông tin đăng kí không hợp lệ";
        }
    }
    @PostMapping("/customer/change-pass")
    public String changepass(@RequestParam("oldpassword") String old,@RequestParam("newpassword") String newp){
        if(customerService.changepass(old,newp,this.customerDTO.getUsername())==0){
            return "redirect:/customer/information?message=mat khau khong chinh xac";
        }
        else {
            return "redirect:/home?message=thay đổi mật khẩu thành công";
        }
    }
    @GetMapping("/customer/logout")
    public String logout(){
        this.customerDTO = null;
        return "redirect:/home";
    }
}
