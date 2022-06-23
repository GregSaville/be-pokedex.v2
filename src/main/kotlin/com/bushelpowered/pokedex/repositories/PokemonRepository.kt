package com.bushelpowered.pokedex.repositories

import com.bushelpowered.pokedex.entities.Pokemon
import com.bushelpowered.pokedex.entities.Type
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.PagingAndSortingRepository

interface PokemonRepository : PagingAndSortingRepository<Pokemon, String> {

    fun findByIdIn(pokeIdList : List<String>, page: PageRequest) : Page<Pokemon>

    fun findByNameContaining(name: String, page: PageRequest): Page<Pokemon>

    fun findPokemonByTypeIn(type: List<Type>): List<Pokemon>

}

