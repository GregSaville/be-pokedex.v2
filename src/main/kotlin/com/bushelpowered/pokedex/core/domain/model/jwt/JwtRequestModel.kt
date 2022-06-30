package com.bushelpowered.pokedex.core.domain.model.jwt

import java.io.Serializable

class JwtRequestModel(val username : String, val password : String) : Serializable {
    private val serialVersionUID : Long = 2636936156391265891L
}