package com.bushelpowered.pokedex.repositories

import com.bushelpowered.pokedex.entities.EggGroup
import org.springframework.data.jpa.repository.JpaRepository

interface EggGroupRepository : JpaRepository<EggGroup, String> {
  fun findByGroup(group: String): EggGroup?

}