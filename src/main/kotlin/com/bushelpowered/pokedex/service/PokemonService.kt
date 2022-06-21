package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.entities.Pokemon
import com.bushelpowered.pokedex.repositories.PokemonRepository
import com.bushelpowered.pokedex.repositories.TypeRepository
import com.fasterxml.jackson.databind.util.JSONPObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service


@Service
class PokemonService(private val pokemonRepository: PokemonRepository, private val typeRepository: TypeRepository) {

    fun findPokemonByPage(page: Int): Page<Pokemon> {
        return pokemonRepository.findAll(PageRequest.of(page, 15))
    }

    fun findPokemonById(id: String): Pokemon? {
        return if (pokemonRepository.findById(id).isPresent) {
            pokemonRepository.findById(id).get()
        } else null
    }

    fun findPokemonByName(name: String): HashMap<String, List<Pokemon>> {
        val returnMap = hashMapOf<String, List<Pokemon>>()
        returnMap["content"] = pokemonRepository.findByNameContaining(name).take(15)
        return returnMap
    }

    fun findPokemonByType(types: List<String>, page: Int): HashMap<String, List<Pokemon>> {
        val returnMap = hashMapOf<String, List<Pokemon>>()
        val mutPokemon = mutableListOf<Pokemon>()
        if(types.size == 1 && types[0].last() == '-'){
            val type = types[0].dropLast(1)
            val tmpType = listOf<String>(type)
            if(validateTypes(tmpType)){
                pokemonRepository.findByType(typeRepository.findByType(type)!!).forEach{
                    if( it.type.size == 1) {
                        mutPokemon.add(it)
                    }
                }
               returnMap["content"] = mutPokemon
                return returnMap
            }
        }
        if (validateTypes(types)) {
            pokemonRepository.findByType(typeRepository.findByType(types[0])!!).forEach {
                mutPokemon.add(it)
            }
            if (types.size > 1) {
                val type1 = mutableListOf<Pokemon>()
                type1.addAll(mutPokemon)
                mutPokemon.clear()
                val type2 = pokemonRepository.findByType(typeRepository.findByType(types[1])!!)
                type2.forEach {
                    if (type1.contains(it)) {
                        mutPokemon.add(it)
                    }
                }
            }
        }
        returnMap["content"] = mutPokemon
        return returnMap
    }

    private fun validateTypes(types: List<String>): Boolean {
        types.forEach {
            if (typeRepository.findByType(it) == null) {
                return false
            }
        }
        return true
    }

}
