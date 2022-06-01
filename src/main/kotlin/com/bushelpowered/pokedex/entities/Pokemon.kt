package com.bushelpowered.pokedex.entities

import com.fasterxml.jackson.annotation.JsonRawValue
import javax.persistence.*

@Table(name = "pokemon")
@Entity
class Pokemon(
    @Id
    val id: String,
    val name: String,
    @Column(name = "types", columnDefinition = "json")
    @JsonRawValue
    val types: String,
    val height: Double,
    val weight: Double,
    @Column(name = "abilities", columnDefinition = "json")
    @JsonRawValue
    val abilities: String,
    @Column(name="egg_groups", columnDefinition = "json")
    @JsonRawValue
    val egg_groups: String,
    @Column(name="stats", columnDefinition = "json")
    @JsonRawValue
    val stats: String,
    val genus: String,
    val description: String
)
{}