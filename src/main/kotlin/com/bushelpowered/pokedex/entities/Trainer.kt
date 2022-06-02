package com.bushelpowered.pokedex.entities

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonRawValue
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Table(name = "trainers")
@Entity
class Trainer (
    @Id
    var id: Long,
    @Column(name="email", nullable = false)
    var email: String,
    @Column(name="password", nullable = false)
    var password: String,
    @Column(name = "captured_pokemon", nullable = true)
    var capturedPokemon: String = ""
    )