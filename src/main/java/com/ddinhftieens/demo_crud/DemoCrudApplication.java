package com.ddinhftieens.demo_crud;
import com.ddinhftieens.demo_crud.Security.UrlAuthenSuccessHandler;
import com.ddinhftieens.demo_crud.Security.WebSecurityConfigurer;
import com.ddinhftieens.demo_crud.Service.AdminService;
import com.ddinhftieens.demo_crud.Model.ProductDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class DemoCrudApplication /*extends WebSecurityConfigurerAdapter*/{

    public static void main(String[] args) {
        SpringApplication.run(DemoCrudApplication.class, args);
    }

//    @Autowired
//    private UserDetailsService userDetailsService;

//    @Autowired
//    private UrlAuthenSuccessHandler urlAuthenSuccessHandler;

    @Autowired
    private AdminService adminService;

    @Bean(name = "demo")
    public String getString(){
        return "demoBean";
    }

    @Bean(name = "listproduct")
    public List<ProductDTO> productDTOList(){
        return adminService.getAll();
    }

    @Bean(name = "listproduct1")
    public List<ProductDTO> productDTOList1(){
        return adminService.getAlltype(1);
    }

    @Bean(name = "listproduct2")
    public List<ProductDTO> productDTOList2(){
        return adminService.getAlltype(2);
    }

    @Bean(name = "listproduct3")
    public List<ProductDTO> productDTOList3(){
        return adminService.getAlltype(3);
    }

    @Bean(name = "listproduct4")
    public List<ProductDTO> productDTOList4(){
        return adminService.getAlltype(4);
    }

    @Bean(name = "listproduct5")
    public List<ProductDTO> productDTOList5(){
        return adminService.getAlltype(5);
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.csrf().disable();
//
//        // Các trang không yêu cầu login
//        http.authorizeRequests().antMatchers("/home", "/customer/login", "/customer/register","/home/product","/home/catalog","/home/cart","/home/cart/delete").permitAll();
//
//        // Trang /userInfo yêu cầu phải login với vai trò ROLE_USER hoặc ROLE_ADMIN.
//        // Nếu chưa login, nó sẽ redirect tới trang /login.
//        http.authorizeRequests().antMatchers("/customer/information","/customer/logout","/customer/history-transaction","/customer/transaction/historydetail","/home/order").access("hasAnyRole('Admin', 'Customer')");
//
//        // Trang chỉ dành cho ADMIN
//        http.authorizeRequests().antMatchers("/admin/**").access("hasAnyRole('Admin')");
//
//        // Khi người dùng đã login, với vai trò XX.
//        // Nhưng truy cập vào trang yêu cầu vai trò YY,
//        // Ngoại lệ AccessDeniedException sẽ ném ra.
//        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
//
//        // Cấu hình cho Login Form.
//        http.authorizeRequests().and().formLogin()//
//                // Submit URL của trang login
//                .loginPage("/customer/login")//
//                .loginProcessingUrl("/customer/login")//
//                .defaultSuccessUrl("/home")//
//                .failureUrl("/customer/login?error=true")//.successHandler(urlAuthenSuccessHandler)//
//                .usernameParameter("username")//
//                .passwordParameter("password")
//                // Cấu hình cho Logout Page.
//                .and().logout().logoutUrl("/customer/logout").logoutSuccessUrl("/home")
//                .deleteCookies("JSESSIONID").invalidateHttpSession(true);
//    }
}
