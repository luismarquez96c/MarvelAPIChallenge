package com.test.api.marvelchallenge.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuración que define beans genéricos y compartidos en la aplicación.
 */
@Configuration
public class GenericBeansInjector {

    /**
     * Define un bean de RestTemplate que se utilizará para realizar solicitudes HTTP en la aplicación.
     *
     * @return Un objeto RestTemplate configurado para realizar solicitudes HTTP.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

