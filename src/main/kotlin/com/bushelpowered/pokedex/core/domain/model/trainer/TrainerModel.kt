package com.bushelpowered.pokedex.core.domain.model.trainer

import com.bushelpowered.pokedex.adapter.persistence.entities.pokemon.Pokemon

data class TrainerModel(
    val id: Long,
    val name: String,
    val email: String,
    private val password: String,
    val capturedPokemon: List<Pokemon>
)