package com.bushelpowered.pokedex.config

import com.bushelpowered.pokedex.core.service.trainer.TrainerService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class JwtUserDetailsService : UserDetailsService{
    override fun loadUserByUsername(username: String?): UserDetails {

        if("randomuser123" == username){
            return User("randomuser123",
                "$2a$10\$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
            arrayListOf()
            )
        } else {
            throw UsernameNotFoundException("User $username not found ")
        }
    }

}