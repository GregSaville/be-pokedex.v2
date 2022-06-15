package com.bushelpowered.pokedex.entities

import javax.persistence.*

@Table(name = "trainers")
@Entity
data class Trainer (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(name = "name", nullable = false)
    var name: String,
    @Column(name="email", nullable = false)
    var email: String,
    @Column(name="password", nullable = false)
    var password: String,
    @Column(name = "captured_pokemon", nullable = false)
    var capturedPokemon: String = ""
)