package com.example.eindopdracht.security;

import com.example.eindopdracht.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
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
                .csrf(csrf -> csrf.disable())
                .httpBasic(basic -> basic.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth


//                        Deze doen het goed, en/of hebben geen USER/ADMIN nodig:
//                                .requestMatchers(HttpMethod.POST, "/single/uploadDB").permitAll()
//
//                                .requestMatchers(HttpMethod.GET, "/properties/{propertyId}").permitAll()
//                                .requestMatchers(HttpMethod.GET, "/properties").permitAll()
//
//                                .requestMatchers(HttpMethod.POST, "/auth").permitAll()


//                                .requestMatchers(HttpMethod.GET, "/users/**").hasAnyAuthority("ADMIN")
//                                .requestMatchers(HttpMethod.GET, "/users").hasAnyAuthority("ADMIN")
////                              TODO: Check onderstaande of klopt. En wil ik dit nog implementeren?
//                                .requestMatchers(HttpMethod.POST, "/users").permitAll()
//
//                                .requestMatchers(HttpMethod.POST, "/accounts").hasAnyAuthority("ADMIN", "USER")
//                                .requestMatchers(HttpMethod.GET, "/accounts/**").hasAnyAuthority("ADMIN", "USER")
//                                .requestMatchers(HttpMethod.GET, "/accounts").hasAnyAuthority("ADMIN")
//                                .requestMatchers(HttpMethod.DELETE, "/accounts/**").hasAnyAuthority("ADMIN")
//
//                                .requestMatchers(HttpMethod.POST, "/properties").hasAnyAuthority("ADMIN")
//                                .requestMatchers(HttpMethod.PATCH, "/properties/**/favorite").hasAnyAuthority("USER")
//                                .requestMatchers(HttpMethod.DELETE, "/properties/**").hasAnyAuthority("ADMIN")
//
//                                .requestMatchers(HttpMethod.GET, "/roles").hasAnyAuthority("ADMIN")
//
//                                .requestMatchers(HttpMethod.POST, "/viewings").hasAnyAuthority("ADMIN", "USER")
//                                .requestMatchers(HttpMethod.GET, "/viewings").hasAnyAuthority("ADMIN", "USER")
//                                .requestMatchers(HttpMethod.DELETE, "/viewings/**").hasAnyAuthority("ADMIN", "USER")
//
//                                .requestMatchers(HttpMethod.GET, "/downloadFromDB/**").hasAnyAuthority("ADMIN") // single download
//                                .requestMatchers(HttpMethod.POST, "/multiple/uploadDB").permitAll() // multiple upload
//                                .requestMatchers(HttpMethod.GET, "/downloadAllFromDB").hasAnyAuthority("ADMIN") // multiple download
//                                .anyRequest().authenticated()
                                .anyRequest().permitAll()






                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
