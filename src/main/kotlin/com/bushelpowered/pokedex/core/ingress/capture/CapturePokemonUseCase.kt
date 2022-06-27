package com.bushelpowered.pokedex.core.ingress.capture

import com.bushelpowered.pokedex.adapter.web.dto.capture.CapturedResponseDto

interface CapturePokemonUseCase :
    CapturePokemon

fun interface CapturePokemon {
    fun capturePokemon(pokeId: String, trainerId: Long): CapturedResponseDto?
}