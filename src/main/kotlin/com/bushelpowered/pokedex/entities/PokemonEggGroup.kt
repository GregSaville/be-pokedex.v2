package com.bushelpowered.pokedex.entities

import javax.persistence.*

@Table(name="pokemon_group")
@Entity
data class PokemonEggGroup(
    @Id
    val id: String,


    @Column(name="poke_id")
    val pokeId: String,

    @Column(name="group_id")
    val groupId: String
)