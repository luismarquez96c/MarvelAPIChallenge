package com.test.api.marvelchallenge.persistence.integration.marvel.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.api.marvelchallenge.dto.MyPageable;
import com.test.api.marvelchallenge.persistence.integration.marvel.mapper.CharacterMapper;
import com.test.api.marvelchallenge.persistence.integration.marvel.MarvelAPIConfig;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.CharacterDto;
import com.test.api.marvelchallenge.service.HttpClientService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * El repositorio `CharacterRepository` proporciona métodos para acceder y gestionar información de personajes desde la API de Marvel.
 */
@Repository
public class CharacterRepository {

    @Autowired
    private MarvelAPIConfig marvelAPIConfig;
    @Autowired
    private HttpClientService httpClientService;

    @Value("${integration.marvel.base-path}")
    private String basePath;
    private String characterPath;

    /**
     * Inicializa la URL base para acceder a los personajes en la API de Marvel.
     */
    @PostConstruct
    private void setPath(){
        characterPath = basePath.concat("/").concat("characters");
    }

    /**
     * Recupera una lista de personajes con la posibilidad de paginación y filtrado por nombre, cómics y series.
     *
     * @param pageable La información de paginación que incluye el offset y el límite de resultados.
     * @param name     El nombre del personaje para filtrar, o nulo si no se realiza un filtrado por nombre.
     * @param comics   Arreglo de IDs de cómics relacionados con el personaje, o nulo si no se realiza un filtrado por cómics.
     * @param series   Arreglo de IDs de series relacionadas con el personaje, o nulo si no se realiza un filtrado por series.
     * @return Una lista de personajes en forma de objetos DTO.
     */
    public List<CharacterDto> findAll(MyPageable pageable, String name, int[] comics, int[] series) {
        Map<String, String> marvelQueryParams = getQueryParamsForFindAll(pageable, name, comics, series);

        JsonNode response = httpClientService.doGet(characterPath, marvelQueryParams, JsonNode.class);

        return CharacterMapper.toDtoList(response);
    }

    /**
     * Construye los parámetros de consulta necesarios para recuperar personajes con paginación y filtrado por nombre, cómics y series.
     *
     * @param pageable La información de paginación que incluye el offset y el límite de resultados.
     * @param name     El nombre del personaje para filtrar, o nulo si no se realiza un filtrado por nombre.
     * @param comics   Arreglo de IDs de cómics relacionados con el personaje, o nulo si no se realiza un filtrado por cómics.
     * @param series   Arreglo de IDs de series relacionadas con el personaje, o nulo si no se realiza un filtrado por series.
     * @return Un mapa de parámetros de consulta para la API de Marvel.
     */
    private Map<String, String> getQueryParamsForFindAll(MyPageable pageable, String name, int[] comics, int[] series) {
        Map<String, String> marvelQueryParams = marvelAPIConfig.getAuthenticationQueryParams();

        marvelQueryParams.put("offset", Long.toString(pageable.offset()));
        marvelQueryParams.put("limit", Long.toString(pageable.limit()));

        if(StringUtils.hasText(name)){
            marvelQueryParams.put("name", name);
        }

        if(comics != null){
            String comicsAsString = this.joinIntArray(comics);
            marvelQueryParams.put("comics", comicsAsString);
        }

        if(series != null){
            String seriesAsString = this.joinIntArray(series);
            marvelQueryParams.put("series", seriesAsString);
        }

        return marvelQueryParams;
    }

    /**
     * Convierte un arreglo de enteros en una cadena separada por comas.
     *
     * @param comics Arreglo de enteros a convertir en cadena.
     * @return Una cadena que representa los enteros del arreglo separados por comas.
     */
    private String joinIntArray(int[] comics) {
        List<String> stringArray = IntStream.of(comics).boxed()
                .map(each -> each.toString())
                .collect(Collectors.toList());

        return String.join(",", stringArray);
    }

    /**
     * Recupera información detallada sobre un personaje específico por su ID.
     *
     * @param characterId El ID del personaje que se desea recuperar.
     * @return Un objeto DTO que representa la información del personaje.
     */
    public CharacterDto.CharacterInfoDto findInfoById(Long characterId) {
        Map<String, String> marvelQueryParams = marvelAPIConfig.getAuthenticationQueryParams();

        String finalUrl = characterPath.concat("/").concat(Long.toString(characterId));

        JsonNode response = httpClientService.doGet(finalUrl, marvelQueryParams, JsonNode.class);

        return CharacterMapper.toInfoDtoList(response).get(0);
    }
}

