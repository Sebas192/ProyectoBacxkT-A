package com.app.web.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/index",
                        "/js/**","/css/**","/img/**","/bootstrap/**"
                ).permitAll()
                .antMatchers("/admin/**").hasAuthority("Administrador")
                .antMatchers("/empleado/**").hasAuthority("empleado")
                .antMatchers("/cliente/**").hasAuthority("cliente")
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler((req, resp, auth) -> {
                    System.out.println("User roles: " + auth.getAuthorities());
                    switch (auth.getAuthorities().iterator().next().getAuthority()) {
                        case "Administrador":
                            resp.sendRedirect("/Usuario");
                            break;
                        case "empleado":
                            resp.sendRedirect("/Calificacion");
                            break;
                        case "cliente":
                            resp.sendRedirect("/cliente/Bienvenida");
                            break;
                        default:
                            resp.sendRedirect("/login");
                            System.out.println("User does not have a valid role!");
                            break;
                    }
                })

                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

}