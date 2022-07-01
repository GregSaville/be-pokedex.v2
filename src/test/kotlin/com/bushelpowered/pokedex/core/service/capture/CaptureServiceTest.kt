package com.bushelpowered.pokedex.core.service.capture

import com.bushelpowered.pokedex.adapter.persistence.entities.capture.Captured
import com.bushelpowered.pokedex.adapter.persistence.entities.trainer.Trainer
import com.bushelpowered.pokedex.adapter.persistence.repository.capture.CapturedRepositoryPort
import com.bushelpowered.pokedex.core.egress.capture.CapturedPort
import com.bushelpowered.pokedex.core.egress.trainer.TrainerPort
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CaptureServiceTest {

    private val capturedPort = mockk<CapturedPort>(relaxed = true)
    private val trainerPort = mockk<TrainerPort>()
    @Test
    fun `calls port to save an entry`() {
        val fakeCaptured = Captured("1-1",1,"1")
        capturedPort.saveCaptured(fakeCaptured)
        verify { capturedPort.saveCaptured(any()) }
    }

    @Test
    fun `calls port to return a trainer with their captured pokemon`() {
        val expected = Trainer(1,"test","test@test.com","passWord4", listOf())
        every { trainerPort.findTrainerById(1) } returns expected

        val result = trainerPort.findTrainerById(1)

        verify { trainerPort.findTrainerById(any()) }
        assertEquals(expected, result)
    }
}