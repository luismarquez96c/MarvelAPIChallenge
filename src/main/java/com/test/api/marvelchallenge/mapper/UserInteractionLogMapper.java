package com.test.api.marvelchallenge.mapper;

import com.test.api.marvelchallenge.dto.GetUserInteractionLogDto;
import com.test.api.marvelchallenge.persistence.entity.UserInteractionLog;

/**
 * La clase `UserInteractionLogMapper` proporciona un método para mapear una entidad `UserInteractionLog` a un objeto `GetUserInteractionLogDto`.
 */
public class UserInteractionLogMapper {

    /**
     * Convierte una entidad `UserInteractionLog` en un objeto `GetUserInteractionLogDto`.
     *
     * @param entity La entidad `UserInteractionLog` que se va a convertir.
     * @return Un objeto `GetUserInteractionLogDto` que representa la información de interacción del usuario.
     */
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

