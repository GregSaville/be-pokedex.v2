package com.bushelpowered.pokedex.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class WelcomeController {

    @GetMapping("")
    fun welcome() : String{
        return "~Welcome to the Pokedex API~  @GetMethods /pokemon   /pokemon/{pokemon id}  /trainers  /trainers/{trainer id}  /trainers/{trainers id}/captured"
    }
}