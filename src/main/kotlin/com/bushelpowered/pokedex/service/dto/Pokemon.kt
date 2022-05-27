package com.bushelpowered.pokedex.service.dto

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "pokemon")
@Entity
data class Pokemon(
    @Id
    val id: String,
    val name: String,
    @Transient
    val types: PokeTypes,
    val height: Double,
    val weight: Double,
    @Transient
    val abilities: PokeAbilities,
    @Transient
    val egg_groups: EggGroups,
    @Transient
    val stats: PokeStats,
    val genus: String,
    val description: String
)