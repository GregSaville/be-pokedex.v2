package com.bushelpowered.pokedex.repositories

import com.bushelpowered.pokedex.entities.AppUser
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AppUserRepository : JpaRepository<AppUser, String> {
    fun findByUsername(username: String) : Optional<AppUser>
}