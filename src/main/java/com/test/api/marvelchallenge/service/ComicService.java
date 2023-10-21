package com.test.api.marvelchallenge.service;

import com.test.api.marvelchallenge.dto.MyPageable;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.ComicDto;

import java.util.List;

/**
 * La interfaz ComicService define métodos para recuperar información sobre cómics.
 */
public interface ComicService {

    /**
     * Recupera una lista de cómics utilizando paginación y un identificador de personaje.
     *
     * @param pageable     Objeto que proporciona la paginación mediante offset y limit.
     * @param characterId  Identificador del personaje asociado a los cómics.
     * @return Lista de cómics que cumplen con los criterios especificados.
     */
    List<ComicDto> findAll(MyPageable pageable, Long characterId);

    /**
     * Recupera un cómic por su identificador.
     *
     * @param comicId Identificador del cómic.
     * @return El cómic correspondiente al identificador proporcionado.
     */
    ComicDto findById(Long comicId);
}

