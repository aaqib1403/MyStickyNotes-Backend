package com.project.MyStickyNotes.Configurations;

import com.project.MyStickyNotes.Filters.JwtFilter;
import com.project.MyStickyNotes.Services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SpringSecurityConfiguration  extends WebSecurityConfigurerAdapter{

    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    JwtFilter jwtFilter;

//    @Autowired
//    cookiecheckfilter cookiefilter;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     auth.userDetailsService(myUserDetailsService).passwordEncoder(bcryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/mystickynotes/login","/mystickynotes/register","/mystickynotes/deletecookie").permitAll().anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
        //http.addFilterBefore(cookiefilter, JwtFilter.class);
    }

    @Bean
    public PasswordEncoder bcryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
