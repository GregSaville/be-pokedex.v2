package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.entities.Pokemon
import com.bushelpowered.pokedex.repositories.PokemonRepository
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import java.util.Optional


@Service
@Primary
class PokemonService(val db: PokemonRepository){
    fun findAllPokemon(): MutableIterable<Pokemon> = db.findAll()

    fun findPokemonByName(name: String): Optional<Pokemon> = db.findByName(name)

    fun findPokemonById(id: String): Optional<Pokemon> = db.findById(id)
    fun findPokemonByIds(ids: List<String>) : MutableIterable<Pokemon> = db.findAllById(ids)
}