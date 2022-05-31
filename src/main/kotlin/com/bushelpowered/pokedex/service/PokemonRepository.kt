package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.service.entities.Pokemon
import org.springframework.data.repository.CrudRepository

interface PokemonRepository : CrudRepository<Pokemon, String>

