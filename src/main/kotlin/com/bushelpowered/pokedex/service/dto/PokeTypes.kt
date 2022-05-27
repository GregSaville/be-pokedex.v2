package com.bushelpowered.pokedex.service.dto

import javax.persistence.Table

@Table(name="typing")
data class PokeTypes(
    val type1: String,
    val type2: String
)
