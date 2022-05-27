package com.bushelpowered.pokedex.service.dto

import javax.persistence.Table

@Table(name="stats")
data class PokeStats(
    val hp: Int,
    val speed: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int
)
