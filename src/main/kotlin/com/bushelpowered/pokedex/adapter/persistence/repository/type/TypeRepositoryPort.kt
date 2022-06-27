package com.bushelpowered.pokedex.adapter.persistence.repository.type

import com.bushelpowered.pokedex.adapter.persistence.entities.type.Type
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TypeRepositoryPort : JpaRepository<Type, String> {
    fun findByType(type: String): Type?
}