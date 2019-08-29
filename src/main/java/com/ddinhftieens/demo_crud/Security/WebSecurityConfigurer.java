package com.ddinhftieens.demo_crud.Security;

import com.ddinhftieens.demo_crud.Service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration // Nó được chú thích (annotate) bởi @Configuration, Annotation này nói với Spring rằng nó là một lớp cấu hình, và vì vậy nó sẽ được Spring phân tích tại thời điểm ứng dụng được chạy.
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    @Autowired
    private AdminServiceImpl userDetailsService;

    @Autowired
    private UrlAuthenSuccessHandler urlAuthenSuccessHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Sét đặt dịch vụ để tìm kiếm User trong Database.
        // Và sét đặt PasswordEncoder.
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        // Các trang không yêu cầu login
        http.authorizeRequests().antMatchers("/home", "/customer/login", "/customer/register","/home/product","/home/catalog","/home/cart","/home/cart/delete").permitAll();

        // Trang /userInfo yêu cầu phải login với vai trò ROLE_USER hoặc ROLE_ADMIN.
        // Nếu chưa login, nó sẽ redirect tới trang /login.
        http.authorizeRequests().antMatchers("/customer/information","/customer/logout","/customer/history-transaction","/customer/transaction/historydetail","/home/order").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')");

        // Trang chỉ dành cho ADMIN
        http.authorizeRequests().antMatchers("/admin/**").access("hasAnyRole('ROLE_ADMIN')");

        // Khi người dùng đã login, với vai trò XX.
        // Nhưng truy cập vào trang yêu cầu vai trò YY,
        // Ngoại lệ AccessDeniedException sẽ ném ra.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // Cấu hình cho Login Form.
        http.authorizeRequests().and().formLogin()//
                // Submit URL của trang login
                .loginPage("/customer/login")//
                .loginProcessingUrl("/customer/login")//
                .defaultSuccessUrl("/home")//
                .failureUrl("/customer/login?error=true")//.successHandler(urlAuthenSuccessHandler)//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Cấu hình cho Logout Page.
                .and().logout().logoutUrl("/customer/logout").logoutSuccessUrl("/home")
                .deleteCookies("JSESSIONID").invalidateHttpSession(true);
    }
}
