package com.bushelpowered.pokedex.adapter.persistence.repository

import com.bushelpowered.pokedex.adapter.persistence.entities.PokemonAbilities
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PokemonAbilitiesRepository : JpaRepository<PokemonAbilities, String> {
}