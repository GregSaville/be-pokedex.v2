package com.bushelpowered.pokedex.adapter.web.controller.capture

import com.bushelpowered.pokedex.core.ingress.capture.CapturePokemonUseCase
import com.bushelpowered.pokedex.core.ingress.capture.FindTrainerPokemonUseCase
import com.bushelpowered.pokedex.core.ingress.trainer.CrudTrainerUseCase
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyAll
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CapturedControllerTest {

    private val captureUseCase = mockk<CapturePokemonUseCase>(relaxed = true)
    private val findCapturedUseCase = mockk<FindTrainerPokemonUseCase>(relaxed = true)
    private val trainerUseCase = mockk<CrudTrainerUseCase>(relaxed = true)

    @Test
    fun `calls use cases to capture a pokemon`() {
        trainerUseCase.getById(1)
        captureUseCase.capturePokemon("1",1)

        verify { trainerUseCase.getById(any()) }
        verify { captureUseCase.capturePokemon(any(),any()) }
    }

    @Test
    fun `calls use cases to return a trainer with their captured pokemon`() {
        trainerUseCase.getById(1)
        findCapturedUseCase.findCapturedPokemon(1)

        verify { trainerUseCase.getById(1) }
        verify { findCapturedUseCase.findCapturedPokemon(any()) }
    }
}