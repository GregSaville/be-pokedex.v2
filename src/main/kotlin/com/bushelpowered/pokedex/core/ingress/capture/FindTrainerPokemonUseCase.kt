package com.bushelpowered.pokedex.core.ingress.capture

import com.bushelpowered.pokedex.adapter.web.dto.CapturedResponseDTO

interface FindTrainerPokemonUseCase : FindPokemon

fun interface FindPokemon{
    fun findCapturedPokemon(trainerId: Long) : CapturedResponseDTO?
}