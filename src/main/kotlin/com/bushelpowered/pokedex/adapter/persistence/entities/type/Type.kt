package com.bushelpowered.pokedex.adapter.persistence.entities.type

import com.fasterxml.jackson.annotation.JsonValue
import javax.persistence.*

@Table(name = "types")
@Entity
data class Type(


    @Id
    @Column(name = "type_id")
    val id: String,


    @JsonValue
    val type: String,

    )
