package com.bushelpowered.pokedex.entities


import com.fasterxml.jackson.annotation.JsonValue
import javax.persistence.*

@Table(name = "pokemon")
@Entity
data class Pokemon(
    @Id
    val id: String,

    val name: String,


    @OneToMany
    @JoinColumn(name="poke_id", referencedColumnName = "id")
    val type: List<Types>,

    val height: Double,

    val weight: Double,

    @Transient
    @OneToOne
    @JoinColumn(name="id")
    val abilities: Abilities,

    @Transient
    @OneToOne
    @JoinColumn(name="id")
    val eggGroups: EggGroup,

    @OneToOne
    @JoinColumn(name="id")
    val stats: Stats,

    val genus: String,

    val description: String
)
{}