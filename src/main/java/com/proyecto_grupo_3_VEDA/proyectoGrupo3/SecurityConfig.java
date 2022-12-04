package com.proyecto_grupo_3_VEDA.proyectoGrupo3;

import com.proyecto_grupo_3_VEDA.proyectoGrupo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UserService userDetailsService;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public UserService getUserService(){
        return new UserService();
    }
    
    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(getUserService());
        return daoAuthenticationProvider;
    }
    
    @Bean
    public AuthenticationSuccessHandler appAuthenticationSuccessHandler(){
        return new AppAuthenticationSuccessHandler();
    }
    
    //constructor
    public SecurityConfig(UserService userPrincipalDetailsService){
        this.userDetailsService = userPrincipalDetailsService;
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authenticationProvider());
    }
    
    String[] anyUserUrl = {"/js/**", "/css/**", "/images/**", "/home", "/signIn"};
    
    String[] adminUrl = {"/aboutUs","/addSupplier","/addUser","/contactUs","/login","/returnHome",
                    "/inventory","/products","/supplierList","/termsAndConditions","/userList","/index",
                    "/machines","/props","/productList","/manegement"};
    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .antMatchers("/home").permitAll() // This will be your home screen URL
            .antMatchers(anyUserUrl)
            .permitAll()
            .antMatchers(adminUrl)
            .hasRole("ADMIN")
            .antMatchers("/profile")
            .hasAnyRole("CUSTOMER","ADMIN")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login").permitAll().defaultSuccessUrl("/home",true)
            .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/home").permitAll();
    }
}
