package com.bushelpowered.pokedex.entities

import com.fasterxml.jackson.annotation.JsonValue
import javax.persistence.*

@Table(name="types")
@Entity
data class Type(


    @Id
    @JoinTable(name="pokemon_type", joinColumns = [JoinColumn(name = "type_id")], inverseJoinColumns = [JoinColumn(name="type_id")])
    val typeId : String,


    @JsonValue
    val type: String,

)