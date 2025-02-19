package fr.ttmc.config;

import fr.ttmc.filter.JwtAuthFilter;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthFilter filter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                //Ne conserve pas l'état de connexion dans le contexte et implique une vérification à chaque requête
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //Intercepteur
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * My mvc configurer web mvc configurer.
     *
     * @return the web mvc configurer
     */
    @Bean
    public WebMvcConfigurer myMvcConfigurer() {

        return new WebMvcConfigurer() {

            // CORS ORIGIN
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/mail/schedule").allowedOrigins("*").allowedMethods("POST");
                registry.addMapping("/utilisateurs/tuteur").allowedOrigins("*").allowedMethods("POST", "PUT", "OPTIONS");
                registry.addMapping("/swagger-ui/index.html").allowedMethods("GET", "POST", "PUT").allowedOrigins("*");
                registry.addMapping("/**").allowedOrigins("*")
                        .allowedMethods("*", "GET", "POST", "PUT", "DELETE", "OPTIONS").allowedHeaders("*")
//						.exposedHeaders("Content-Type", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers",
//								"Origin", "Authorization", "X-Requested-With", "requestId", "Correlation-Id")
                ;
            }

        };
    }
}
