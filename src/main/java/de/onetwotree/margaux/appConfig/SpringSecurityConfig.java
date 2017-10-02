package de.onetwotree.margaux.appConfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



/**
 * Created by SebUndefined on 02/10/17.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

  @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
      httpSecurity.authorizeRequests()
              .anyRequest().authenticated()
              .and()
              .formLogin().loginPage("/login")
              .permitAll()
              .and()
              .logout()
              .permitAll();
  }
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**");
    }

  @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

      auth.inMemoryAuthentication()
              .withUser("user").password("password").roles("USER");
  }


}
