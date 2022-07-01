package com.bushelpowered.pokedex.adapter.persistence.dao.csv

import com.bushelpowered.pokedex.adapter.persistence.entities.csv.PokemonEggGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PokemonEggGroupDao : JpaRepository<PokemonEggGroup, String>