package com.bushelpowered.pokedex.adapter.persistence.entities.trainer

import com.bushelpowered.pokedex.core.domain.Pokemon
import javax.persistence.*

@Table(name = "trainers")
@Entity
data class Trainer(
    @Id
    @Column(name = "trainer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "name", nullable = false, unique = false)
    val name: String,

    @Column(name = "email", nullable = false, unique = true)
    val email: String,

    @Column(name = "password", nullable = false, unique = false)
    private val password: String,

    @OneToMany
    @JoinTable(
        name = "captured",
        joinColumns = [JoinColumn(name = "trainer_id", referencedColumnName = "trainer_id")],
        inverseJoinColumns = [JoinColumn(name = "poke_id", referencedColumnName = "id")]
    )
    val capturedPokemon: List<Pokemon>

){
    fun getPassword() : String{
        return this.password
    }
}