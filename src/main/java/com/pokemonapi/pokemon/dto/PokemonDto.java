package com.pokemonapi.pokemon.dto;

import com.pokemonapi.pokemon.models.Type;
import lombok.Data;

import java.util.List;


@Data
public class PokemonDto {
    private Long id;
    private String name;
    private List<Type> type;
}
