package com.ddinhftieens.demo_crud.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.ddinhftieens.demo_crud.Model.CustomerDTO;
import com.ddinhftieens.demo_crud.Service.CustomerService;
import com.ddinhftieens.demo_crud.Service.AdminService;
import com.ddinhftieens.demo_crud.Model.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private AdminService adminService;

    @Autowired
    @Qualifier("demo")
    private String bean;

    private List<CustomerDTO> customerDTO;
    private List<ProductDTO> productDTOList;

    @GetMapping("/home")
    public String adminhome(){
        customerDTO = new ArrayList<>();
        customerDTO = customerService.getAll();
        productDTOList = new ArrayList<>();
        productDTOList = adminService.getAll();
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
    @GetMapping("/productmanager")
    public String adminproduct(Model model,@ModelAttribute("searchproduct") String name){
        if(!name.isEmpty()){
            System.out.println(name);
        }
        model.addAttribute("listProduct",this.productDTOList);
        return "admin/admin-product-info";
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
    public String showcustomer(@RequestParam("ID") int id,Model model){
        model.addAttribute("customer",customerService.getByID(id));
        return "admin/admin-detail-customer";
    }
    @GetMapping("/delete")
    public String deletecustomer(@RequestParam("ID") int id){
        customerService.delete(id);
        return "redirect:/admin/home?message=xóa tài khoản thành công";
    }
    @GetMapping("/add-product")
    public String addproduct(Model model){
        model.addAttribute("product",new ProductDTO());
        return "admin/admin-add-product";
    }
    @PostMapping("/post-product")
    public String pustproduct(@ModelAttribute("product") ProductDTO productDTO, @RequestParam("imagee") MultipartFile multipartFile) throws IOException {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        String joindate = "" +(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.DATE)+"/"+(calendar.get(Calendar.YEAR))+" "+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);
        productDTO.setDatecreated(joindate);
        productDTO.setIDcode(""+date.getTime());
        byte[] images = multipartFile.getBytes();
        productDTO.setImage(images);
        adminService.addproduct(productDTO);
        return "redirect:/admin/home?message=thêm sản phẩm thành công";
    }
    @GetMapping("/editproduct")
    public String showproduct(@RequestParam("ID") String Idcode,Model model){
        model.addAttribute("product",adminService.getIDcode(Idcode));
        return "admin/admin-edit-product";
    }
    @PostMapping("/editproduct")
    public String editproduct(@ModelAttribute("product") ProductDTO productDTO,@RequestParam("ID") String IDcode){
        productDTO.setIDcode(IDcode);
        adminService.updateproduct(productDTO);
        return "redirect:/admin/home?message=cập nhật sản phẩm thành công";
    }
    @GetMapping("/deleteproduct")
    public String deleteproduct(@RequestParam("ID") String IDcode){
        adminService.deleteproduct(IDcode);
        return "redirect:/admin/home?message=xoa san pham thanh cong";
    }
}
