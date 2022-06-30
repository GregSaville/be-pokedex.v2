package com.bushelpowered.pokedex.core.domain.model.pokemon

import com.bushelpowered.pokedex.adapter.persistence.entities.csv.Ability
import com.bushelpowered.pokedex.adapter.persistence.entities.csv.EggGroup
import com.bushelpowered.pokedex.adapter.persistence.entities.csv.Stats
import com.bushelpowered.pokedex.adapter.persistence.entities.type.Type


data class PokemonModel(
    val id: String,
    val name: String,
    val type: List<Type>,
    val height: Double,
    val weight: Double,
    val abilities: List<Ability>,
    val eggGroups: List<EggGroup>,
    val stats: Stats,
    val genus: String,
    val description: String,
    val image: String,
    val sprite: String,
    val gif: String,
    val shinyGif: String,
    val shinySprite: String
)