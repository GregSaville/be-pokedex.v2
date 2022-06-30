package com.bushelpowered.pokedex.core.ingress.capture

import com.bushelpowered.pokedex.adapter.persistence.entities.trainer.Trainer

interface FindTrainerPokemonUseCase : FindPokemon

fun interface FindPokemon {
    fun findCapturedPokemon(trainerId: Long): Trainer?
}