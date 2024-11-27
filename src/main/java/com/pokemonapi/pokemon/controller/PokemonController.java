package com.pokemonapi.pokemon.controller;

import com.pokemonapi.pokemon.models.Pokemon;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemon")
public class PokemonController {


    @GetMapping()
    public ResponseEntity<List<Pokemon>> getAllPokemons() {
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(1L,"Charizard", "Fire"));
        pokemons.add(new Pokemon(2L,"Chicorita", "Herb"));
        pokemons.add(new Pokemon(3L,"Squirtle", "Water"));
        return ResponseEntity.ok(pokemons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable Long id) {
        Pokemon pokemon = new Pokemon(id, "Charizard", "Fire");
        return ResponseEntity.ok(pokemon); // Wrap Pokemon in a ResponseEntity
    }

    /*
    @GetMapping("pokemon/{id}")
    public Pokemon getPokemonById(@PathVariable Long id) {
        return new Pokemon(id, "Charizard", "Fire");
    }

}

     */

}