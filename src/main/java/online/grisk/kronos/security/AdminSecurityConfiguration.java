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
public class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${ADMIN_USER}")
    String user;
    @Value("${ADMIN_PASS}")
    String pass;

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(user).password("{noop}".concat(pass)).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and().httpBasic().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").hasRole("ADMIN")
                .antMatchers("/info", "/health").authenticated().anyRequest()
                .denyAll().and().csrf().disable();
    }

}
