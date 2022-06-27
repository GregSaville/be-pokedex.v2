package com.bushelpowered.pokedex.core.service.pokemon

import com.bushelpowered.pokedex.core.egress.pokemon.PokemonPort
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.springframework.data.domain.PageRequest


internal class PokemonServiceTest {

    private val pokemonPort = mockk<PokemonPort>(relaxed = true)

    @Test
    fun `finds a page of pokemon`(){
        val result = pokemonPort.findAll(PageRequest.of(1, 15))
        assertNotNull(result)
    }

    @Test
    fun `finds a pokemon by id`() {
        val result = pokemonPort.findById("1")
        assertNotNull(result)
    }

}