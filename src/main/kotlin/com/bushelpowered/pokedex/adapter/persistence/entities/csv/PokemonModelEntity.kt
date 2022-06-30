package com.bushelpowered.pokedex.adapter.persistence.entities.csv

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "pokemon")
@Entity
data class PokemonModelEntity(
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
