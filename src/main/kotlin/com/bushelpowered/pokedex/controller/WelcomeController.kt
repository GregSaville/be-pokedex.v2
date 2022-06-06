package com.bushelpowered.pokedex.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController

class WelcomeController {

    val welcomeHTML = "<h1>Welcome to the Pokedex API</h1>" +
            " <h2>---Public Endpoints---</h2> <h4>/welcome</h4> <h4>/api/pokemon</h4>   <h4>/api/pokemon/{pokemon id}</h4> <h4>/api/pokemon/?name={pokemon's name}</h4> <h2>---Authenticated Endpoints---</h2> <h4>/api/trainers</h4>  <h4>/api/trainers/{trainer id}</h4>  <h4>/api/trainers/{trainers id}/captured</h4>" +
            "<h3>Add Trainer -> @POST /api/trainers email=\"string\" & password=\"string\" </h3>" +
            "<h3>Mark Pokemon as Captued -> @PUT /api/trainers/{trainers id}  Pokemon's number </h3>" +
            "<h4>-----> can add multiple pokemon in one put request format text like  4 5 1 356 291  </h4>" +
            "<h3>Clear Trainers Pokemon  -> @DELETE /api/trainers/{trainers id}/captured</h3>"

    @GetMapping("/welcome")
    fun welcome() : String{
        return welcomeHTML
    }

    @GetMapping("/api")
    fun reroute() : String{
        return welcome()
    }

}