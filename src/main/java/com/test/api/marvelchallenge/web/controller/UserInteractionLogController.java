package com.test.api.marvelchallenge.web.controller;

import com.test.api.marvelchallenge.dto.GetUserInteractionLogDto;
import com.test.api.marvelchallenge.persistence.entity.UserInteractionLog;
import com.test.api.marvelchallenge.service.UserInteractionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Clase controladora que gestiona las operaciones relacionadas con los registros de interacciones de usuarios.
 * Proporciona endpoints para recuperar registros de interacciones y buscar registros por nombre de usuario.
 */
@RestController
@RequestMapping("/users-interactions")
public class UserInteractionLogController {

    @Autowired
    private UserInteractionLogService userInteractionLogService;

    /**
     * Endpoint para recuperar todos los registros de interacciones de usuarios.
     *
     * @param offset Valor que representa el desplazamiento de la paginación (por defecto, 0).
     * @param limit  Cantidad máxima de resultados por página (por defecto, 10).
     * @return ResponseEntity que contiene una página de registros de interacciones de usuarios.
     */
    @PreAuthorize("hasAuthority('user-interaction:read-all')")
    @GetMapping
    public ResponseEntity<Page<GetUserInteractionLogDto>> findAll(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit
    ){
        Pageable pageable = buildPageable(offset, limit);
        return ResponseEntity.ok(userInteractionLogService.findAll(pageable));
    }

    /**
     * Endpoint para buscar registros de interacciones por nombre de usuario.
     *
     * @param username Nombre de usuario por el cual se desean buscar las interacciones.
     * @param offset   Valor que representa el desplazamiento de la paginación (por defecto, 0).
     * @param limit    Cantidad máxima de resultados por página (por defecto, 20).
     * @return ResponseEntity que contiene una página de registros de interacciones de usuarios filtrados por nombre de usuario.
     */
    @PreAuthorize("hasAuthority('user-interaction:read-by-username') || (@interactionLogValidator.validate(#username) && hasAuthority('user-interaction:read-my-interactions') )")
    @GetMapping("/{username}")
    public ResponseEntity<Page<GetUserInteractionLogDto>> findByUsername(
            @PathVariable String username,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "20") int limit
    ){
        Pageable pageable = buildPageable(offset, limit);
        return ResponseEntity.ok(userInteractionLogService.findByUsername(pageable, username));
    }

    /**
     * Método estático para construir un objeto Pageable a partir de los valores de offset y limit.
     *
     * @param offset Valor que representa el desplazamiento de la paginación.
     * @param limit  Cantidad máxima de resultados por página.
     * @return Objeto Pageable configurado con los valores de offset y limit.
     * @throws IllegalArgumentException si offset es menor que cero o si limit es menor o igual a cero.
     */
    private static Pageable buildPageable(int offset, int limit) {
        Pageable pageable;

        if(offset < 0 ){
            throw new IllegalArgumentException("El atributo offset no puede ser menor a cero");
        }

        if(limit <= 0){
            throw new IllegalArgumentException("El atributo limit no puede ser menor o igual a cero");
        }

        if(offset == 0) {
            pageable = PageRequest.of(0, limit);
        } else {
            pageable = PageRequest.of(offset / limit, limit);
        }

        return pageable;
    }
}

