package security;

import com.collab.g5.demo.users.UserDetailsSecurity;
import com.collab.g5.demo.users.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableWebSecurity
@Configuration
@EnableTransactionManagement
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                //antMatchers does some simple whitelisting and the order matters
                .antMatchers(HttpMethod.GET, "/user/*","/user/**").hasAnyRole("HR")
                .antMatchers(HttpMethod.POST, "/user/**").hasAnyRole("HR")
                .antMatchers(HttpMethod.PUT, "/user/*").hasAnyRole("HR")
                .antMatchers(HttpMethod.DELETE, "/user/**").hasAnyRole("HR")
                .anyRequest()
                .authenticated()
                .and()
                //csrf : cross site request forgery
                // action of forging a copy or imitation of a document, signature, banknote / art
                // only use it when it could be processed by a brows  er by normal users
                .csrf().disable()
                .headers().disable()
                .formLogin().disable();
                //.loginPage("/login").permitAll()
                //.defaultSuccessUrl(("/dashboard"),true)
                //.and()
                //.rememberMe() //defaults 2 weeks   ;
    }


//    @Bean
//    public BCryptPasswordEncoder encoder() {
//        return new BCryptPasswordEncoder(10);
//    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(encoder());
        provider.setUserDetailsService(userDetailsServiceImpl);

        return provider;
    }


}
