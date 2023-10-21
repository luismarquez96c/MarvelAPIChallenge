package com.test.api.marvelchallenge.service.impl;

import com.test.api.marvelchallenge.dto.MyPageable;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.CharacterDto;
import com.test.api.marvelchallenge.persistence.integration.marvel.repository.CharacterRepository;
import com.test.api.marvelchallenge.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * El servicio `CharacterServiceImpl` proporciona métodos para interactuar con personajes de marvel.
 */
@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    /**
     * Recupera una lista de personajes según los parámetros de paginación, nombre y filtros de cómics y series.
     *
     * @param pageable Parámetros de paginación, como el offset y el límite.
     * @param name     El nombre del personaje o parte del nombre.
     * @param comics   Un arreglo de IDs de cómics que se utilizan como filtro.
     * @param series   Un arreglo de IDs de series que se utilizan como filtro.
     * @return Una lista de personajes que coinciden con los criterios especificados.
     */
    @Override
    public List<CharacterDto> findAll(MyPageable pageable, String name, int[] comics, int[] series) {
        return characterRepository.findAll(pageable, name, comics, series);
    }

    /**
     * Recupera información detallada de un personaje por su ID.
     *
     * @param characterId El ID del personaje que se desea recuperar.
     * @return Información detallada del personaje correspondiente al ID proporcionado.
     */
    @Override
    public CharacterDto.CharacterInfoDto findInfoById(Long characterId) {
        return characterRepository.findInfoById(characterId);
    }

}

