package com.bushelpowered.pokedex.entities

import com.fasterxml.jackson.annotation.JsonValue
import javax.persistence.*

@Table(name="egg_group")
@Entity
data class EggGroup(
    @Id
    val groupId : String,

    @JsonValue
    @Column(name = "group_name")
    val group: String,

)
