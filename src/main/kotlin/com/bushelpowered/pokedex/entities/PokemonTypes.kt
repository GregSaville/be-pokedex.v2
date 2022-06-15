package com.bushelpowered.pokedex.entities

import javax.persistence.*

@Table(name="pokemon_type")
@Entity
data class PokemonTypes(
    @Id
    val id: String,


    @Column(name="poke_id")
    val pokeId: String,

    @Column(name="type_id")
    val typeId: String
)
