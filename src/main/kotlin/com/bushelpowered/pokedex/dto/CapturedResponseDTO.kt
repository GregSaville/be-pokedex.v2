package com.bushelpowered.pokedex.dto

import com.bushelpowered.pokedex.entities.Pokemon

data class CapturedResponseDTO(
    val id: Long,
    val name: String,
    val email: String,
    val captured: List<Pokemon>
)
