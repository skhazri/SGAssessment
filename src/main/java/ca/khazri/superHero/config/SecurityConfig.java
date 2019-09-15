package ca.khazri.superHero.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {

                auth.inMemoryAuthentication()
                        .withUser("user").password("{noop}password").roles("USER")
                        .and()
                        .withUser("admin").password("{noop}password").roles("USER", "ADMIN");

        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

                http
                        //HTTP Basic authentication
                        .httpBasic()
                        .and()
                        .authorizeRequests()
                        .antMatchers(HttpMethod.DELETE, "/api/missions/**").hasRole("USER")
                        .antMatchers(HttpMethod.DELETE, "/api/missions/**").hasRole("ADMIN")
                        .and()
                        .csrf().disable()
                        .formLogin().disable();
        }


}