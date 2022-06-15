package com.bushelpowered.pokedex.entities
import javax.persistence.*


@Entity
data class Pokemon(
    @Id
    val id: String,

    val name: String,

    @OneToMany
    @JoinTable(name="pokemon_type", joinColumns = [JoinColumn(name = "type_id")])
    val type: List<Type>,

    val height: Double,

    val weight: Double,

    @OneToMany
    @JoinTable(name="pokemon_ability", joinColumns = [JoinColumn(name = "ability_id")])
    val abilities: List<Ability>,

    @OneToMany
    @JoinTable(name="pokemon_group", joinColumns = [JoinColumn(name = "group_id")])
    val eggGroups: List<EggGroup>,

    @OneToOne
    @JoinColumn(name="id")
    val stats: Stats,

    val genus: String,

    val description: String
)
