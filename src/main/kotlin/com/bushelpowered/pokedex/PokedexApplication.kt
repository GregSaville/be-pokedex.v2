package com.bushelpowered.pokedex

import com.bushelpowered.pokedex.core.ingress.csv.CsvUseCase
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener

@SpringBootApplication
class PokedexApplication(private val csvUseCase: CsvUseCase){

	@EventListener(ApplicationReadyEvent::class)
	fun loadCsv(){
		csvUseCase.loadCsv()
	}

}

fun main(args: Array<String>) {
	runApplication<PokedexApplication>(*args)
}
