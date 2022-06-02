package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.repositories.AppUserRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

class AppUserService(val db : AppUserRepository) : UserDetailsService{

    override fun loadUserByUsername(username: String): UserDetails {
//        val user = db.findByUsername(username)
//        if(user.isPresent){
//            return UserDetailsSer
//        } else throw NotFoundException()
        TODO("Not yet implemented")
    }
}