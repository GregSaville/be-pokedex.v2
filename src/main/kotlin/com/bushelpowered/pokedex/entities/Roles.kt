package com.bushelpowered.pokedex.entities

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name="roles")
@Entity
class Roles(
    @Id
    val roleId: Int,
    val roleName : String
) {
}