package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.entities.Pokemon
import com.bushelpowered.pokedex.entities.Trainer
import com.bushelpowered.pokedex.repositories.PokemonRepository
import com.bushelpowered.pokedex.repositories.TrainerRepository
import org.springframework.stereotype.Service

@Service

class TrainerService(private val trainerRepository: TrainerRepository, private val pokemonRepository: PokemonRepository) {

    fun getAllTrainers(): List<Trainer> = trainerRepository.findAll()

    fun getTrainerById(trainerId: Long): Trainer? {
        return if (trainerRepository.findById(trainerId).isPresent) {
            trainerRepository.findById(trainerId).get()
        } else null
    }

    fun getTrainerByName(trainerName: String): List<Trainer> = trainerRepository.findByName(trainerName)

    fun addTrainer(trainer: Trainer): Trainer {
        if (trainerRepository.findTrainerByEmail(trainer.email) != null) {
            println("${trainer.email} is already registered")
        } else {
            trainerRepository.save(trainer)
        }
        return trainer
    }

    fun removeTrainer(trainer: Trainer): Trainer {
        trainerRepository.delete(trainer)
        println("${trainer.email} was removed")
        return trainer
    }

    fun addPokemon(pokeId: String, trainer: Trainer): Trainer {
        return (
                trainerRepository.save(
                    Trainer(
                        name = trainer.name,
                        email = trainer.email,
                        password = trainer.password,
                        capturedPokemon = handleCapture(trainer.capturedPokemon, pokeId),
                        id = trainer.id
                    )
                ))
    }


    fun findTrainersPokemon(trainer: Trainer): List<Pokemon> {
        val listOfPokeID = trainer.capturedPokemon.split(" ")
        return pokemonRepository.findAllById(listOfPokeID)
    }

    fun clearTrainersPokemon(trainer: Trainer): Trainer {
        return (
                trainerRepository.save(
                    Trainer(
                        name = trainer.name,
                        email = trainer.email,
                        password = trainer.password,
                        capturedPokemon = "",
                        id = trainer.id
                    )
                ))
    }


    private fun verifyNumber(toTest: String): String {
        var testList = toTest.split(" ", "\n")
        testList = testList.toSet().toList()
        var returnString = ""
        testList.forEach {
            if (isNumber(it)) {
                if (it.toInt() in 1..553) {
                    returnString = "$returnString $it"
                } else {
                    println("Invalid Pokemon Number: $it")
                }
            } else {
                println("Invalid Pokemon Number: $it")
            }
        }
        return returnString.trim()
    }

    private fun isNumber(token: String): Boolean {
        return if (token.isNullOrEmpty()) {
            false
        } else token.all { Character.isDigit(it) }
    }

    private fun handleCapture(capturedPokemon: String, pokeId: String): String {
        var updatedCapturedPokemon = if (capturedPokemon.isEmpty()) {
            verifyNumber(pokeId)
        } else {
            capturedPokemon + " " + verifyNumber(pokeId)
        }
        updatedCapturedPokemon = removeDuplicatesAndSort(updatedCapturedPokemon.split(" "))
        return updatedCapturedPokemon
    }

    private fun removeDuplicatesAndSort(updatedCapturedPokemon: List<String>): String {
        var returnString = ""
        var sortedCapturedPokemon = mutableListOf<Int>()

        updatedCapturedPokemon.toSet().toList().forEach {
            sortedCapturedPokemon.add(it.toInt())
        }

        sortedCapturedPokemon.sorted().forEach {
            returnString = "$returnString${it.toString()} "
        }
        return returnString.trim()
    }

}



