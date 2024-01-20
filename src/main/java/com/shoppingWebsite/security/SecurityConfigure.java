package com.shoppingWebsite.security;

import com.shoppingWebsite.security.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()
                .authorizeRequests().antMatchers("/api/public/authenticate").permitAll()

                .antMatchers("/api/public/user/create").permitAll()
                .antMatchers(("/api/public/user/update")).permitAll()
                .antMatchers(("/api/public/user/delete/{id}")).permitAll()
                .antMatchers(("/api/public/user/{id}")).permitAll()

                .antMatchers(("/api/public/item/create")).permitAll()
                .antMatchers(("/api/public/item/{id}")).permitAll()
                .antMatchers(("/api/public/item/getAllItems")).permitAll()
                .antMatchers(("/api/public/item/searchItemsByLetter")).permitAll()

                .antMatchers(("/api/public/user/addItemToFavoriteList/{itemId}/{userId}")).permitAll()
                .antMatchers(("/api/public/user/removeItemFromFavoriteList/{itemId}/{userId}")).permitAll()
                .antMatchers(("/api/public/user/getUserFavoriteList/{userId}")).permitAll()


                .antMatchers(("/api/public/order/create")).permitAll()
                .antMatchers(("/api/public/order/{orderId}")).permitAll()
                .antMatchers(("/api/public/user/getOrderItemListByOrderId/{orderId}")).permitAll()
                .antMatchers(("/api/public/order/submitOrder/{userId}/{orderId}")).permitAll()

                .antMatchers(("/api/public/user/addItemToUserOrder/{itemId}/{userId}")).permitAll()
                .antMatchers(("/api/public/user//removeItemFromUserOrder/{orderId}/{itemId}/{userId}")).permitAll()
                .antMatchers(("/api/public/user/getAllOrdersByUserId/{userId}")).permitAll()
                .antMatchers(("/api/public/user/getClosedOrdersByUserId/{userId}")).permitAll()
                .antMatchers(("/api/public/user//getTempOrdersByUserId/{userId}")).permitAll()


                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}


