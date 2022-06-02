package com.bushelpowered.pokedex.security

import io.jsonwebtoken.Claims
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.Serializable

@Component
class JwtTokenUtil : Serializable {

    private val serialVersionUID : Long = -2550185165626007488L
    val JWT_TOKEN_VALIDITY = (5 * 60 * 60).toLong()

    @Value("\${jwt.secret}")
    private lateinit var secret : String

//    fun getUsernameFromToken(token : String){
//        return getClaimFromToken(token, Claims::getSubject)
//    }

//    fun getClaimFromToken(token: String, kFunction1: Function<Claims, String>) {
//
//    }
}
