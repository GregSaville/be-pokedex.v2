package com.bushelpowered.pokedex.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import javax.persistence.*

@Table(name="types")
@Entity
data class Types(
    @JsonIgnore
    @Column(name = "type_id")
    @Id
    val id : String,

    val type: String,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id")
    val pokemon: Pokemon
)