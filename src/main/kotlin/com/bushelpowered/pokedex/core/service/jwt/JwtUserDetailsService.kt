package com.bushelpowered.pokedex.core.service.jwt


import com.bushelpowered.pokedex.adapter.persistence.entities.trainer.Trainer
import com.bushelpowered.pokedex.core.egress.trainer.TrainerPort
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class JwtUserDetailsService(val trainerPort: TrainerPort) : UserDetailsService{

    override fun loadUserByUsername(username: String): UserDetails{
        val trainer = trainerPort.findTrainerByEmail(username)
        if(trainer != null){
            return User(trainer.email,
                trainer.getPassword(),
            arrayListOf()
            )
        } else {
            throw UsernameNotFoundException("User $username not found ")
        }
    }

}