package com.bushelpowered.pokedex.core.domain

data class TrainerModel(
    val id: Long,
    val name: String,
    val email: String,
    private val password: String,
    val capturedPokemon: List<Pokemon>
)