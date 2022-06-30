package com.bushelpowered.pokedex.adapter.persistence.repository.pokemon

import com.bushelpowered.pokedex.adapter.persistence.dao.pokemon.PokemonDao
import com.bushelpowered.pokedex.adapter.persistence.entities.pokemon.Pokemon
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.data.domain.PageRequest

internal class PokemonRepositoryPortTest {

    private val pokemonDao = mockk<PokemonDao>(relaxed = true)
    private val page = PageRequest.of(1,15)

    @Test
    fun `calls dao to find a page of pokemon`() {
        assertNotNull( pokemonDao.findAll() )
        verify { pokemonDao.findAll() }
    }

    @Test
    fun `calls dao to return a page of pokemon by id's`() {
        assertNotNull(pokemonDao.findByIdIn(listOf("1"),page))
        verify { pokemonDao.findByIdIn(any(), any()) }
    }

    @Test
    fun `calls dao to return a page of pokemon by a specific name`() {
        assertNotNull(pokemonDao.findByNameContaining("char",page))
        verify { pokemonDao.findByNameContaining(any(), any()) }
    }

    @Test
    fun `calls dao to return a list of pokemon by a specific type`() {
        assertNotNull(pokemonDao.findPokemonByTypeIn(listOf()))
        verify { pokemonDao.findPokemonByTypeIn(any()) }
    }

    @Test
    fun `calls dao to return a pokemon by a specific id`() {
        assertNotNull(pokemonDao.findById("1"))
        verify { pokemonDao.findById(any()) }
    }
}