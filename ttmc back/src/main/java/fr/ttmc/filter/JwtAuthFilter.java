package fr.ttmc.filter;

import fr.ttmc.tools.JwtUtils;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final UserDetailsService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && (authHeader.startsWith("Bearer ") || authHeader.startsWith("bearer "))) {
            String token = authHeader.substring(7);
            try {
                String username = JwtUtils.extractUsername(token);
                if (username != null) {
                    UserDetails userDetails = service.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(userToken);
                }
            }
            catch (JwtException e) {

            }

        }
        filterChain.doFilter(request, response);
    }
}
