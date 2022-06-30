package com.bushelpowered.pokedex.adapter.web.controller.trainer

import com.bushelpowered.pokedex.adapter.web.dto.trainer.TrainerRequestDto
import com.bushelpowered.pokedex.core.domain.model.trainer.TrainerModel
import com.bushelpowered.pokedex.core.ingress.trainer.CrudTrainerUseCase
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class TrainerControllerTest {

    private val trainerUseCase = mockk<CrudTrainerUseCase>(relaxed = true)

    @Test
    fun `calls use case to get all trainers`() {
        trainerUseCase.getAllTrainers()
        verify { trainerUseCase.getAllTrainers() }
    }

    @Test
    fun `calls use case to get a trainer by an id`() {
        trainerUseCase.getById(1)
        verify { trainerUseCase.getById(any()) }
    }

    @Test
    fun `calls use case to add a new trainer`() {
        val fakeTrainerRequest = TrainerRequestDto(
            "fake",
            "fake@fake.com",
            "fakePassword")
        trainerUseCase.addTrainer(fakeTrainerRequest)
        verify { trainerUseCase.addTrainer(any()) }
    }

    @Test
    fun `calls use case to remove a trainer`() {
        val currentTrainer = trainerUseCase.getById(1)
        verify { trainerUseCase.getById(any()) }

        trainerUseCase.removeTrainer(currentTrainer!!.id)
        verify { trainerUseCase.removeTrainer(any()) }

    }
}