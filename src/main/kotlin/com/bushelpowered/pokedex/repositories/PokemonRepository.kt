package com.bushelpowered.pokedex.repositories

import com.bushelpowered.pokedex.entities.Pokemon
import com.bushelpowered.pokedex.entities.Type
import org.springframework.data.domain.Page
import org.springframework.data.repository.PagingAndSortingRepository

interface PokemonRepository : PagingAndSortingRepository<Pokemon, String> {

    fun findByNameContaining(name: String): List<Pokemon>

    fun findByType(type: Type): List<Pokemon>

}

