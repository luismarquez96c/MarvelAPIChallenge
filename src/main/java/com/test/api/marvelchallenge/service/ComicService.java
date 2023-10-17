package com.test.api.marvelchallenge.service;

import com.test.api.marvelchallenge.dto.MyPageable;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.ComicDto;

import java.util.List;

public interface ComicService {
    List<ComicDto> findAll(MyPageable pageable, Long characterId);

    ComicDto findById(Long comicId);
}
