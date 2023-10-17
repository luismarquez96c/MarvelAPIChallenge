package com.test.api.marvelchallenge.mapper;

import com.test.api.marvelchallenge.dto.GetUserInteractionLogDto;
import com.test.api.marvelchallenge.persistence.entity.UserInteractionLog;

public class UserInteractionLogMapper {

    public static GetUserInteractionLogDto toDto(UserInteractionLog entity){

        if(entity == null) return null;

        return new GetUserInteractionLogDto(
                entity.getId(),
                entity.getUrl(),
                entity.getHttpMethod(),
                entity.getUsername(),
                entity.getTimestamp(),
                entity.getRemoteAddress()
        );

    }

}
