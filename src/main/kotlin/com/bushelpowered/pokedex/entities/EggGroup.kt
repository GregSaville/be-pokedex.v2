package com.bushelpowered.pokedex.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import javax.persistence.*

@Table(name="egg_group")
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
data class EggGroup(
    @JsonIgnore
    @JoinColumn(name = "poke_id")
    @Id
    val pokeId : String,

    val group1: String,

    val group2: String

)
