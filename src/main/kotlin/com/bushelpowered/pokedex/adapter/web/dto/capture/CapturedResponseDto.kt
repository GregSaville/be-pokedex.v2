package com.bushelpowered.pokedex.adapter.web.dto.capture

import com.bushelpowered.pokedex.core.domain.Pokemon

data class CapturedResponseDto(
    val id: Long,
    val name: String,
    val email: String,
    val captured: List<Pokemon>
)
