package org.t01.gdp.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.t01.gdp.security.handler.*;
import org.t01.gdp.security.detailservice.AdministratorDetailService;
import org.t01.gdp.security.detailservice.StudentDetailService;
import org.t01.gdp.security.detailservice.TeacherDetailService;

@EnableWebSecurity
public class WebSecurityConfig {

    @Configuration
    @Order(1)
    public static class StudentWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private StudentDetailService studentDetailService;
        @Autowired
        private StudentLoginSuccessHandler studentLoginSuccessHandler;
        @Autowired
        private StudentLogoutSuccessHandler studentLogoutSuccessHandler;
        @Autowired
        private StudentLoginFailureHandler studentLoginFailureHandler;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            String pattern = "/student/**";

            http
                    .authorizeRequests()
                    .antMatchers(pattern).access("hasRole('ROLE_STU')")
                    .and()
                    .antMatcher(pattern)
                    .formLogin()
                    .loginPage("/student/login")
                    .loginProcessingUrl("/student/login/check")
                    .successHandler(studentLoginSuccessHandler)
                    .failureHandler(studentLoginFailureHandler)
                    .permitAll()
                    .and()
                    .antMatcher(pattern)
                    .logout()
                    .logoutUrl("/student/logout")
                    .logoutSuccessHandler(studentLogoutSuccessHandler)
                    .and()
                    .logout()
                    .permitAll()
                    .and()
                    .csrf().disable();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(studentDetailService).passwordEncoder(new PasswordEncoder() {
                @Override
                public String encode(CharSequence charSequence) {
                    return charSequence.toString();
                }

                @Override
                public boolean matches(CharSequence charSequence, String s) {
                    return s.equals(charSequence.toString());
                }
            });
        }
    }

    @Configuration
    @Order(2)
    public static class TeacherWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private TeacherDetailService teacherDetailService;
        @Autowired
        private TeacherLoginSuccessHandler teacherLoginSuccessHandler;
        @Autowired
        private TeacherLogoutSuccessHandler teacherLogoutSuccessHandler;
        @Autowired
        private TeacherLoginFailureHandler teacherLoginFailureHandler;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            String pattern = "/teacher/**";

            http
                    .authorizeRequests()
                    .antMatchers(pattern).access("hasRole('ROLE_TEA')")
                    .anyRequest().authenticated()
                    .and()
                    .antMatcher(pattern)
                    .formLogin()
                    .loginPage("/teacher/login")
                    .loginProcessingUrl("/teacher/login/check")
                    .successHandler(teacherLoginSuccessHandler)
                    .failureHandler(teacherLoginFailureHandler)
                    .permitAll()
                    .and()
                    .antMatcher(pattern)
                    .logout()
                    .logoutUrl("/teacher/logout")
                    .logoutSuccessHandler(teacherLogoutSuccessHandler)
                    .and()
                    .logout()
                    .permitAll()
                    .and()
                    .csrf().disable();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(teacherDetailService).passwordEncoder(new PasswordEncoder() {
                @Override
                public String encode(CharSequence charSequence) {
                    return charSequence.toString();
                }

                @Override
                public boolean matches(CharSequence charSequence, String s) {
                    return s.equals(charSequence.toString());
                }
            });
        }
    }

    @Configuration
    @Order(3)
    public static class AdministratorWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private AdministratorDetailService administratorDetailService;
        @Autowired
        private AdministratorLoginSuccessHandler administratorLoginSuccessHandler;
        @Autowired
        private AdministratorLogoutSuccessHandler administratorLogoutSuccessHandler;
        @Autowired
        private AdministratorLoginFailureHandler administratorLoginFailureHandler;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            String pattern = "/administrator/**";

            http
                    .authorizeRequests()
                    .antMatchers(pattern).access("hasRole('ROLE_ADM')")
                    .anyRequest().authenticated()
                    .and()
                    .antMatcher(pattern)
                    .formLogin()
                    .loginPage("/administrator/login")
                    .loginProcessingUrl("/administrator/login/check")
                    .successHandler(administratorLoginSuccessHandler)
                    .failureHandler(administratorLoginFailureHandler)
                    .permitAll()
                    .and()
                    .antMatcher(pattern)
                    .logout()
                    .logoutUrl("/administrator/logout")
                    .logoutSuccessHandler(administratorLogoutSuccessHandler)
                    .and()
                    .logout()
                    .permitAll()
                    .and()
                    .csrf().disable();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(administratorDetailService).passwordEncoder(new PasswordEncoder() {
                @Override
                public String encode(CharSequence charSequence) {
                    return charSequence.toString();
                }

                @Override
                public boolean matches(CharSequence charSequence, String s) {
                    return s.equals(charSequence.toString());
                }
            });
        }
    }

    @Configuration
    @Order(4)
    public static class OtherWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter{
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/", "/index","/Rendering/**","/forgotPassword","/retrievePassword","/sendVerifyCode").permitAll()
                    .and()
                    .csrf().disable();
        }
    }
}