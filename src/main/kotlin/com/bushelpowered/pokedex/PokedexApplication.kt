package com.bushelpowered.pokedex

import com.bushelpowered.pokedex.controller.CSVController
import com.bushelpowered.pokedex.utilites.CSVHelper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PokedexApplication

fun main(args: Array<String>) {
	runApplication<PokedexApplication>(*args)
}
