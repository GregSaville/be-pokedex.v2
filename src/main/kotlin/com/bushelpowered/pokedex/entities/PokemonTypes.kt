package com.bushelpowered.pokedex.entities

import javax.persistence.*

@Table(name="pokemon_type")
@Entity
data class PokemonTypes(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column(name="poke_id")
    @JoinTable(name="pokemon")
    val pokeId: String,

    @Column(name="type_id")
    @JoinTable(name="types")
    val typeId: String
)
