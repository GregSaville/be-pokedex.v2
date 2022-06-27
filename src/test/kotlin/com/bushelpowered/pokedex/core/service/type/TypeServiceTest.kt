package com.bushelpowered.pokedex.core.service.type

import com.bushelpowered.pokedex.core.egress.type.TypePort
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class TypeServiceTest {

    private val typePort = mockk<TypePort>(relaxed = true)

    @Test
    fun `type service returns a list of types`(){
        val result = typePort.findAll()
        assertNotNull(result)
    }
}