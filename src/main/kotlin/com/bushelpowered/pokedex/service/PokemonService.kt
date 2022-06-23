package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.entities.Pokemon
import com.bushelpowered.pokedex.entities.Type
import com.bushelpowered.pokedex.repositories.PokemonRepository
import com.bushelpowered.pokedex.repositories.TypeRepository
import com.fasterxml.jackson.databind.util.JSONPObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service


@Service
class PokemonService(
    private val pokemonRepository: PokemonRepository,
    private val typeRepository: TypeRepository
) {

    fun findPokemonByPage(page: Int): Page<Pokemon> {
        return pokemonRepository.findAll(PageRequest.of(page, 15))
    }

    fun findPokemonById(id: String): Pokemon? {
        return if (pokemonRepository.findById(id).isPresent) {
            pokemonRepository.findById(id).get()
        } else null
    }

    fun findPokemonByName(name: String, page: Int): Page<Pokemon> {
        return pokemonRepository.findByNameContaining(name, PageRequest.of(page, 15))

    }

    fun findPokemonByType(types: List<String>, page: Int): Page<Pokemon>? {
        val pokeList = mutableListOf<String>()
        return if (validateTypes(listOf(types[0].dropLast(1)))) {
            val result = pokemonRepository.findPokemonByTypeIn(listOf(getType(types[0].dropLast(1))))
            result.forEach {
                if (it.type.size == 1) {
                    pokeList.add(it.id)
                }
            }
            pokemonRepository.findByIdIn(pokeList.toList(), PageRequest.of(page, 15))
        } else null
    }

    fun findPokemonByTypes(types: List<String>, page: Int): Page<Pokemon>? {
        val typeOneList = mutableListOf<String>()
        val pokeList = mutableListOf<String>()
        return if (validateTypes(types)) {
            var result = pokemonRepository.findPokemonByTypeIn(listOf(getType(types[0])))
            result.forEach {
                typeOneList.add(it.id)
            }
            result = pokemonRepository.findPokemonByTypeIn(listOf(getType(types[1])))
            result.forEach {
                if (typeOneList.contains(it.id)) {
                    pokeList.add(it.id)
                }
            }
            pokemonRepository.findByIdIn(pokeList.toList(), PageRequest.of(page, 15))
        } else null
    }

    fun findPokemonWithType(types: List<String>, page: Int): Page<Pokemon>? {
        val pokeList = mutableListOf<String>()
        return if (validateTypes(types)) {
            val result = pokemonRepository.findPokemonByTypeIn(listOf(getType(types[0])))
            result.forEach {
                pokeList.add(it.id)
            }
            pokemonRepository.findByIdIn(pokeList.toList(), PageRequest.of(page, 15))
        } else null
    }

    private fun validateTypes(types: List<String>): Boolean {
        types.forEach {
            if (typeRepository.findByType(it) == null) {
                return false
            }
        }
        return true
    }

    private fun getType(type: String): Type {
        return typeRepository.findByType(type)!!
    }

}
