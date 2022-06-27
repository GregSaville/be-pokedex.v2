package com.bushelpowered.pokedex.adapter.persistence.entities.csv

import com.fasterxml.jackson.annotation.JsonValue
import javax.persistence.*

@Table(name = "egg_group")
@Entity
data class EggGroup(
    @Id
    @Column(name = "group_id")
    val groupId: String,

    @JsonValue
    @Column(name = "group_name")
    val group: String,

    )
