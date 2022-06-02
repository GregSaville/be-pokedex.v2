package com.bushelpowered.pokedex.security

import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint

class RESTAuthenticationEntryPoint : BasicAuthenticationEntryPoint() {

    override fun afterPropertiesSet() {
        realmName = "PokedexApplication"
        super.afterPropertiesSet()
    }
}