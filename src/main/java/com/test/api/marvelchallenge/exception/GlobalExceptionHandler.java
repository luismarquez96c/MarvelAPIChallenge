package com.test.api.marvelchallenge.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;

/**
 * El manejador global de excepciones para la aplicación. Gestiona las excepciones generales y las redirige a
 * manejadores de excepciones específicos según el tipo de excepción. Proporciona respuestas adecuadas para diferentes
 * tipos de errores, incluyendo errores HTTP, denegación de acceso y excepciones generales.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja las excepciones generales lanzadas en la aplicación. Redirige la excepción a un manejador específico
     * según el tipo de excepción.
     *
     * @param exception La excepción general que se va a manejar.
     * @param request La solicitud HTTP que generó la excepción.
     * @param webRequest La solicitud web que generó la excepción.
     * @return Una respuesta adecuada para el tipo de excepción.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDto> handlerGeneralExceptions(Exception exception,
                                                                HttpServletRequest request,
                                                                WebRequest webRequest){
        exception.printStackTrace();
        if(exception instanceof HttpClientErrorException){
            return this.handleHttpClientErrorException((HttpClientErrorException) exception, request, webRequest);
        }else if(exception instanceof AccessDeniedException){
            return this.handleAccessDeniedException((AccessDeniedException) exception, request, webRequest);
        }else if(exception instanceof AuthenticationCredentialsNotFoundException){
            return this.handleAuthenticationCredentialsNotFoundException((AuthenticationCredentialsNotFoundException) exception, request, webRequest);
        }else {
            return this.handleGenericException(exception, request, webRequest);
        }

    }

    /**
     * Maneja una excepción genérica que no coincide con otros manejadores de excepciones específicos.
     *
     * @param exception La excepción genérica a manejar.
     * @param request La solicitud HTTP que generó la excepción.
     * @param webRequest La solicitud web que generó la excepción.
     * @return Una respuesta interna del servidor con detalles de error.
     */
    private ResponseEntity<ApiErrorDto> handleGenericException(
            Exception exception,
            HttpServletRequest request,
            WebRequest webRequest) {

        ApiErrorDto dto = new ApiErrorDto(
                "Error inesperado vuelva a intentarlo",
                exception.getMessage(),
                request.getMethod(),
                request.getRequestURL().toString()
        );

        return ResponseEntity.internalServerError().body(dto);

    }

    /**
     * Maneja una excepción de tipo `AuthenticationCredentialsNotFoundException`, que indica que el usuario no tiene acceso
     * a un recurso protegido debido a la falta de credenciales de autenticación.
     *
     * @param exception La excepción de falta de credenciales de autenticación.
     * @param request La solicitud HTTP que generó la excepción.
     * @param webRequest La solicitud web que generó la excepción.
     * @return Una respuesta de error no autorizada.
     */
    private ResponseEntity<ApiErrorDto> handleAuthenticationCredentialsNotFoundException(
            AuthenticationCredentialsNotFoundException exception,
            HttpServletRequest request,
            WebRequest webRequest) {

        ApiErrorDto dto = new ApiErrorDto(
                "No tienes acceso a este recurso",
                exception.getMessage(),
                request.getMethod(),
                request.getRequestURL().toString()
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(dto);
    }

    /**
     * Maneja una excepción de tipo `AccessDeniedException`, que indica que el usuario no tiene acceso a un recurso protegido
     * debido a la denegación de acceso.
     *
     * @param exception La excepción de denegación de acceso.
     * @param request La solicitud HTTP que generó la excepción.
     * @param webRequest La solicitud web que generó la excepción.
     * @return Una respuesta de error prohibido.
     */
    private ResponseEntity<ApiErrorDto> handleAccessDeniedException(
            AccessDeniedException exception,
            HttpServletRequest request,
            WebRequest webRequest) {

        ApiErrorDto dto = new ApiErrorDto(
                "No tienes acceso a este recurso",
                exception.getMessage(),
                request.getMethod(),
                request.getRequestURL().toString()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(dto);
    }

    /**
     * Maneja una excepción de cliente HTTP, como `HttpClientErrorException`, y determina la respuesta adecuada en función
     * del tipo de error HTTP.
     *
     * @param exception La excepción de cliente HTTP.
     * @param request La solicitud HTTP que generó la excepción.
     * @param webRequest La solicitud web que generó la excepción.
     * @return Una respuesta HTTP apropiada con detalles de error.
     */
    private ResponseEntity<ApiErrorDto> handleHttpClientErrorException(
            HttpClientErrorException exception,
            HttpServletRequest request,
            WebRequest webRequest) {

        String message = null;

        if(exception instanceof HttpClientErrorException.Forbidden){
            message = "No tienes acceso a este recurso";
        }else if (exception instanceof HttpClientErrorException.Unauthorized){
            message = "No haz iniciado sessión para acceder a este recurso";
        }else if (exception instanceof HttpClientErrorException.NotFound){
            message = "Recurso no encontrado";
        }else if (exception instanceof HttpClientErrorException.Conflict){
            message = "Conflicto en el procesamiento de la petición";
        }else{
            message = "Error inesperado al realizar consulta";
        }

        ApiErrorDto dto = new ApiErrorDto(
                message,
                exception.getMessage(),
                request.getMethod(),
                request.getRequestURL().toString()
        );

        return ResponseEntity.status(exception.getStatusCode()).body(dto);
    }

}
