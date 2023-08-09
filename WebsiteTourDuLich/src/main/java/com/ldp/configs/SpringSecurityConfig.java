/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author ACER
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement //de quan li giao tac(session)
@ComponentScan(basePackages = {
    "com.ldp.repository",
    "com.ldp.service"
}) // chi dinh 1 so bean
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationSuccessHandler dangNhapThanhCong;
    @Autowired
    private LogoutSuccessHandler dangXuatThanhCong;
    
    //phương thức băm mật khẩu
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //Cung cấp thông tin chứng thực
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
    
    //Phân quyền
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //liên kết với form
        http.formLogin().loginPage("/dangNhap")
                .usernameParameter("taikhoan")
                .passwordParameter("matkhau");
        
        //dang nhap thanh cong thi
        //dang nhap that bai thi
        http.formLogin()
                //thanh cong thi ve trang chu
                .defaultSuccessUrl("/")
                //that bai thi o yen day
                .failureUrl("/dangNhap?error");
        
        http.formLogin().successHandler(this.dangNhapThanhCong);
        
        //logout
        http.logout().logoutSuccessUrl("/dangNhap");
        http.logout().logoutSuccessHandler(this.dangXuatThanhCong);
        
        //Kiem tra co quyen truy cap khong
        http.exceptionHandling()
                .accessDeniedPage("dangNhap?accessDenied");
        //Cau hinh quyen
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/nhanVien/**").access("hasAnyRole('ROLE_MANAGE', 'ROLE_ADMIN')");
        
        http.csrf().disable();
    }
    //Nap truoc de thuc khi no quet cac cau hinh khong bi loi
    //Cau hinh de ket noi voi CLoudinary
    @Bean
    public Cloudinary cloudinary() {
        Cloudinary c = new Cloudinary(ObjectUtils.asMap(
              "cloud_name", "dp87nci6w",
              "api_key", "265565247531146",
              "api_secret", "BCbKBOA3atj_bvp5vcGQI13APqo",
              "secure", true
        ));
        return c;
    }
    
    //tao doi tuong
    @Bean
    public AuthenticationSuccessHandler dangNhapThanhCong() {
        return new XuLyDangNhapThanhCong();
    }
    
    @Bean
    public LogoutSuccessHandler dangXuatThanhCong() {
        return new XuLyDangXuat();
    }
}
