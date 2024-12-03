package com.pokemonapi.pokemon.service;

import com.pokemonapi.pokemon.dto.PokemonDto;

import java.util.List;

public interface IPokemonService {
    PokemonDto createPokemon(PokemonDto pokemonDto);
    List<PokemonDto> getAllPokemons();
    PokemonDto getPokemonById(Long id);
    PokemonDto updatePokemon(PokemonDto pokemonDto, Long id);
    void deletePokemonById(Long id);
}
