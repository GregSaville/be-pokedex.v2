package com.bushelpowered.pokedex.core.egress.pokemon

import com.bushelpowered.pokedex.adapter.persistence.entities.type.Type
import com.bushelpowered.pokedex.core.domain.Pokemon
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest


interface PokemonPort :
    FindPage,
    FindByIdIn,
    FindByNameContaining,
    FindPokemonByTypeIn,
    FindById

fun interface FindPage {
    fun findAll(pageRequest: PageRequest): Page<Pokemon>
}

fun interface FindById {
    fun findById(id: String): Pokemon?
}

fun interface FindByIdIn {
    fun findByIdIn(pokeIdList: List<String>, page: PageRequest): Page<Pokemon>
}

fun interface FindByNameContaining {
    fun findByNameContaining(name: String, page: PageRequest): Page<Pokemon>
}

fun interface FindPokemonByTypeIn {
    fun findPokemonByTypeIn(type: List<Type>): List<Pokemon>
}