package com.test.api.marvelchallenge.exception;

/**
 * Excepción personalizada para representar errores específicos de la API. Esta excepción puede ser lanzada en
 * situaciones donde ocurra un error en la lógica de la API o en la interacción con servicios externos.
 */
public class ApiErrorException extends RuntimeException {

    /**
     * Crea una nueva instancia de `ApiErrorException` sin mensaje de error.
     */
    public ApiErrorException() {
        super();
    }

    /**
     * Crea una nueva instancia de `ApiErrorException` con un mensaje de error descriptivo.
     *
     * @param message El mensaje de error que describe la razón de la excepción.
     */
    public ApiErrorException(String message) {
        super(message);
    }

    /**
     * Crea una nueva instancia de `ApiErrorException` con un mensaje de error descriptivo y una causa subyacente.
     *
     * @param message El mensaje de error que describe la razón de la excepción.
     * @param cause La causa subyacente que originó esta excepción.
     */
    public ApiErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Crea una nueva instancia de `ApiErrorException` con una causa subyacente.
     *
     * @param cause La causa subyacente que originó esta excepción.
     */
    public ApiErrorException(Throwable cause) {
        super(cause);
    }
}

