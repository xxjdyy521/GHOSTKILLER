package com.my.ghost.admin.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.my.ghost.admin.conf.authentication.GhostAuthFailure;
import com.my.ghost.admin.conf.authentication.GhostAuthSuccess;

@Configuration
@EnableWebSecurity
public class GhostSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private GhostAuthSuccess authSuccess;

    @Autowired
    private GhostAuthFailure authFailure;


    /*注入
     * @Bean UserDetailsService userDetails(){ return new UserDetailServiceImp(); }
     */

    @Autowired
    private UserDetailsService userDetailServiceImp;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailServiceImp);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * session管理策略
         * 	ALWAYS---总是创建HttpSession
         IF_REQUIRED---Spring Security只会在需要时创建一个HttpSession
         NEVER---Spring Security不会创建HttpSession，但如果它已经存在，将可以使用HttpSession
         STATELESS---Spring Security永远不会创建HttpSession，它不会使用HttpSession来获取SecurityContext
         */
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/authority/**").hasAnyRole("USER","ADMIN")
                // .antMatchers("/user/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/user/save").permitAll()
                .antMatchers("/test/**").permitAll()
                .antMatchers("/eureka/**").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .permitAll()
                .loginPage("/authority/loginPage")
                .loginProcessingUrl("/authority/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(authSuccess)
                .failureHandler(authFailure)
                .and()
                .csrf()
                .disable();
    }
}

