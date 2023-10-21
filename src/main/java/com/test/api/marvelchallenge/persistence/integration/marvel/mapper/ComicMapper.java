package com.test.api.marvelchallenge.persistence.integration.marvel.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.ComicDto;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.ThumbnailDto;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase `ComicMapper` proporciona métodos para mapear datos de objetos JsonNode a objetos DTO relacionados con cómics.
 */
public class ComicMapper {

    /**
     * Convierte un objeto JsonNode que representa una lista de cómics en una lista de objetos ComicDto.
     *
     * @param rootNode El nodo JsonNode que contiene la lista de cómics.
     * @return Una lista de objetos ComicDto que representan los cómics.
     * @throws IllegalArgumentException Si el nodo JsonNode es nulo.
     */
    public static List<ComicDto> toDtoList(JsonNode rootNode){
        ArrayNode resultsNode = getResultsNode(rootNode);

        List<ComicDto> comics = new ArrayList<>();
        resultsNode.elements().forEachRemaining(each -> {
            comics.add(ComicMapper.toDto(each));
        });

        return comics;
    }

    /**
     * Convierte un objeto JsonNode que representa un cómic en un objeto ComicDto.
     *
     * @param comicNode El nodo JsonNode que contiene información sobre un cómic.
     * @return Un objeto ComicDto que representa el cómic.
     * @throws IllegalArgumentException Si el nodo JsonNode es nulo.
     */
    private static ComicDto toDto(JsonNode comicNode) {
        if(comicNode == null){
            throw new IllegalArgumentException("El nodo json no puede ser null");
        }

        ThumbnailDto thumbnailDto = ThumbnailMapper.toDto(comicNode.get("thumbnail"));

        ComicDto dto = new ComicDto(
                comicNode.get("id").asLong(),
                comicNode.get("title").asText(),
                comicNode.get("description").asText(),
                comicNode.get("modified").asText(),
                comicNode.get("resourceURI").asText(),
                thumbnailDto
        );

        return dto;
    }

    /**
     * Obtiene el nodo "results" de un objeto JsonNode.
     *
     * @param rootNode El nodo JsonNode que contiene la información de los resultados.
     * @return El nodo "results" que contiene la lista de cómics.
     * @throws IllegalArgumentException Si el nodo JsonNode es nulo.
     */
    private static ArrayNode getResultsNode(JsonNode rootNode){
        if(rootNode == null){
            throw new IllegalArgumentException("El nodo json no puede ser null");
        }

        JsonNode dataNode = rootNode.get("data");
        return (ArrayNode) dataNode.get("results");
    }
}

