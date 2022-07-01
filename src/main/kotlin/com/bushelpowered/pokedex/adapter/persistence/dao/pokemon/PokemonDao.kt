package com.bushelpowered.pokedex.adapter.persistence.dao.pokemon

import com.bushelpowered.pokedex.adapter.persistence.entities.type.Type
import com.bushelpowered.pokedex.adapter.persistence.entities.pokemon.Pokemon
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository


@Repository
interface PokemonDao : PagingAndSortingRepository<Pokemon, String> {

    fun findByIdIn(pokeIdList: List<String>, page: PageRequest): Page<Pokemon>

    fun findByNameContaining(name: String, page: PageRequest): Page<Pokemon>

    fun findPokemonByTypeIn(type: List<Type>): List<Pokemon>

}