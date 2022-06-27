package com.bushelpowered.pokedex.adapter.persistence.repository

import com.bushelpowered.pokedex.adapter.persistence.entities.Ability
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AbilityRepository : JpaRepository<Ability, String> {
    fun findByAbility(ability: String): Ability?
}