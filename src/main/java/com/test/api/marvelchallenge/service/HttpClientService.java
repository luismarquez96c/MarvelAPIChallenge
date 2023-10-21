package com.test.api.marvelchallenge.service;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

/**
 * La interfaz HttpClientService define métodos para realizar operaciones HTTP, como GET, POST, PUT y DELETE, utilizando un cliente HTTP.
 */
public interface HttpClientService {

    /**
     * Realiza una solicitud HTTP GET al endpoint especificado con parámetros de consulta y devuelve una respuesta del tipo especificado.
     *
     * @param endpoint    El endpoint de la solicitud HTTP.
     * @param queryParams Parámetros de consulta para la solicitud.
     * @param responseType Tipo de la respuesta esperada.
     * @param <T>         Tipo de la respuesta esperada.
     * @return Respuesta del tipo especificado.
     */
    <T> T doGet(String endpoint, Map<String, String> queryParams, Class<T> responseType);

    /**
     * Realiza una solicitud HTTP POST al endpoint especificado con parámetros de consulta y cuerpo de solicitud, y devuelve una respuesta del tipo especificado.
     *
     * @param endpoint    El endpoint de la solicitud HTTP.
     * @param queryParams Parámetros de consulta para la solicitud.
     * @param responseType Tipo de la respuesta esperada.
     * @param bodyRequest Cuerpo de la solicitud.
     * @param <T>         Tipo de la respuesta esperada.
     * @param <R>         Tipo del cuerpo de la solicitud.
     * @return Respuesta del tipo especificado.
     */
    <T, R> T doPost(String endpoint, Map<String, String> queryParams, Class<T> responseType, R bodyRequest);

    /**
     * Realiza una solicitud HTTP PUT al endpoint especificado con parámetros de consulta y cuerpo de solicitud, y devuelve una respuesta del tipo especificado.
     *
     * @param endpoint    El endpoint de la solicitud HTTP.
     * @param queryParams Parámetros de consulta para la solicitud.
     * @param responseType Tipo de la respuesta esperada.
     * @param bodyRequest Cuerpo de la solicitud.
     * @param <T>         Tipo de la respuesta esperada.
     * @param <R>         Tipo del cuerpo de la solicitud.
     * @return Respuesta del tipo especificado.
     */
    <T, R> T doPut(String endpoint, Map<String, String> queryParams, Class<T> responseType, R bodyRequest);

    /**
     * Realiza una solicitud HTTP DELETE al endpoint especificado con parámetros de consulta y devuelve una respuesta del tipo especificado.
     *
     * @param endpoint    El endpoint de la solicitud HTTP.
     * @param queryParams Parámetros de consulta para la solicitud.
     * @param responseType Tipo de la respuesta esperada.
     * @param <T>         Tipo de la respuesta esperada.
     * @return Respuesta del tipo especificado.
     */
    <T> T doDelete(String endpoint, Map<String, String> queryParams, Class<T> responseType);
}

