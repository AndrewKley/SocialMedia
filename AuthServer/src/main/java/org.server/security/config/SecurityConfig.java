package org.server.security.config;

import org.server.models.User;
import org.server.repositories.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(requests ->
                        requests.anyRequest().authenticated())
                .formLogin()
                .and().build();
    }

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository) {
        return login -> userRepository.findByLoginUserDetails(login);
    }

    @Bean
    public ApplicationRunner applicationRunner(
            UserRepository repository, PasswordEncoder encoder) {
        return  args -> {
            repository.save(
                    new User("Habuma", encoder.encode("password"), "ROLE_ADMIN"));
            repository.save(
                    new User("Gilf", encoder.encode("12345678"), "ROLE_ADMIN"));
        };
    }
}
