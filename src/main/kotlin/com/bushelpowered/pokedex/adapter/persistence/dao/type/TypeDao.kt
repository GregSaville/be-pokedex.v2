package com.bushelpowered.pokedex.adapter.persistence.dao.type

import com.bushelpowered.pokedex.adapter.persistence.repository.type.TypeRepositoryPort
import com.bushelpowered.pokedex.adapter.persistence.entities.type.Type
import com.bushelpowered.pokedex.core.egress.type.TypePort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository


@Repository
interface TypeDao : JpaRepository<Type, String> {

  fun findByType(type : String) : Type?

}