package com.bushelpowered.pokedex.core.ingress.capture

import com.bushelpowered.pokedex.adapter.persistence.entities.capture.Captured

interface CapturePokemonUseCase :
    CapturePokemon

fun interface CapturePokemon {
    fun capturePokemon(pokeId: String, trainerId: Long): Captured?
}