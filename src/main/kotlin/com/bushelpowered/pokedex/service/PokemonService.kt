package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.entities.Pokemon
import com.bushelpowered.pokedex.repositories.PokemonRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service


@Service
class PokemonService(private val pokemonRepository: PokemonRepository) {

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

}
