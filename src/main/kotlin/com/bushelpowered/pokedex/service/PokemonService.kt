package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.entities.Pokemon
import com.bushelpowered.pokedex.repositories.PokemonRepository
import com.bushelpowered.pokedex.repositories.TypeRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service


@Service
class PokemonService(private val pokemonRepository: PokemonRepository, private val typeRepository: TypeRepository) {

    fun findPokemonByPage(page: Int) : Page<Pokemon> {
       return pokemonRepository.findAll(PageRequest.of(page,15))
    }

    fun findPokemonById(id : String) : Pokemon? {
        return if(pokemonRepository.findById(id).isPresent){
            pokemonRepository.findById(id).get()
        }else null
    }

    fun findPokemonByName(name: String): List<Pokemon> {
        return pokemonRepository.findByNameContaining(name)
    }

    fun findPokemonByType(types: List<String>) : List<Pokemon> {
        val mutPokemon = mutableListOf<Pokemon>()
       if(validateTypes(types)) {
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
            return mutPokemon.toSet().toList()
    }

    private fun validateTypes(types: List<String>): Boolean {
        types.forEach {
            if(typeRepository.findByType(it) == null){
                return false
            }
        }
        return true
    }

}
