package com.test.api.marvelchallenge.service;

import com.test.api.marvelchallenge.dto.MyPageable;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.CharacterDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * La interfaz CharacterService define métodos para recuperar información sobre personajes de cómics.
 */
public interface CharacterService {

    /**
     * Recupera una lista de personajes utilizando paginación y varios criterios de búsqueda.
     *
     * @param pageable Objeto que proporciona la paginación mediante offset y limit.
     * @param name     Nombre del personaje o filtro de búsqueda (opcional).
     * @param comics   Arreglo de identificadores de cómics relacionados con el personaje (opcional).
     * @param series   Arreglo de identificadores de series relacionadas con el personaje (opcional).
     * @return Lista de personajes que cumplen con los criterios de búsqueda y paginación especificados.
     */
    List<CharacterDto> findAll(MyPageable pageable, String name, int[] comics, int[] series);

    /**
     * Recupera información detallada de un personaje por su identificador.
     *
     * @param characterId Identificador del personaje.
     * @return Información detallada del personaje correspondiente al identificador proporcionado.
     */
    CharacterDto.CharacterInfoDto findInfoById(Long characterId);
}

