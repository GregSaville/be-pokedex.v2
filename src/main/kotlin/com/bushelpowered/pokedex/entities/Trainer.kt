package com.bushelpowered.pokedex.entities

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
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

) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("Not yet implemented")
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        TODO("Not yet implemented")
    }

    override fun isAccountNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isAccountNonLocked(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isCredentialsNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEnabled(): Boolean {
        TODO("Not yet implemented")
    }
}