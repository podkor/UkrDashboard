package com.podkor.ukrdashboard.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http.csrf()
//            .disable()
//            .authorizeRequests()
//
////            .authorizeHttpRequests((authorize) -> authorize
////                .anyRequest().authenticated()
////            )
//            .antMatchers("/admin/**")
//            .hasRole("ADMIN")
//            .antMatchers("/anonymous*")
//            .anonymous()
//            .antMatchers("/login*")
//            .permitAll()
//            .anyRequest()
//            .authenticated()
////            .httpBasic(withDefaults())
////            .formLogin(withDefaults());
//            .and()
//                   .formLogin()
//            .loginPage("/login.html")
//            .loginProcessingUrl("/perform_login")
//            .defaultSuccessUrl("/homepage.html", true)
//            .failureUrl("/login.html?error=true")
////            .failureHandler(authenticationFailureHandler())
//            .and()
//            .logout()
//            .logoutUrl("/perform_logout")
//            .deleteCookies("JSESSIONID");
//
////            .logoutSuccessHandler(logoutSuccessHandler());
//        return http.build();

        http.userDetailsService(userDetailsService)
            .authorizeRequests()
            .antMatchers("/app/data").permitAll()
            .antMatchers("/static/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin(form -> form
                .loginPage("/app/login")
                .failureUrl("/app/login?error=true")
                .defaultSuccessUrl("/app/data")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/app/logout")
                 .logoutSuccessUrl("/app/data")


            );

        return http.build();
    }

//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http
//            .formLogin()
//            .loginPage("/login.html")
//            .failureUrl("/login-error.html")
//            .and()
//            .logout()
//            .logoutSuccessUrl("/index.html");
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

}
