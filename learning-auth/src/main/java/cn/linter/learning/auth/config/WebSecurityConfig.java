package cn.linter.learning.auth.config;

import cn.linter.learning.auth.client.UserClient;
import cn.linter.learning.auth.entity.User;
import cn.linter.learning.common.entity.ResultStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Security配置
 *
 * @author wangxiaoyang
 * @since 2020/11/03
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserClient userClient;

    public WebSecurityConfig(UserClient userClient) {
        this.userClient = userClient;
    }

    @Bean
    public PasswordEncoder passwordEncoderFactory() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBeanFactory() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public UserDetailsService userDetailsServiceFactory() {
        return username -> {
            User user = userClient.queryUser(username).getData();
            if (user == null) {
                throw new UsernameNotFoundException(ResultStatus.USER_NOT_FOUND.getMessage());
            }
            return user;
        };
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorize -> authorize
                .anyRequest().permitAll())
                .csrf().disable();
    }

}