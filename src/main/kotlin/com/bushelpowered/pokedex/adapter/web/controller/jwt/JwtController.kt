package com.bushelpowered.pokedex.adapter.web.controller.jwt

import com.bushelpowered.pokedex.core.domain.model.jwt.JwtRequestModel
import com.bushelpowered.pokedex.core.domain.model.jwt.JwtResponseModel
import com.bushelpowered.pokedex.core.service.jwt.JwtUserDetailsService
import com.bushelpowered.pokedex.core.domain.jwt.TokenManager
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class JwtController(private val jwtUserDetailsService: JwtUserDetailsService,
                    private val authenticationManager: AuthenticationManager,
                    private val tokenManager: TokenManager
){

    @PostMapping("/login")
    fun login(@RequestBody request : JwtRequestModel): ResponseEntity<JwtResponseModel> {
        try{
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(request.username, request.password)
            )
        }catch(e : DisabledException){
            throw Exception("USER_DISABLED", e)
        }catch(e : BadCredentialsException){
            throw Exception("INVALID_CREDENTIALS", e)
        }
        val userDetails = jwtUserDetailsService.loadUserByUsername(request.username)
        val jwtToken = tokenManager.generateJwtToken(userDetails)
        return ResponseEntity.ok(JwtResponseModel(jwtToken))
    }

}