package com.bushelpowered.pokedex.adapter.persistence.repository

import com.bushelpowered.pokedex.adapter.persistence.entities.PokemonModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PokemonModelRepository : JpaRepository<PokemonModel, String>