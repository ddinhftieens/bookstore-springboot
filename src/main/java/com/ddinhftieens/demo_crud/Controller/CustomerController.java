package com.ddinhftieens.demo_crud.Controller;

import com.ddinhftieens.demo_crud.Model.CartItemDTO;
import com.ddinhftieens.demo_crud.Model.OrderDTO;
import com.ddinhftieens.demo_crud.Service.AdminService;
import com.ddinhftieens.demo_crud.Service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ddinhftieens.demo_crud.Model.CustomerDTO;
import com.ddinhftieens.demo_crud.Service.CustomerService;
import com.ddinhftieens.demo_crud.Model.ProductDTO;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class CustomerController{
    @Value("${logging.message}")
    private String message;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private HomeService homeService;

    @Autowired
    private AdminService adminService;

    private List<CartItemDTO> cartItemDTOList;

    @Autowired
    @Qualifier("listproduct")
    private List<ProductDTO> productDTOList;
    @Autowired
    @Qualifier("listproduct1")
    private List<ProductDTO> productDTOList1;
    @Autowired
    @Qualifier("listproduct2")
    private List<ProductDTO> productDTOList2;
    @Autowired
    @Qualifier("listproduct3")
    private List<ProductDTO> productDTOList3;
    @Autowired
    @Qualifier("listproduct4")
    private List<ProductDTO> productDTOList4;
    @Autowired
    @Qualifier("listproduct5")
    private List<ProductDTO> productDTOList5;

    private CustomerDTO customerDTO = null;

    @GetMapping("/customer/login")
    public String login(Model model){
        model.addAttribute("customerDTO",new CustomerDTO());
        return "client/login";
    }

//    @GetMapping("/NDTBookStore")
//    public String NDTBookStore(HttpSession session){
//        final String UserName = SecurityContextHolder.getContext().getAuthentication().getName();
//        this.customerDTO = customerService.getByUserName(UserName);
//        session.setAttribute("login",this.customerDTO);
//        return "redirect:/home";
//    }

    @GetMapping("/home")
    public String home(Model model,HttpSession session){
        model.addAttribute("productList",this.productDTOList);
        model.addAttribute("productList1",this.productDTOList1);
        model.addAttribute("productList2",this.productDTOList2);
        model.addAttribute("productList3",this.productDTOList3);
        model.addAttribute("productList4",this.productDTOList4);
        model.addAttribute("productList5",this.productDTOList5);
        final String UserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Object object = session.getAttribute("login");
        if(UserName.equals("anonymousUser")){
            session.setAttribute("user","customer");
            return "home";
        }
        else{
            if(object==null){
                this.customerDTO = customerService.getByUserName(UserName);
                session.setAttribute("login",this.customerDTO);
                session.setAttribute("user",this.customerDTO.getFristname()+this.customerDTO.getLastname());
            }
//            model.addAttribute("user",this.customerDTO.getFristname()+this.customerDTO.getLastname());
            return "home";
        }
    }

    @GetMapping("/customer/register")
    public String register(Model model){
        model.addAttribute("customerDTO",new CustomerDTO());
        return "client/register";
    }

    @GetMapping("/customer/information")
    public String customerinformation(Model model,HttpSession session){
        final String UserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(session.getAttribute("login")==null){
            CustomerDTO customerDTOO = customerService.getByUserName(UserName);
            session.setAttribute("login",customerDTOO);
            session.setAttribute("user",customerDTOO.getFristname()+customerDTOO.getLastname());
        }
        model.addAttribute("customerDTO",(CustomerDTO)session.getAttribute("login"));
        return "client/customer-info";
    }

    @PostMapping("/customer/update-information")//error
    public String updateInformation(@ModelAttribute("customerDTO") CustomerDTO customerDTO,HttpSession session){
        if(customerService.checkregister_update(customerDTO.getUsername(),customerDTO.getEmail(),customerDTO.getIDcard(),customerDTO.getPhone(),this.customerDTO.getUsername())==0){
            this.customerDTO.setAddress(customerDTO.getAddress());
            this.customerDTO.setEmail(customerDTO.getEmail());
            this.customerDTO.setPhone(customerDTO.getPhone());
            session.setAttribute("login",this.customerDTO);
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
            customerDTO.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
            customerService.add(customerDTO);
            return "redirect:/home?message=đăng kí thành công";
        }
        else{
            return "redirect:/customer/register?message=thông tin đăng kí không hợp lệ";
        }
    }

    @PostMapping("/customer/change-pass")//error
    public String changepass(@RequestParam("oldpassword") String old,@RequestParam("newpassword") String newp){
        if(customerService.changepass(passwordEncoder.encode(old),passwordEncoder.encode(newp),this.customerDTO.getUsername())==0){
            return "redirect:/customer/information?message=mat khau khong chinh xac";
        }
        else {
            return "redirect:/home?message=thay đổi mật khẩu thành công";
        }
    }
    @GetMapping("/customer/logout")
    public String logout(HttpSession session){
        this.customerDTO = null;
        session.invalidate();
        return "redirect:/home";
    }


    @GetMapping("/customer/history-transaction")//error
    public String historytransaction(Model model,HttpSession session){
        if(session.getAttribute("login") == null){
            final String UserName = SecurityContextHolder.getContext().getAuthentication().getName();
            CustomerDTO customerDTOO = (CustomerDTO) customerService.getByUserName(UserName);
            session.setAttribute("login",customerDTOO);
            session.setAttribute("user",customerDTOO.getFristname()+customerDTOO.getLastname());
        }
        Object login = session.getAttribute("login");
//        if(login!=null){
            CustomerDTO customerDTO = (CustomerDTO) login;
            List<OrderDTO> orderDTOList = homeService.getorderbyIDuser(customerDTO.getID());
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
            model.addAttribute("rolee","customer");
            return "client/history-transaction";
//        }
//        else {
//            return "redirect:/customer/login";
//        }
    }


    @GetMapping("/customer/transaction/historydetail")
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
