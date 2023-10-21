package com.test.api.marvelchallenge.persistence.integration.marvel.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.CharacterDto;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.ThumbnailDto;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase `CharacterMapper` proporciona métodos para mapear datos de objetos JsonNode a objetos DTO relacionados con personajes.
 */
public class CharacterMapper {

    /**
     * Convierte un objeto JsonNode que representa una lista de personajes en una lista de objetos CharacterDto.
     *
     * @param rootNode El nodo JsonNode que contiene la lista de personajes.
     * @return Una lista de objetos CharacterDto que representan los personajes.
     * @throws IllegalArgumentException Si el nodo JsonNode es nulo.
     */
    public static List<CharacterDto> toDtoList(JsonNode rootNode){
        ArrayNode resultsNode = getResultsNode(rootNode);

        List<CharacterDto> characters = new ArrayList<>();
        resultsNode.elements().forEachRemaining( each -> {
            characters.add( CharacterMapper.toDto(each) );
        } );

        return characters;
    }

    /**
     * Convierte un objeto JsonNode que representa un personaje en un objeto CharacterDto.
     *
     * @param characterNode El nodo JsonNode que contiene información sobre un personaje.
     * @return Un objeto CharacterDto que representa el personaje.
     * @throws IllegalArgumentException Si el nodo JsonNode es nulo.
     */
    private static CharacterDto toDto(JsonNode characterNode) {
        if(characterNode == null){
            throw new IllegalArgumentException("El nodo json no puede ser null");
        }

        CharacterDto dto = new CharacterDto(
                Long.parseLong(characterNode.get("id").asText()),
                characterNode.get("name").asText(),
                characterNode.get("description").asText(),
                characterNode.get("modified").asText(),
                characterNode.get("resourceURI").asText()
        );

        return dto;
    }

    /**
     * Obtiene el nodo "results" de un objeto JsonNode.
     *
     * @param rootNode El nodo JsonNode que contiene la información de los resultados.
     * @return El nodo "results" que contiene la lista de personajes.
     * @throws IllegalArgumentException Si el nodo JsonNode es nulo.
     */
    private static ArrayNode getResultsNode(JsonNode rootNode){
        if(rootNode == null){
            throw new IllegalArgumentException("El nodo json no puede ser null");
        }

        JsonNode dataNode = rootNode.get("data");
        return (ArrayNode) dataNode.get("results");
    }

    /**
     * Convierte un objeto JsonNode que representa una lista de información detallada de personajes en una lista de objetos CharacterDto.CharacterInfoDto.
     *
     * @param response El nodo JsonNode que contiene la lista de información detallada de personajes.
     * @return Una lista de objetos CharacterDto.CharacterInfoDto que representan la información detallada de personajes.
     * @throws IllegalArgumentException Si el nodo JsonNode es nulo.
     */
    public static List<CharacterDto.CharacterInfoDto> toInfoDtoList(JsonNode response) {
        ArrayNode resultsNode = getResultsNode(response);

        List<CharacterDto.CharacterInfoDto> characters = new ArrayList<>();
        resultsNode.elements().forEachRemaining( each -> {
            characters.add( CharacterMapper.toInfoDto(each) );
        } );

        return characters;
    }

    /**
     * Convierte un objeto JsonNode que representa información detallada de un personaje en un objeto CharacterDto.CharacterInfoDto.
     *
     * @param characterNode El nodo JsonNode que contiene información detallada de un personaje.
     * @return Un objeto CharacterDto.CharacterInfoDto que representa la información detallada del personaje.
     * @throws IllegalArgumentException Si el nodo JsonNode es nulo.
     */
    private static CharacterDto.CharacterInfoDto toInfoDto(JsonNode characterNode) {
        if(characterNode == null){
            throw new IllegalArgumentException("El nodo json no puede ser null");
        }

        JsonNode thumbnailNode = characterNode.get("thumbnail");
        ThumbnailDto thumbnailDto = ThumbnailMapper.toDto(thumbnailNode);
        String image = thumbnailDto.path().concat(".").concat(thumbnailDto.extension());

        CharacterDto.CharacterInfoDto dto = new CharacterDto.CharacterInfoDto(
                image,
                characterNode.get("description").asText()
        );

        return dto;
    }
}

