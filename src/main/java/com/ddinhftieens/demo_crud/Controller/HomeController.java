package com.ddinhftieens.demo_crud.Controller;

import com.ddinhftieens.demo_crud.Model.CustomerDTO;
import com.ddinhftieens.demo_crud.Model.OrderDTO;
import com.ddinhftieens.demo_crud.Model.ProductDTO;
import com.ddinhftieens.demo_crud.Service.AdminService;
import com.ddinhftieens.demo_crud.Service.CustomerService;
import com.ddinhftieens.demo_crud.Service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import com.ddinhftieens.demo_crud.Model.CartItemDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private HomeService homeService;

    private ProductDTO productDTO;
    private float price;
    private String timee;

    @GetMapping("/product")//error
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
        return "redirect:/home/catalog?query="+query;
    }

    @GetMapping("/catalog")
    public String catalog(Model model,@RequestParam("query") String query){
        if(query.equals("100")){
            model.addAttribute("productList",adminService.getAll());
        }
        else {
            model.addAttribute("productList",homeService.getbyType(query));

        }
        model.addAttribute("title",query);
        return "client/catalog";
    }

    @GetMapping("/cart")
    public String cart(HttpSession session,Model model){
        price = 0;
        Object object = session.getAttribute("cart");
        if(object!=null){
            Map<String,CartItemDTO> cartItemDTOMap = (Map<String, CartItemDTO>) object;
            for(Map.Entry<String,CartItemDTO> itemDTOEntry:cartItemDTOMap.entrySet()){
                price+=itemDTOEntry.getValue().getPrice();
            }
            model.addAttribute("cartItem",cartItemDTOMap);
            model.addAttribute("price",price);
        }
        return "client/list-order";
    }

    @GetMapping("/cart/delete")
    public String deleteorder(@RequestParam("Idcode") String Idcode,HttpSession session,HttpServletRequest httpServletRequest){
        Object object = session.getAttribute("cart");
        Map<String,CartItemDTO> cartItemDTOMap = (Map<String, CartItemDTO>) object;
        cartItemDTOMap.remove(Idcode);
        session.setAttribute("cart",cartItemDTOMap);
        return "redirect:/home/cart?message=bo san pham thanh cong";
    }

    @GetMapping("/order")
    public String order(Model model,HttpSession session){

        if(session.getAttribute("login")==null){
            final String UserName = SecurityContextHolder.getContext().getAuthentication().getName();
            CustomerDTO customerDTOO = customerService.getByUserName(UserName);
            session.setAttribute("login",customerDTOO);
            session.setAttribute("user",customerDTOO.getFristname()+customerDTOO.getLastname());
        }
        Object object = session.getAttribute("cart");
        if(object!=null){
            Map<String,CartItemDTO> cartItemDTOMap = (Map<String, CartItemDTO>) object;
            model.addAttribute("cartItem",cartItemDTOMap);
            model.addAttribute("price",price);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE,3);
            timee = calendar.get(Calendar.YEAR)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.DATE);
            model.addAttribute("time",timee);
            return "client/order";
        }
        else {
            return "redirect:/home";
        }
    }

    @PostMapping("/order")
    public String confirm(@RequestParam("note") String note,@RequestParam("name") String name,HttpSession session,@RequestParam("phone") String phone,@RequestParam("apartmentnumber") String apartmentnumber,@RequestParam("neighbors") String neighbors,@RequestParam("village") String village,@RequestParam("town") String town,@RequestParam("township") String township,@RequestParam("city") String city){
        Object object = session.getAttribute("login");
        OrderDTO orderDTO = new OrderDTO();
        Object objectCart = session.getAttribute("cart");
        Map<String,CartItemDTO> cartItemDTOMap = (Map<String, CartItemDTO>) objectCart;
        String IDcode="";
        String quantity ="", cost="";
        for(Map.Entry<String,CartItemDTO> i:cartItemDTOMap.entrySet()){
            homeService.updatequantity(i.getKey(),(0-i.getValue().getQuantity()));
            IDcode += (i.getKey()+" ");
            quantity +=(i.getValue().getQuantity() + " ");
            cost += (i.getValue().getCost() + " ");
        }
        orderDTO.setIDcode(IDcode);
        orderDTO.setQuantity(quantity);
        orderDTO.setCost(cost);
        String address = "Số nhà " + apartmentnumber +" Ngõ "+ neighbors + " | " +village + " " +town + " " +township + " " +city;
        orderDTO.setName(name);
        orderDTO.setAddress(address);
        orderDTO.setPhone(phone);
        Calendar calendar = Calendar.getInstance();
        String datecreated = "" +(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.DATE)+"/"+(calendar.get(Calendar.YEAR))+" "+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);
        orderDTO.setDatecreated(datecreated);
        CustomerDTO customerDTO = (CustomerDTO) object;
        orderDTO.setIDuser(customerDTO.getID());
        orderDTO.setNote(note);
        orderDTO.setTime(timee);
        orderDTO.setStatus("New");
        homeService.orderr(orderDTO);
        session.setAttribute("cart",null);
        return "redirect:/home?message=dat hang thanh cong";
    }
}
