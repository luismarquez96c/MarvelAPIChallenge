package com.test.api.marvelchallenge.web.interceptor;

import com.test.api.marvelchallenge.exception.ApiErrorException;
import com.test.api.marvelchallenge.persistence.entity.UserInteractionLog;
import com.test.api.marvelchallenge.persistence.repository.UserInteractionLogRepository;
import com.test.api.marvelchallenge.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

/**
 * Interceptor para el registro de interacciones del usuario. Este interceptor captura las solicitudes a los endpoints relacionados con "comics" y "characters".
 * Registra información sobre la interacción del usuario, como el método HTTP utilizado, la URL de la solicitud, el nombre de usuario, la dirección remota y la marca de tiempo.
 */
@Component
public class UserInteractionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserInteractionLogRepository userLogRepository;

    @Autowired
    @Lazy
    private AuthenticationService authenticationService;

    /**
     * Intercepta las solicitudes antes de ser manejadas por el controlador.
     *
     * @param request  La solicitud HTTP entrante.
     * @param response La respuesta HTTP.
     * @param handler  El controlador o método del controlador que manejará la solicitud.
     * @return true si la solicitud debe continuar siendo procesada; de lo contrario, false.
     * @throws Exception Si ocurre un error durante el procesamiento de la solicitud.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("Inteceptor: " + this.getClass().getName());

        String requestURI = request.getRequestURI();

        if (requestURI.startsWith("/comics") || requestURI.startsWith("/characters")) {
            UserInteractionLog userLog = new UserInteractionLog();
            userLog.setHttpMethod(request.getMethod());
            userLog.setUrl(request.getRequestURL().toString());

            UserDetails user = authenticationService.getUserLoggedIn();
            userLog.setUsername(user.getUsername());
            userLog.setRemoteAddress(request.getRemoteAddr());
            userLog.setTimestamp(LocalDateTime.now());

            try {
                userLogRepository.save(userLog);
                return true;
            } catch (Exception exception) {
                throw new ApiErrorException("No se logró guardar el registro de interacción correctamente");
            }
        }

        return true;
    }
}

