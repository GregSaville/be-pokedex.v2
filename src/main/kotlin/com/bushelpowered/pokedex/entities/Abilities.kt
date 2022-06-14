package com.bushelpowered.pokedex.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="abilities")
@Entity
data class Abilities(
    @JsonIgnore
    @Column(name = "poke_id")
    @Id
    val id: String,

    val ability1: String,

    val ability2: String,

    val ability3: String

)
