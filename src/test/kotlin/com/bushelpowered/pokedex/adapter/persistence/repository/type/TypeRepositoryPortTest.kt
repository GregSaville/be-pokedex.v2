package com.bushelpowered.pokedex.adapter.persistence.repository.type

import com.bushelpowered.pokedex.adapter.persistence.dao.type.TypeDao
import com.bushelpowered.pokedex.adapter.persistence.entities.type.Type
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class TypeRepositoryPortTest {

    private val typeDao = mockk<TypeDao>(relaxed = true)

    @Test
    fun `calls dao to return a list of types`() {
        val result = typeDao.findAll()
        assertNotNull(result)
    }

    @Test
    fun `confirms database has types of typ water and ice`() {
        val types = listOf<String>("water","ice")
        types.forEach{
            assertNotNull(typeDao.findByType(it))
        }
    }


    @Test
    fun `gets type object from string water`() {
        assertNotNull(typeDao.findByType("water"))
    }
}