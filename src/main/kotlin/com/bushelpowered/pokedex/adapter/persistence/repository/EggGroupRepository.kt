package com.bushelpowered.pokedex.adapter.persistence.repository

import com.bushelpowered.pokedex.adapter.persistence.entities.EggGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EggGroupRepository : JpaRepository<EggGroup, String> {
    fun findByGroup(group: String): EggGroup?

}