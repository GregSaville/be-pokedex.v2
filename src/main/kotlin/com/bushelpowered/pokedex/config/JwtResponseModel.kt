package com.bushelpowered.pokedex.config

import java.io.Serializable

class JwtResponseModel(val token : String) : Serializable {
    private val serialVersionUID : Long = 1L
}