package com.example.utilityserviceprovider.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity

public class webSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder getPasswordEncoder (){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/" ,"/customer-signup" , "/login" ,"/css/**" , "/js/**" , "/assets/**", "/service-signup", "/signup").permitAll()
                .antMatchers("/admin").hasAuthority("ADMIN")
                .antMatchers("/profile").hasAuthority("CUSTOMER")
                .antMatchers("/service-profile").hasAuthority("SERVICEPROVIDER")
//                .antMatchers("/style.css").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login-process")
                .defaultSuccessUrl("/")
                .failureUrl("/login")
                .and()
                .logout()
                .logoutUrl("/logout-process")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID");
    }
}