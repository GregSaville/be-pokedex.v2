package com.bushelpowered.pokedex.repositories

import com.bushelpowered.pokedex.entities.Pokemon
import com.bushelpowered.pokedex.entities.Trainer
import com.fasterxml.jackson.databind.util.JSONPObject
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PokemonRepository : JpaRepository<Pokemon, String>{
    fun findByName(name: String): Optional<Pokemon>
    fun findByType(types : String) : MutableList<Optional<Pokemon>>
}

