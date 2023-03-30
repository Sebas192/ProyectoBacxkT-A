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
                .antMatchers("/login", "/index", "/js/**","/css/**","/img/**","/bootstrap/**").permitAll()
                .antMatchers("/admin/**", "/Usuario","/Cargo","/Cita","/Contrato","/Resultado",
                        "/Vacante","/CosulHojaVida","/Pqrs","/Venta"
                ).hasRole("ADMIN")
                .antMatchers("/empleado/**").hasRole("EMPLEADO")
                .antMatchers("/cliente/**","/cliente/Bienvenida","/cliente/Calificacion","/cliente/Cotizacion","/cliente/HojaDeVida",
                        "/cliente/Pqrs","/cliente/Venta","/cliente/GenerarCalificacion","/cliente/GenerarCotizacion","/cliente/GenerarHojaDeVida","/cliente/GenerarPQRS"

                ).hasRole("CLIENTE")
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler((req, resp, auth) -> {
                    System.out.println("User roles: " + auth.getAuthorities());
                    switch (auth.getAuthorities().iterator().next().getAuthority()) {
                        case "ROLE_ADMIN":
                            resp.sendRedirect("/Usuario");
                            break;
                        case "ROLE_EMPLEADO":
                            resp.sendRedirect("/empleado/index");
                            break;
                        case "ROLE_CLIENTE":
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

