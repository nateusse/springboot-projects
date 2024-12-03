package com.pokemonapi.pokemon.repositories;

import com.pokemonapi.pokemon.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IPokemonRepository extends JpaRepository<Pokemon, Long> {


    //Optional<Pokemon> findByName(String name);
    // Optional maybe Pokemon exist, empty if not name found

    boolean existsByName(String name); // checks if name exists, no returns

    @Modifying
    @Query("DELETE FROM Pokemon p WHERE p.name = :name")
    void deleteByName(String name);


    Optional<Pokemon> findByName(String name);
}
