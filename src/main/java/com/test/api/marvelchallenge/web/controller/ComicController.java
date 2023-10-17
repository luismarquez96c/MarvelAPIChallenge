package com.test.api.marvelchallenge.web.controller;

import com.test.api.marvelchallenge.dto.MyPageable;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.ComicDto;
import com.test.api.marvelchallenge.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase controladora que gestiona las operaciones relacionadas con los cómics (comics) de Marvel.
 * Proporciona endpoints para buscar cómics y obtener detalles de un cómic específico.
 */
@RestController
@RequestMapping("/comics")
public class ComicController {

    @Autowired
    private ComicService comicService;

    /**
     * Endpoint para buscar cómics de Marvel relacionados con un personaje específico.
     *
     * @param characterId ID del personaje cuyos cómics se desean buscar.
     * @param offset      Valor que representa el desplazamiento de la paginación (por defecto, 0).
     * @param limit       Cantidad máxima de resultados por página (por defecto, 10).
     * @return ResponseEntity que contiene una lista de cómics relacionados con el personaje especificado.
     */
    @PreAuthorize("hasAuthority('comic:read-all')")
    @GetMapping
    public ResponseEntity<List<ComicDto>> findAll(
            @RequestParam(required = false) Long characterId,
            @RequestParam(defaultValue = "0") long offset,
            @RequestParam(defaultValue = "10") long limit
    )
    {
        MyPageable pageable = new MyPageable(offset, limit);
        return ResponseEntity.ok(comicService.findAll(pageable, characterId));
    }

    /**
     * Endpoint para obtener detalles de un cómic específico de Marvel.
     *
     * @param comicId ID del cómic cuyos detalles se desean obtener.
     * @return ResponseEntity que contiene los detalles del cómic especificado.
     */
    @PreAuthorize("hasAuthority('comic:read-by-id')")
    @GetMapping("/{comicId}")
    public ResponseEntity<ComicDto> findById(
            @PathVariable Long comicId
    )
    {
        return ResponseEntity.ok(comicService.findById(comicId));
    }
}

