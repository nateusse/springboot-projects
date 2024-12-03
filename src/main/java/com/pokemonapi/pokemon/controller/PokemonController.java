package com.pokemonapi.pokemon.controller;

import com.pokemonapi.pokemon.dto.PokemonDto;
import com.pokemonapi.pokemon.exceptions.PokemonNotFoundException;
import com.pokemonapi.pokemon.models.Pokemon;
import com.pokemonapi.pokemon.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.tomcat.util.net.openssl.OpenSSLStatus.getName;

@RestController
@RequestMapping("/api/v1/pokemon")
//@AllArgsConstructor // for   private final PokemonService pokemonService;
public class PokemonController {

    //@Autowired no for easy testing
    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService){
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public ResponseEntity<List<PokemonDto>> getPokemons() {
        //getAllPokemon using Service instance
        return new ResponseEntity<>(pokemonService.getAllPokemons(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonDto> pokemonDetail(@PathVariable Long id) {
           // return new ResponseEntity<>(pokemonService.getPokemonById(id), HttpStatus.OK);
        return ResponseEntity.ok(pokemonService.getPokemonById(id));
    }
        // getAllPokemons factory method List
        /*  public List<Pokemon> getAllPokemon() {
                 List<Pokemon> pokemons = Arrays.asList(
                      new Pokemon(1L,"Charizard", "FIRE"));
                      new Pokemon(2L,"Chicorita", "HERB"));
                  return pokemons; */


        //getAllPokemons with ResponseEntity
        /*@GetMapping()
        public ResponseEntity<List<Pokemon>> getAllPokemon() {
            List<Pokemon> pokemons = new ArrayList<>();
            pokemons.add(new Pokemon(1L,"Charizard", "Fire"));
            pokemons.add(new Pokemon(2L,"Chicorita", "Herb"));
            return ResponseEntity.ok(pokemons);
        }*/


    @PostMapping
    //public void addPokemon(@RequestBody Pokemon pokemon) {
    //public ResponseEntity<Pokemon> addPokemon(@RequestBody Pokemon pokemon) {
    public ResponseEntity<PokemonDto> addPokemon(@RequestBody PokemonDto pokemonDto){
    //public ResponseEntity<String> addPokemon(@RequestBody Pokemon pokemon) {
        //TODO check if pokemon already in db
        System.out.printf("Name: %s, Type: %s%n", pokemonDto.getName(), pokemonDto.getType());
        //pokemonService.addPokemon(pokemon); // for <Pokemon> or also
        //return ResponseEntity.status(HttpStatus.CREATED).body(pokemon); //for <Pokemon>
       //return ResponseEntity.status(HttpStatus.CREATED).body("Pokemon added successfully!"); //For string , poersonalized message
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto), HttpStatus.CREATED);
    }
/*
    @DeleteMapping("delete/{id}")
    public void deletePokemonById(@PathVariable Long id) {
        //TODO check if pokemon exist in Service
        pokemonService.deletePokemonById(id);
    }
*//*
    @DeleteMapping("delete/{name}")
    public ResponseEntity<String> deletePokemonByName(@PathVariable String name) {
        try {
            pokemonService.deletePokemonByName(name);
            return ResponseEntity.ok("Pokemon deleted successfully.");
        } catch (PokemonNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace(); // Log exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

*/


    /*

    // Example with ResponseEntity
    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable Long id) {
        Pokemon pokemon = new Pokemon(id, "Charizard",  Type.FIRE);
        return ResponseEntity.ok(pokemon); // Wrap Pokemon in a ResponseEntity
    }
     */

    /*
    //Example without response entity
    @GetMapping("{id}")
    public Pokemon getPokemonById(@PathVariable Long id) {
        return new Pokemon(id, "Charizard", Type.FIRE);
    }*/



/*
    @PostMapping("/create")
    //@ResponseStatus(HttpStatus.CREATED)  fixed 201 with answer, no customization, no need to add ReponseEntity in the return
    public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon pokemon) {
         // return pokemonService.save(pokemon); //For the @ResponseStatus annotatio
        System.out.println(pokemon.getName());
        System.out.println(pokemon.getType());
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemon);
    }


*/

    @PutMapping("/{id}")
    //public ResponseEntity<Pokemon> updatePokemon(@RequestBody Pokemon pokemon, @PathVariable("id") Long pokemonId) {
    public ResponseEntity<PokemonDto> updatePokemon(@RequestBody PokemonDto pokemonDto, @PathVariable("id") Long pokemonId) {
        PokemonDto response = pokemonService.updatePokemon(pokemonDto, pokemonId);
       // return new ResponseEntity<>(response, HttpStatus.OK);
        System.out.println(pokemonDto.getName());
        System.out.println(pokemonDto.getType());
        //return ResponseEntity.ok(pokemonDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}/delete")  //id can be beofre or after, must match @PathVariable
    public ResponseEntity<String> deletePokemon(@PathVariable("id") Long pokemonId) {
    //public void deletePokemon(@PathVariable("id") Long id){
        pokemonService.deletePokemonById(pokemonId);
        //return ResponseEntity.ok("Pokemon deleted successfully");

       return new ResponseEntity<>("Pokemon delete", HttpStatus.OK);

    }





}