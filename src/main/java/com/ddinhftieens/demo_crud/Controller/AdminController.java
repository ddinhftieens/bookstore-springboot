package com.ddinhftieens.demo_crud.Controller;

import com.ddinhftieens.demo_crud.Model.CartItemDTO;
import com.ddinhftieens.demo_crud.Model.OrderDTO;
import com.ddinhftieens.demo_crud.Service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.ddinhftieens.demo_crud.Model.CustomerDTO;
import com.ddinhftieens.demo_crud.Service.CustomerService;
import com.ddinhftieens.demo_crud.Service.AdminService;
import com.ddinhftieens.demo_crud.Model.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private HomeService homeService;

    @Autowired
    private AdminService adminService;


//    private final String UserName = SecurityContextHolder.getContext().getAuthentication().getName();
//    private CustomerDTO customerDTOO = customerService.getByUserName(UserName);

    private List<CustomerDTO> customerDTO;
    private List<ProductDTO> productDTOList;
    private List<OrderDTO> orderDTOList;
    private List<CartItemDTO> cartItemDTOList;
    
    @GetMapping("/customer-info")
    public String admin_cinfor(Model model,@ModelAttribute("search") String name,HttpSession session){
        final String UserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Object object = session.getAttribute("login");
        if(object==null){
            CustomerDTO customerDTOO = customerService.getByUserName(UserName);
            session.setAttribute("login",customerDTOO);
            session.setAttribute("user",customerDTOO.getFristname()+customerDTOO.getLastname());
        }
        customerDTO = new ArrayList<>();
        customerDTO = customerService.getAll();
        if(!name.isEmpty()){
            System.out.println("OK");
        }
        model.addAttribute("customerDTO",customerDTO);
        return "admin/admin-customer-info";
    }
    @GetMapping("/productmanager")
    public String adminproduct(Model model,@ModelAttribute("searchproduct") String name,@ModelAttribute("fiter") String fiter,HttpSession session){
//        if(session.getAttribute("user") == null){
//            session.setAttribute("user",customerDTOO.getFristname()+customerDTOO.getLastname());
//        }
        final String UserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Object object = session.getAttribute("login");
        if(object==null){
            CustomerDTO customerDTOO = customerService.getByUserName(UserName);
            session.setAttribute("login",customerDTOO);
            session.setAttribute("user",customerDTOO.getFristname()+customerDTOO.getLastname());
        }
        productDTOList = new ArrayList<>();
        productDTOList = adminService.getAll();
        if(!fiter.isEmpty()){
            if(fiter.equals("12")){
                productDTOList.sort(new Comparator<ProductDTO>() {
                    @Override
                    public int compare(ProductDTO o1, ProductDTO o2) {
                        return o1.getQuantity() - o2.getQuantity();
                    }
                });
            }
            else {
                productDTOList.sort(new Comparator<ProductDTO>() {
                    @Override
                    public int compare(ProductDTO o1, ProductDTO o2) {
                        return o2.getQuantity() - o1.getQuantity();
                    }
                });
            }
        }
        if(!name.isEmpty()){
            System.out.println(name);
        }
        model.addAttribute("listProduct",this.productDTOList);
        return "admin/admin-product-info";
    }
    @GetMapping("/showcustomer")
    public String showcustomer(@RequestParam("ID") int id,Model model){
        model.addAttribute("customer",customerService.getByID(id));
        return "admin/admin-detail-customer";
    }
    @GetMapping("/delete")
    public String deletecustomer(@RequestParam("ID") int id){
        customerService.delete(id);
        return "redirect:/home?message=xóa tài khoản thành công";
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
        return "redirect:/home?message=thêm sản phẩm thành công";
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
        return "redirect:/home?message=cập nhật sản phẩm thành công";
    }
    @GetMapping("/deleteproduct")
    public String deleteproduct(@RequestParam("ID") String IDcode){
        adminService.deleteproduct(IDcode);
        return "redirect:/home?message=xoa san pham thanh cong";
    }
    @GetMapping("/ordermanager")
    public String ordermanager(Model model,HttpSession session){
        final String UserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Object object = session.getAttribute("login");
        if(object==null){
            CustomerDTO customerDTOO = customerService.getByUserName(UserName);
            session.setAttribute("login",customerDTOO);
            session.setAttribute("user",customerDTOO.getFristname()+customerDTOO.getLastname());
        }
        orderDTOList = new ArrayList<>();
        orderDTOList = adminService.getAllorder();
        model.addAttribute("orderlist",orderDTOList);
        return "admin/admin-list-order";
    }
    @GetMapping("/confirmorder")
    public String confirmorder(@RequestParam("ID") String ID,Model model){
        float pricee = 0;
        int IDorder = Integer.parseInt(ID);
        OrderDTO orderDTO = adminService.getByID(IDorder);
        String[] IDcode = orderDTO.getIDcode().split(" ");
        String[] quantity = orderDTO.getQuantity().split(" ");
        String[] cost = orderDTO.getCost().split(" ");
        cartItemDTOList = new ArrayList<>();
        for(int i=0;i<IDcode.length;i++){
            CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setIDcode(IDcode[i]);
            cartItemDTO.setImage(adminService.getIDcode(IDcode[i]).getImagebase64());
            cartItemDTO.setQuantity(Integer.parseInt(quantity[i]));
            cartItemDTO.setCost(Float.parseFloat(cost[i]));
            cartItemDTO.setPrice(cartItemDTO.getQuantity()*cartItemDTO.getCost());
            pricee += cartItemDTO.getPrice();
            cartItemDTOList.add(cartItemDTO);
        }
        model.addAttribute("infor",orderDTO);
        model.addAttribute("cartItem",cartItemDTOList);
        model.addAttribute("pricee",pricee);
        return "admin/admin-order-confirm";
    }
    @PostMapping ("/confirmorder")
    public String confiorderstatus(@RequestParam("status") String status,@RequestParam("ID") String id){
        adminService.updatestatus(status,Integer.parseInt(id));
        return "redirect:/admin/ordermanager";
    }
    @GetMapping("/transaction")
    public String transaction(Model model,@RequestParam("ID") String id){
        List<OrderDTO> orderDTOList = homeService.getorderbyIDuser(Integer.parseInt(id));
        for(OrderDTO i:orderDTOList){
            float pricee = 0;
            String[] quantity = i.getQuantity().split(" ");
            String[] cost = i.getCost().split(" ");
            for(int k = 0;k<quantity.length;k++){
                pricee += (Integer.parseInt(quantity[k])*Float.parseFloat(cost[k]));
            }
            if(pricee<150000.0){
                pricee+=15000.0;
            }
            i.setPrice(pricee);
        }
        model.addAttribute("listorder",orderDTOList);
        model.addAttribute("rolee","admin");
        return "client/history-transaction";
    }
    @GetMapping("/transaction/historydetail")
    public String transactiondetail(Model model,@RequestParam("ID") String id){
        float pricee = 0;
        int IDorder = Integer.parseInt(id);
        OrderDTO orderDTO = adminService.getByID(IDorder);
        String[] IDcode = orderDTO.getIDcode().split(" ");
        String[] quantity = orderDTO.getQuantity().split(" ");
        String[] cost = orderDTO.getCost().split(" ");
        cartItemDTOList = new ArrayList<>();
        for(int i=0;i<IDcode.length;i++){
            CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setIDcode(IDcode[i]);
            cartItemDTO.setImage(adminService.getIDcode(IDcode[i]).getImagebase64());
            cartItemDTO.setQuantity(Integer.parseInt(quantity[i]));
            cartItemDTO.setCost(Float.parseFloat(cost[i]));
            cartItemDTO.setPrice(cartItemDTO.getQuantity()*cartItemDTO.getCost());
            pricee += cartItemDTO.getPrice();
            cartItemDTOList.add(cartItemDTO);
        }
        model.addAttribute("infor",orderDTO);
        model.addAttribute("cartItem",cartItemDTOList);
        model.addAttribute("pricee",pricee);
        return "client/history-transaction-detail";
    }
}
