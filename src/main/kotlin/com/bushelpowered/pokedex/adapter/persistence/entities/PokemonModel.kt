package com.bushelpowered.pokedex.adapter.persistence.entities

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "pokemon")
@Entity
data class PokemonModel(
    @Id
    val id: String,
    val name: String,
    val height: String,
    val weight: String,
    val genus: String,
    val description: String,
    val image: String,
    val sprite: String,
    val gif: String,
    val shinyGif: String,
    val shinySprite: String
)
