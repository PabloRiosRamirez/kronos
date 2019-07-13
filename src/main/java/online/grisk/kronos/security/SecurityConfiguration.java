package online.grisk.kronos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.ws.rs.HttpMethod;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${SECRET_USER}")
    String user;
    @Value("${SECRET_PASS}")
    String pass;

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(user).password("{noop}".concat(pass)).roles("SYSTEM");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and().requestMatchers().antMatchers("/eureka/**")
                .and().authorizeRequests()
                .antMatchers("/eureka/**").authenticated()
                .antMatchers(HttpMethod.GET, "/").authenticated()
                .antMatchers("/info", "/health").authenticated()
                .anyRequest().denyAll()
                .and().httpBasic()
                .and().csrf().disable();
    }
}
