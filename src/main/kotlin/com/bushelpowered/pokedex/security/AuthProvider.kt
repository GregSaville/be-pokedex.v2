package com.bushelpowered.pokedex.security

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User

class AuthProvider : AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
//        val username : String = authentication?.principal.toString()
//        val password : String = authentication?.credentials.toString()
//
////        val user : User =
        TODO("Not yet implemented")
    }






    override fun supports(authentication: Class<*>?): Boolean {
        TODO("Not yet implemented")
    }
}