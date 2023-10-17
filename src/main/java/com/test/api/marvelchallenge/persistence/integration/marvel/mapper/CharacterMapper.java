package com.test.api.marvelchallenge.persistence.integration.marvel.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.CharacterDto;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.ThumbnailDto;

import java.util.ArrayList;
import java.util.List;

public class CharacterMapper {

    public static List<CharacterDto> toDtoList(JsonNode rootNode){
        ArrayNode resultsNode = getResultsNode(rootNode);

        List<CharacterDto> characters = new ArrayList<>();
        resultsNode.elements().forEachRemaining( each -> {
            characters.add( CharacterMapper.toDto(each) );
        } );

        return characters;
    }

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

    private static ArrayNode getResultsNode(JsonNode rootNode){
        if(rootNode == null){
            throw new IllegalArgumentException("El nodo json no puede ser null");
        }

        JsonNode dataNode = rootNode.get("data");
        return (ArrayNode) dataNode.get("results");
    }

    public static List<CharacterDto.CharacterInfoDto> toInfoDtoList(JsonNode response) {
        ArrayNode resultsNode = getResultsNode(response);

        List<CharacterDto.CharacterInfoDto> characters = new ArrayList<>();
        resultsNode.elements().forEachRemaining( each -> {
            characters.add( CharacterMapper.toInfoDto(each) );
        } );

        return characters;

    }

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
