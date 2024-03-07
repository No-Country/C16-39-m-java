package com.c1639.backend.security;

import com.c1639.backend.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain
    ) throws ServletException, IOException {

        String token = tokenService.getTokenFromHeader(request);

        if (token != null) {

            String subject = tokenService.getVerifier(token).getSubject();

            if (subject != null) {
                //Buscar usuario en BD
                var usuario = userRepository.findByEmailAndActiveTrue(subject);

                var authentication = new UsernamePasswordAuthenticationToken(
                  usuario,
                  null,
                  usuario.getAuthorities() //Forzamos el inicio de sesion
                );

                //Seteamos manualmente el usuario autenticado
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

}
