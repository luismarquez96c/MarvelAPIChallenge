package com.test.api.marvelchallenge.service.impl;

import com.test.api.marvelchallenge.dto.GetUserInteractionLogDto;
import com.test.api.marvelchallenge.mapper.UserInteractionLogMapper;
import com.test.api.marvelchallenge.persistence.repository.UserInteractionLogRepository;
import com.test.api.marvelchallenge.service.UserInteractionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * La clase UserInteractionLogServiceImpl implementa el servicio de registro de interacciones de usuario.
 */
/**
 * La clase UserInteractionLogServiceImpl implementa el servicio de registro de interacciones de usuario.
 */
@Service
public class UserInteractionLogServiceImpl implements UserInteractionLogService {

    @Autowired
    private UserInteractionLogRepository userInteractionLogRepository;

    /**
     * Recupera todas las interacciones de usuario paginadas.
     *
     * @param pageable Configuración de paginación.
     * @return Una página de DTOs que representan las interacciones de usuario.
     */
    @Override
    public Page<GetUserInteractionLogDto> findAll(Pageable pageable) {
        return userInteractionLogRepository.findAll(pageable)
                .map(UserInteractionLogMapper::toDto);
    }

    /**
     * Recupera las interacciones de un usuario específico paginadas.
     *
     * @param pageable Configuración de paginación.
     * @param username Nombre de usuario del usuario cuyas interacciones se desean recuperar.
     * @return Una página de DTOs que representan las interacciones de usuario del usuario especificado.
     */
    @Override
    public Page<GetUserInteractionLogDto> findByUsername(Pageable pageable, String username) {
        return userInteractionLogRepository.findByUsername(pageable, username)
                .map(UserInteractionLogMapper::toDto);
    }
}

