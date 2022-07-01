package com.bushelpowered.pokedex.core.service.trainer

import com.bushelpowered.pokedex.adapter.persistence.entities.trainer.Trainer
import com.bushelpowered.pokedex.adapter.web.dto.trainer.TrainerRequestDto
import com.bushelpowered.pokedex.core.egress.trainer.TrainerPort
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class TrainerServiceTest {

    private val trainerPortRelaxed = mockk<TrainerPort>(relaxed = true)
    private val trainerPortUnrelaxed = mockk<TrainerPort>(relaxed = false)

    @Test
    fun `calls port and returns a list of trainers`() {
        val result = trainerPortRelaxed.findAllTrainers()
        assertNotNull(result)
    }

    @Test
    fun `calls port and returns a trainer with a specific id`() {

        val expected = Trainer(1,"test", "test@Test.com", "passWord4", listOf())
        every { trainerPortUnrelaxed.findTrainerById(1) } returns expected

        val result = trainerPortUnrelaxed.findTrainerById(1)

        verify { trainerPortUnrelaxed.findTrainerById(any()) }
        assertEquals(expected, result)

    }

    @Test
    fun `calls port to add a new trainer`() {
        val trainerToAdd = TrainerRequestDto("test", "test@Test.com", "passWord4")
        every { trainerPortUnrelaxed.addTrainer(trainerToAdd) } returns true

        val result = trainerPortUnrelaxed.addTrainer(trainerToAdd)
        verify { trainerPortUnrelaxed.addTrainer(any())}
        assert(result)
    }

    @Test
    fun `calls port to remove trainer`() {
        every { trainerPortUnrelaxed.removeTrainer(1)} returns true

        val result = trainerPortUnrelaxed.removeTrainer(1)
        verify { trainerPortUnrelaxed.removeTrainer(1) }
        assert(result)
    }
}