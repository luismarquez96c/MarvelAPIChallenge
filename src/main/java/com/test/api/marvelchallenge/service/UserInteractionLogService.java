package com.test.api.marvelchallenge.service;

import com.test.api.marvelchallenge.dto.GetUserInteractionLogDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserInteractionLogService {
    Page<GetUserInteractionLogDto> findAll(Pageable pageable);

    Page<GetUserInteractionLogDto> findByUsername(Pageable pageable, String username);
}
