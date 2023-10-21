package com.test.api.marvelchallenge.persistence.integration.marvel.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.api.marvelchallenge.dto.MyPageable;
import com.test.api.marvelchallenge.persistence.integration.marvel.MarvelAPIConfig;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.ComicDto;
import com.test.api.marvelchallenge.persistence.integration.marvel.mapper.ComicMapper;
import com.test.api.marvelchallenge.service.HttpClientService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * El repositorio `ComicRepository` proporciona métodos para acceder y gestionar información de cómics desde la API de Marvel.
 */
@Repository
public class ComicRepository {

    @Autowired
    private MarvelAPIConfig marvelAPIConfig;
    @Autowired
    private HttpClientService httpClientService;

    @Value("${integration.marvel.base-path}")
    private String basePath;
    private String comicPath;

    /**
     * Inicializa la URL base para acceder a los cómics en la API de Marvel.
     */
    @PostConstruct
    private void setPath(){
        comicPath = basePath.concat("/").concat("comics");
    }

    /**
     * Recupera una lista de cómics con la posibilidad de paginación y filtrado por personaje.
     *
     * @param pageable    La información de paginación que incluye el offset y el límite de resultados.
     * @param characterId El ID del personaje para filtrar cómics específicos, o nulo si no se realiza un filtrado por personaje.
     * @return Una lista de cómics en forma de objetos DTO.
     */
    public List<ComicDto> findAll(MyPageable pageable, Long characterId) {
        Map<String, String> marvelQueryParams = getQueryParamsForFindAll(pageable, characterId);

        JsonNode response = httpClientService.doGet(comicPath, marvelQueryParams, JsonNode.class);

        return ComicMapper.toDtoList(response);
    }

    /**
     * Construye los parámetros de consulta necesarios para recuperar cómics con paginación y filtrado por personaje.
     *
     * @param pageable    La información de paginación que incluye el offset y el límite de resultados.
     * @param characterId El ID del personaje para filtrar cómics específicos, o nulo si no se realiza un filtrado por personaje.
     * @return Un mapa de parámetros de consulta para la API de Marvel.
     */
    private Map<String, String> getQueryParamsForFindAll(MyPageable pageable, Long characterId) {
        Map<String, String> marvelQueryParams = marvelAPIConfig.getAuthenticationQueryParams();

        marvelQueryParams.put("offset", Long.toString(pageable.offset()));
        marvelQueryParams.put("limit", Long.toString(pageable.limit()));

        if(characterId != null && characterId.longValue() > 0){
            marvelQueryParams.put("characters", Long.toString(characterId));
        }

        return marvelQueryParams;
    }

    /**
     * Recupera información detallada sobre un cómic específico por su ID.
     *
     * @param comicId El ID del cómic que se desea recuperar.
     * @return Un objeto DTO que representa el cómic.
     */
    public ComicDto findById(Long comicId) {
        Map<String, String> marvelQueryParams = marvelAPIConfig.getAuthenticationQueryParams();

        String finalUrl = comicPath.concat("/").concat(Long.toString(comicId));
        JsonNode response = httpClientService.doGet(finalUrl, marvelQueryParams, JsonNode.class);

        return ComicMapper.toDtoList(response).get(0);
    }
}

