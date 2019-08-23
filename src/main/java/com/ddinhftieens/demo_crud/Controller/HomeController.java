package com.ddinhftieens.demo_crud.Controller;

import com.ddinhftieens.demo_crud.Model.ProductDTO;
import com.ddinhftieens.demo_crud.Service.AdminService;
import com.ddinhftieens.demo_crud.Service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import com.ddinhftieens.demo_crud.Model.CartItemDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private HomeService homeService;

    private ProductDTO productDTO;
    private float price = 0;

    @GetMapping("/product")
    public String product(@RequestParam("ID") String IDcode, Model model){
        productDTO = adminService.getIDcode(IDcode);
        model.addAttribute("product",this.productDTO);
        return "client/product";
    }
    @PostMapping("/product")
    public String addproduct(@RequestParam("ID") String IDcode, @RequestParam("query") String query, HttpSession session,@RequestParam("quantity") int quantity){
        Object object = session.getAttribute("cart");
        CartItemDTO cartItemDTO = new CartItemDTO(this.productDTO.getIDcode(),this.productDTO.getImagebase64(),quantity,this.productDTO.getPrice(),quantity*this.productDTO.getPrice());
        if(object == null){
            Map<String,CartItemDTO> cartItemDTOMap = new HashMap<>();
            cartItemDTOMap.put(IDcode,cartItemDTO);
            session.setAttribute("cart",cartItemDTOMap);
        }
        else {
            Map<String,CartItemDTO> cartItemDTOMap = (Map<String, CartItemDTO>) object;
            cartItemDTOMap.put(IDcode,cartItemDTO);
            session.setAttribute("cart",cartItemDTOMap);
        }
        price += cartItemDTO.getPrice();
        return "redirect:/home/catalog?query="+query;
    }

    @GetMapping("/catalog")
    public String catalog(Model model,@RequestParam("query") String query){
        model.addAttribute("productList",homeService.getbyType(query));
        model.addAttribute("title",query);
        return "client/catalog";
    }
    @GetMapping("/cart")
    public String cart(HttpSession session,Model model){
        Object object = session.getAttribute("cart");
        if(object!=null){
            Map<String,CartItemDTO> cartItemDTOMap = (Map<String, CartItemDTO>) object;
            model.addAttribute("cartItem",cartItemDTOMap);
            model.addAttribute("price",price);
        }
        return "client/list-order";
    }
    @GetMapping("/cart/delete")
    public String deleteorder(@RequestParam("Idcode") String Idcode,HttpSession session,HttpServletRequest httpServletRequest){
        Object object = session.getAttribute("cart");
        Map<String,CartItemDTO> cartItemDTOMap = (Map<String, CartItemDTO>) object;
        price -= cartItemDTOMap.get(Idcode).getPrice();
        cartItemDTOMap.remove(Idcode);
        session.setAttribute("cart",cartItemDTOMap);
        return "redirect:/home/cart?message=bo san pham thanh cong";
    }
}
