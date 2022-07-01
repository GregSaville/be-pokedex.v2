package com.bushelpowered.pokedex.core.ingress.pokemon

import com.bushelpowered.pokedex.adapter.persistence.entities.pokemon.Pokemon
import com.bushelpowered.pokedex.core.domain.model.pokemon.PokemonModel
import org.springframework.data.domain.Page

interface FindPokemonUseCase :
    FindByPage,
    FindById,
    FindByName,
    FindByType,
    FindByTypes,
    FindWithType

fun interface FindByPage {
    fun findByPage(page: Int): Map<String, Any>
}

fun interface FindById {
    fun findById(id: String): PokemonModel?
}

fun interface FindByName {
    fun findByName(name: String, page: Int): Map<String, Any>
}

fun interface FindByType {
    fun findByType(type: List<String>, page: Int): Map<String, Any>?
}

fun interface FindByTypes {
    fun findByTypes(types: List<String>, page: Int): Map<String, Any>?
}

fun interface FindWithType {
    fun findWithType(type: List<String>, page: Int): Map<String, Any>?
}


