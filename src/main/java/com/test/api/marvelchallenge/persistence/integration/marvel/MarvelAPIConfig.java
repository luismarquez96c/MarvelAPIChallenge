package com.test.api.marvelchallenge.persistence.integration.marvel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuración y gestión de las credenciales requeridas para interactuar con la API de Marvel.
 */
@Component
public class MarvelAPIConfig {

    @Autowired
    @Qualifier("md5Encoder")
    private PasswordEncoder md5Encoder;

    private long timestamp = new Date(System.currentTimeMillis()).getTime();

    @Value("${integration.marvel.public-key}")
    private String publicKey;

    @Value("${integration.marvel.private-key}")
    private String privateKey;

    /**
     * Obtiene el valor hash requerido para la autenticación en la API de Marvel.
     *
     * @return El valor hash generado para la autenticación.
     */
    private String getHash() {
        String hashDecoded = Long.toString(timestamp).concat(privateKey).concat(publicKey);
        return md5Encoder.encode(hashDecoded);
    }

    /**
     * Obtiene los parámetros de consulta necesarios para la autenticación en la API de Marvel.
     *
     * @return Un mapa que contiene los parámetros de autenticación (ts, apikey y hash).
     */
    public Map<String, String> getAuthenticationQueryParams() {
        Map<String, String> securityQueryParams = new HashMap<>();

        securityQueryParams.put("ts", Long.toString(timestamp));
        securityQueryParams.put("apikey", publicKey);
        securityQueryParams.put("hash", this.getHash());

        return securityQueryParams;
    }
}

