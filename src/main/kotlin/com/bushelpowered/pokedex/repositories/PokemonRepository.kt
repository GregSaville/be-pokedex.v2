package com.bushelpowered.pokedex.repositories

import com.bushelpowered.pokedex.entities.Pokemon
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonRepository : JpaRepository<Pokemon, String>

