package com.bushelpowered.pokedex.adapter.web.dto.capture

import com.bushelpowered.pokedex.adapter.persistence.entities.pokemon.Pokemon

data class CapturedResponseDto(
    val id: Long,
    val name: String,
    val email: String,
    val captured: List<Pokemon>
)
