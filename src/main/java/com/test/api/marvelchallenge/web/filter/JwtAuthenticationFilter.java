package com.test.api.marvelchallenge.web.filter;

import com.test.api.marvelchallenge.service.JwtService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro de autenticación JWT que intercepta las solicitudes entrantes y verifica la presencia de un token JWT válido en la cabecera de autorización.
 * Si se encuentra un token válido, autentica al usuario y establece la autenticación en el contexto de seguridad.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Realiza la verificación de autenticación JWT en cada solicitud entrante.
     *
     * @param request     La solicitud HTTP entrante.
     * @param response    La respuesta HTTP.
     * @param filterChain Cadena de filtros para el procesamiento de la solicitud.
     * @throws ServletException Si ocurre un error durante el procesamiento de la solicitud.
     * @throws IOException      Si ocurre un error de E/S durante el procesamiento de la solicitud.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization"); // Formato: Bearer jwt

        if (!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authorizationHeader.split(" ")[1];
        String subject = null;

        try {
            subject = jwtService.extractSubject(jwt);
        } catch (JwtException jwtException) {
            filterChain.doFilter(request, response);
            return;
        }

        UserDetails user = userDetailsService.loadUserByUsername(subject);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}

