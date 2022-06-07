package com.bushelpowered.pokedex.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController

class WelcomeController {

    val welcomeHTML = "<h1>Welcome to the Pokedex API</h1>" +
            " <h2>---Public Endpoints---</h2> <h4>/welcome</h4> <h4>/api/pokemon</h4>  <h4>/api/pokemon?page={1 - 37}</h4>  <h4>/api/pokemon/all</h4>  <h4>/api/pokemon/{pokemon id}</h4> <h4>/api/pokemon?name={pokemon's name}</h4> " +
            "<h4>/api/pokemon?type={type1,type2}  || ?type={type1 type2} || ?type={type1+type2}</h4> <h4> for all with split typing --> /api/pokemon?type={type}</h4> <h4>for all with only single typing --> /api/pokemon?type={type+}</h4>" +
            "<h2>---Authenticated Endpoints---</h2> <h4>/api/trainers</h4>  <h4>/api/trainers/{trainer id}</h4> <h4>/api/trainers/?name={trainer's name}</h4> <h4>/api/trainers/{trainers id}/captured</h4>" +
            "<h4>Add Trainer -> @POST /api/trainers email=\"string\" & password=\"string\" </h4>" +
            "<h4>Mark Pokemon as Captued -> @PUT /api/trainers/{trainers id}  Pokemon's number </h4>" +
            "<h5>-----> can add multiple pokemon in one put request format text like  4 5 1 356 291  </h5>" +
            "<h4>Clear Trainers Pokemon  -> @DELETE /api/trainers/{trainers id}/captured</h4>"

    @GetMapping("/welcome")
    fun welcome() : String{
        return welcomeHTML
    }

    @GetMapping("/api")
    fun reroute() : String{
        return welcome()
    }

}