package com.bushelpowered.pokedex.adapter.web.dto

import com.bushelpowered.pokedex.core.domain.Pokemon

data class CapturedResponseDTO(
    val id: Long,
    val name: String,
    val email: String,
    val captured: List<Pokemon>
)
