package com.bushelpowered.pokedex.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name="users")
@Entity
class AppUser(
    @Id
    val username : String,
    @Column(name="email")
    val email : String,
    @Column(name = "password")
    val password : String,
    @Column(name="roleId")
    val roleID : Int
) {
}