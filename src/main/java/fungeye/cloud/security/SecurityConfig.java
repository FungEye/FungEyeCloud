package fungeye.cloud.security;
/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// TODO REENABLE! + security in pom

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // TODO disable cors?? play around with this
                .authorizeHttpRequests()
                .anyRequest().anonymous() //
                .and()
                .httpBasic(); // Http instead of Https

        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.builder()
                .username("admin")
                .password("password")
                .roles("admin")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password("password")
                .roles("user")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}

 */

