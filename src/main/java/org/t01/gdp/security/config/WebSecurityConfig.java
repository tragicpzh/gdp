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

        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/student/**").access("hasRole('ROLE_STU')")
                    .and()
                    .antMatcher("/student/**")
                    .formLogin()
                    .loginPage("/student/login")
                    .loginProcessingUrl("/student/login/check")
                    .successHandler(studentLoginSuccessHandler)
                    .failureUrl("/student/login?failure=true")
                    .permitAll()
                    .and()
                    .antMatcher("/student/**")
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

        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/teacher/**").access("hasRole('ROLE_TEA')")
                    .anyRequest().authenticated()
                    .and()
                    .antMatcher("/teacher/**")
                    .formLogin()
                    .loginPage("/teacher/login")
                    .loginProcessingUrl("/teacher/login/check")
                    .successHandler(teacherLoginSuccessHandler)
                    .failureUrl("/teacher/login?failure=true")
                    .permitAll()
                    .and()
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

        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/administrator/**").access("hasRole('ROLE_ADM')")
                    .anyRequest().authenticated()
                    .and()
                    .antMatcher("/administrator/**")
                    .formLogin()
                    .loginPage("/administrator/login")
                    .loginProcessingUrl("/administrator/login/check")
                    .successHandler(administratorLoginSuccessHandler)
                    .failureUrl("/administrator/login?failure=true")
                    .permitAll()
                    .and()
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

//    @Configuration
//    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .authorizeRequests()
//                    .anyRequest().authenticated()
//                    .and()
//                    .formLogin();
//        }
//    }

//    @Autowired
//    private MyUserDetailService myUserDetailService;
//    @Autowired
//    private LoginSuccessHandler loginSuccessHandler;
//    @Autowired
//    private MyLogoutSuccessHandler myLogoutSuccessHandler;
//
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/index","/Rendering/**","/student/login").permitAll()
//                .antMatchers("/administrator/**").access("hasRole('ROLE_ADM')")
//                .antMatchers("/teacher/**").access("hasRole('ROLE_TEA')")
//                .antMatchers("/student/**").access("hasRole('ROLE_STU')")
//                .anyRequest().authenticated()
//                .and()
//                .antMatcher("/**")
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/login/check")
//                .successHandler(loginSuccessHandler)
//                .failureUrl("/login?failure=true")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessHandler(myLogoutSuccessHandler)
//                .and()
//                .logout()
//                .permitAll()
//                .and()
//                .csrf().disable()
//                .headers().frameOptions().disable();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myUserDetailService).passwordEncoder(new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence charSequence) {
//                return charSequence.toString();
//            }
//
//            @Override
//            public boolean matches(CharSequence charSequence, String s) {
//                return s.equals(charSequence.toString());
//            }
//        });
//    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("TEA")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
}