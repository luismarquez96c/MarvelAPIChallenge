package com.test.api.marvelchallenge.web.controller;

import com.test.api.marvelchallenge.dto.MyPageable;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.CharacterDto;
import com.test.api.marvelchallenge.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase controladora que gestiona las operaciones relacionadas con los personajes (characters) de Marvel.
 * Proporciona endpoints para buscar personajes y obtener detalles de un personaje específico.
 */
@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    /**
     * Endpoint para buscar personajes de Marvel basados en varios criterios de búsqueda.
     *
     * @param name   Nombre del personaje a buscar (opcional).
     * @param comics Lista de IDs de cómics relacionados con el personaje (opcional).
     * @param series Lista de IDs de series relacionadas con el personaje (opcional).
     * @param offset Valor que representa el desplazamiento de la paginación (por defecto, 0).
     * @param limit  Cantidad máxima de resultados por página (por defecto, 10).
     * @return ResponseEntity que contiene una lista de personajes que coinciden con los criterios de búsqueda.
     */
    @PreAuthorize("hasAuthority('character:read-all')")
    @GetMapping
    public ResponseEntity<List<CharacterDto>> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int[] comics,
            @RequestParam(required = false) int[] series,
            @RequestParam(defaultValue = "0") long offset,
            @RequestParam(defaultValue = "10") long limit
    )
    {
        MyPageable pageable = new MyPageable(offset, limit);
        return ResponseEntity.ok(characterService.findAll(pageable, name, comics, series));
    }

    /**
     * Endpoint para obtener detalles de un personaje específico de Marvel.
     *
     * @param characterId ID del personaje cuyos detalles se desean obtener.
     * @return ResponseEntity que contiene los detalles del personaje especificado.
     */
    @PreAuthorize("hasAuthority('character:read-detail')")
    @GetMapping("/{characterId}")
    public ResponseEntity<CharacterDto.CharacterInfoDto> findInfoById(@PathVariable Long characterId){
        return ResponseEntity.ok(characterService.findInfoById(characterId));
    }
}

