package com.pokemonapi.pokemon.service;


import com.pokemonapi.pokemon.dto.PokemonDto;
import com.pokemonapi.pokemon.exceptions.PokemonNotFoundException;
import com.pokemonapi.pokemon.models.Pokemon;
import com.pokemonapi.pokemon.repositories.IPokemonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
//@AllArgsConstructor no needed for autowirind constructor
public class PokemonService implements IPokemonService {

  private final IPokemonRepository pokemonRepository;

  @Autowired
  public PokemonService(IPokemonRepository pokemonRepository) {
    this.pokemonRepository = pokemonRepository;
  }


  @Override
  //public PokemonDto getAllPokemons() {
   public List<PokemonDto> getAllPokemons(){
    //return pokemonRepository.findAll(); //for List<Pokemon>
    //Getting data from DB, so it has to be same type
    List<Pokemon> pokemon = pokemonRepository.findAll(); //returns pokemons, no DTO
    return pokemon.stream().map(this::mapToPokemonDto).collect(Collectors.toList());

  }

  @Override
  public PokemonDto getPokemonById(Long id) {
    //  return pokemonRepository.getReferenceById(id);  //for public Pokemon
    Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(()-> new PokemonNotFoundException("Pokemon does not exist"));
    return mapToPokemonDto(pokemon);
  }

  @Override
  public PokemonDto updatePokemon(PokemonDto pokemonDto, Long id) {
    Pokemon pokemon = pokemonRepository.findById(id)
            .orElseThrow(() -> new PokemonNotFoundException("Pokemon with ID " + id + " does not exist, impossible to update"));

    // Update Pokemon fields from DTO
    pokemon.setName(pokemonDto.getName());
    pokemon.setType(pokemonDto.getType());

    // Save updated Pokemon in the database
    Pokemon updatedPokemon = pokemonRepository.save(pokemon);

    // Convert the updated Pokemon entity back to DTO and return it
    return mapToPokemonDto(updatedPokemon);
  }

  @Override
  public void deletePokemonById(Long id) {
    Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(()-> new PokemonNotFoundException("Pokemon with ID " + id + " does not exist, Impossible to delete"));
    pokemonRepository.delete(pokemon);
  }


  @Override
  public PokemonDto createPokemon(PokemonDto pokemonDto) {
    //Optional<Pokemon> existingPokemon = pokemonRepository.findByName(((pokemonDto.getName());
/*
    if (existingPokemon.isPresent()) {
      // If Pokemon already exists, throw an exception or return an error message
      throw new IllegalArgumentException("Pokemon with name '" + pokemonDto.getName() + "' already exists.");
    }*/

    Pokemon pokemon = new Pokemon();
    pokemon.setName(pokemonDto.getName());
    pokemon.setType(pokemonDto.getType());

    Pokemon newPokemon = pokemonRepository.save(pokemon);

    PokemonDto pokemonResponse = new PokemonDto();
    pokemonResponse.setId(newPokemon.getId());
    pokemonResponse.setName(newPokemon.getName());
    pokemonResponse.setType(newPokemon.getType());
    return pokemonResponse;
  }




  //public void addPokemon(Pokemon pokemon) {
    /*
 Optional<Pokemon> existingPokemon = pokemonRepository.findByName(pokemon.getName());

    if (existingPokemon.isPresent()) {
        // If Pokemon already exists, throw an exception or return an error message
        throw new IllegalArgumentException("Pokemon with name '" + pokemon.getName() + "' already exists.");
    }
     */
    // Check if Pokemon with the same name already exists
   /* if (pokemonRepository.existsByName(pokemon.getName())) {
      // If Pokemon already exists, throw an exception or return an error message
      throw new IllegalArgumentException("Pokemon with name '" + pokemon.getName() + "' already exists.");
    }
    // If it doesn't exist, save the new Pokemon
   pokemonRepository.save(pokemon);


  }*/

  public void deletePokemon(Long pokemonId) {
    //if id not in repository
    if(!pokemonRepository.existsById(pokemonId)) {
      //throw execption, not found
      throw new PokemonNotFoundException("pokemon with id " + pokemonId + " not found");
    }
    pokemonRepository.deleteById(pokemonId);
  }
/*
  @Transactional
  public void deletePokemonByName(String name) {
    if(!pokemonRepository.existsByName(name)) {
      throw new PokemonNotFoundException("pokemon with name " + name + " not found");
    }
    pokemonRepository.deleteByName(name);
  }
*/

 //Map DTO to Pokemon
  private PokemonDto mapToPokemonDto(Pokemon pokemon) {
    PokemonDto pokemonDto = new PokemonDto();
    pokemonDto.setId(pokemon.getId());
    pokemonDto.setName(pokemon.getName());
    pokemonDto.setType(pokemon.getType());
    return pokemonDto;
  }

  //Map Pokemon to DTO
  private Pokemon mapToEntity(PokemonDto pokemonDto) {
    Pokemon pokemon = new Pokemon();
    pokemon.setName(pokemonDto.getName());
    pokemon.setType(pokemonDto.getType());
    return pokemon;
  }

}
