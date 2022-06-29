package com.bushelpowered.pokedex.config

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

@Component
class TokenManager : java.io.Serializable {

    private val serialVersionUID : Long = 7008375124389347049L
    private val tokenValidity : Long = 10 * 60 * 60

    private val jwtSecret : String = "pokedexJWTSecret"

    fun generateJwtToken(userDetails: UserDetails): String {
        val claims = hashMapOf<String, Object>()
        return Jwts.builder().setClaims(claims.toMap()).setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + tokenValidity * 1000))
            .signWith(SignatureAlgorithm.HS512, jwtSecret).compact()
    }

    fun validateJwtToken(token : String, userDetails: UserDetails) : Boolean{
        val username = getUsernameFromToken(token)
        val claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body
        val isTokenExpired = claims.expiration.before(Date())
        return (username == userDetails.username && !isTokenExpired)
    }

    fun getUsernameFromToken(token : String) : String{
        val claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body
        return claims.subject
    }

}