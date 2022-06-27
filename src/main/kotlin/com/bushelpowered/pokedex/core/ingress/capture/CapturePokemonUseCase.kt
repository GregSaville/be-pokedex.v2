package com.bushelpowered.pokedex.core.ingress.capture

import com.bushelpowered.pokedex.adapter.web.dto.CapturedResponseDTO

interface CapturePokemonUseCase :
        CapturePokemon

fun interface CapturePokemon{
    fun capturePokemon(pokeId: String, trainerId: Long) : CapturedResponseDTO?
}