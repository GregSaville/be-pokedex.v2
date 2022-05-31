package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.service.entities.Pokemon
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import java.util.Optional


@Service
@Primary
class PokemonService(val db: PokemonRepository){
    fun findAllPokemon(): MutableIterable<Pokemon> = db.findAll()

    fun findPokemonById(id: String): Optional<Pokemon> = db.findById(id)
    fun findPokemonByIds(ids: List<String>) : MutableIterable<Pokemon> = db.findAllById(ids)
}