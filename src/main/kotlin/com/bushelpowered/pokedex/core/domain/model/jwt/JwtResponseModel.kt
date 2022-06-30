package com.bushelpowered.pokedex.core.domain.model.jwt

import java.io.Serializable

class JwtResponseModel(val token : String) : Serializable {
    private val serialVersionUID : Long = 1L
}