package com.bushelpowered.pokedex.service.dto

import javax.persistence.Table

@Table(name="egg_group")
data class EggGroups(
    val group1: String,
    val group2: String
)
