package com.example.eindopdracht.security;

import com.example.eindopdracht.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public SecurityConfig(JwtService service, UserRepository userRepos) {
        this.jwtService = service;
        this.userRepository = userRepos;
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService udService, PasswordEncoder passwordEncoder) throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(udService);
        return new ProviderManager(auth);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService(this.userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth

//                                .requestMatchers("/*").hasRole("ADMIN")
//                                .requestMatchers("/**").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/users/{userId}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.POST, "/accounts").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.GET, "/accounts/{accountId}").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.GET, "/accounts").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/accounts/{accountId}").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.POST, "/properties").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/properties/{propertyId}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/properties").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/properties/{propertyId}").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/roles").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.POST, "/viewings").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.GET, "/viewings").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.DELETE, "/viewings/{viewingId}").hasAnyRole("ADMIN", "USER")

                        //TODO: Toevoegen AUTH, maar vraag Mark eerst van hoe en wat
                                .requestMatchers(HttpMethod.POST, "/auth").hasRole("ADMIN")
                        
                                .requestMatchers(HttpMethod.POST, "/single/uploadDB").hasRole("ADMIN") // single upload
                                .requestMatchers(HttpMethod.GET, "/downloadFromDB/{fileName}").hasRole("ADMIN") // single download
//                                .requestMatchers(HttpMethod.POST, "/multiple/uploadDB").hasRole("ADMIN") // multiple upload
//                                .requestMatchers(HttpMethod.GET, "/downloadAllFromDB").hasRole("ADMIN") // multiple download

//                                .requestMatchers(HttpMethod.POST, "/users").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/auth").permitAll()
//                                .requestMatchers(HttpMethod.GET, "/*").permitAll()
//                                .requestMatchers(HttpMethod.DELETE, "/*").permitAll()

//                                .requestMatchers("/secret").hasRole("ADMIN")
//                                .requestMatchers("/hello").authenticated()
//                        .anyRequest().denyAll()


                                .requestMatchers("/*").permitAll()
                                .requestMatchers("/**").permitAll()
//                                .anyRequest().denyAll()
                                .anyRequest().permitAll()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
