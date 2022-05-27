package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.service.dto.Pokemon
import org.springframework.stereotype.Service
import java.util.Optional


@Service
class PokemonService(val db: PokemonRepository){

    fun findAllPokemon(): MutableIterable<Pokemon> = db.findAll()

    fun findPokemonById(id: String): Optional<Pokemon> = db.findById(id)

}