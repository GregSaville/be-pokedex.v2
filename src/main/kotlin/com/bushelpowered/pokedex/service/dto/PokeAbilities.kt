package com.bushelpowered.pokedex.service.dto

import javax.persistence.Table

@Table(name="abilities")
data class PokeAbilities(
    val ability1: String,
    val ability2: String,
    val ability3: String
) {

}
