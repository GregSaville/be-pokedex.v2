package com.bushelpowered.pokedex.core.ingress.pokemon

import com.bushelpowered.pokedex.adapter.persistence.entities.pokemon.Pokemon
import org.springframework.data.domain.Page

interface FindPokemonUseCase :
    FindByPage,
    FindById,
    FindByName,
    FindByType,
    FindByTypes,
    FindWithType

fun interface FindByPage {
    fun findByPage(page: Int): Page<Pokemon>
}

fun interface FindById {
    fun findById(id: String): Pokemon?
}

fun interface FindByName {
    fun findByName(name: String, page: Int): Page<Pokemon>
}

fun interface FindByType {
    fun findByType(type: List<String>, page: Int): Page<Pokemon>?
}

fun interface FindByTypes {
    fun findByTypes(types: List<String>, page: Int): Page<Pokemon>?
}

fun interface FindWithType {
    fun findWithType(type: List<String>, page: Int): Page<Pokemon>?
}


