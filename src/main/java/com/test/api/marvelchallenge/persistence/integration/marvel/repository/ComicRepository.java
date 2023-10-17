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

@Repository
public class ComicRepository {

    @Autowired
    private MarvelAPIConfig marvelAPIConfig;
    @Autowired
    private HttpClientService httpClientService;

    @Value("${integration.marvel.base-path}")
    private String basePath;
    private String comicPath;

    @PostConstruct
    private void setPath(){
        comicPath = basePath.concat("/").concat("comics");
    }

    public List<ComicDto> findAll(MyPageable pageable, Long characterId) {
        Map<String, String> marvelQueryParams = getQueryParamsForFindAll(pageable, characterId);

        JsonNode response = httpClientService.doGet(comicPath, marvelQueryParams, JsonNode.class);

        return ComicMapper.toDtoList(response);
    }

    private Map<String, String> getQueryParamsForFindAll(MyPageable pageable, Long characterId) {
        Map<String, String> marvelQueryParams = marvelAPIConfig.getAuthenticationQueryParams();

        marvelQueryParams.put("offset", Long.toString(pageable.offset()));
        marvelQueryParams.put("limit", Long.toString(pageable.limit()));

        if(characterId != null && characterId.longValue() > 0){
            marvelQueryParams.put("characters", Long.toString(characterId));
        }

        return marvelQueryParams;
    }

    public ComicDto findById(Long comicId) {
        Map<String, String> marvelQueryParams = marvelAPIConfig.getAuthenticationQueryParams();

        String finalUrl = comicPath.concat("/").concat(Long.toString(comicId));
        JsonNode response = httpClientService.doGet(finalUrl, marvelQueryParams, JsonNode.class);

        return ComicMapper.toDtoList(response).get(0);
    }
}
