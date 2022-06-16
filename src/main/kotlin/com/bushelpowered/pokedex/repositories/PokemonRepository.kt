package com.bushelpowered.pokedex.repositories

import com.bushelpowered.pokedex.entities.Pokemon
import org.springframework.data.repository.PagingAndSortingRepository

interface PokemonRepository : PagingAndSortingRepository<Pokemon, String>{
    fun findByNameContaining(name : String) : List<Pokemon>
}

