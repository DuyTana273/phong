package com.phong.demo.config.security;

import com.phong.demo.common.CustomAccessDeniedHandler;
import com.phong.demo.common.CustomAuthenticationEntryPoint;
import com.phong.demo.common.CustomAuthenticationFailureHandler;
import com.phong.demo.common.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        // Chuỗi mã hóa mật khẩu
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(
                                "/clear-session",
                                "/contact/submit")
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login",
                                "/css/**",
                                "/img/**",
                                "/js/**",
                                "/favicon.ico",
                                "/clear-session",
                                //Homepage
                                "/",
                                "/home",
                                "/about",
                                "/company",
                                "/contact/**",
                                "/service",
                                "/price",
                                "/area",
                                "/qa",
                                "/terms",
                                "/nondisclosure").permitAll()
                        .requestMatchers("/dashboard/admin").hasAnyRole("Admin")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginProcessingUrl("/perform_login")
                        .successHandler(customAuthenticationSuccessHandler)
                        .failureHandler(customAuthenticationFailureHandler)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessHandler((request, response, authentication) -> {
                            request.getSession().setAttribute("SUCCESS_MESSAGE", "Đăng xuất thành công!");
                            response.sendRedirect("/login");
                        })
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll()
                )
                .sessionManagement(session -> session
                        .invalidSessionUrl("/login")
                        .maximumSessions(1)
                        .expiredUrl("/login")
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                )
                // Tắt hoàn toàn RequestCache phuc vụ việc redirect về trang cố ý truy cập trước khi đăng nhập
                .requestCache(RequestCacheConfigurer::disable
                );

        return http.build();
    }

}