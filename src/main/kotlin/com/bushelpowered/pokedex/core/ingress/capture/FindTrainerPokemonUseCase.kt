package com.bushelpowered.pokedex.core.ingress.capture

import com.bushelpowered.pokedex.adapter.web.dto.capture.CapturedResponseDto

interface FindTrainerPokemonUseCase : FindPokemon

fun interface FindPokemon {
    fun findCapturedPokemon(trainerId: Long): CapturedResponseDto?
}