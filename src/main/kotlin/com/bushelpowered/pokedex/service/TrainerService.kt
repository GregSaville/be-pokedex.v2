package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.entities.Pokemon
import com.bushelpowered.pokedex.entities.Trainer
import com.bushelpowered.pokedex.repositories.PokemonRepository
import com.bushelpowered.pokedex.repositories.TrainerRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import java.util.Optional

@Service

class TrainerService(private val TrainerRepository: TrainerRepository, PokemonRepository: PokemonRepository) : PokemonService(PokemonRepository) {

    fun getAllTrainers(): MutableIterable<Trainer> = TrainerRepository.findAll()

    fun getTrainerById(trainerId: Long): Optional<Trainer> = TrainerRepository.findById(trainerId)

    fun getTrainerByName(trainerName: String): MutableIterable<Optional<Trainer>> = TrainerRepository.findByName(trainerName)

    fun addTrainer(trainer: Trainer) : Trainer{
        return if (TrainerRepository.findTrainerByEmail(trainer.email).isPresent){
            println("${trainer.email} is already registered")
            TrainerRepository.findTrainerByEmail(trainer.email).get()
        }else{
            TrainerRepository.save(trainer)
        }
    }

    fun removeTrainer(trainer: Optional<Trainer>) :String {
        return if(trainer.isPresent){
            TrainerRepository.delete(trainer.get())
            "${trainer.get().email} was removed"
        } else {
            "${trainer.get().email} was not found"
        }
    }

    fun addPokemon(pokeId: String, trainer: Optional<Trainer>): Trainer {
        return if (trainer.isPresent) {
            TrainerRepository.save(
                Trainer(
                    name = trainer.get().name,
                    email = trainer.get().email,
                    password = trainer.get().password,
                    capturedPokemon = handleCapture(trainer.get().capturedPokemon, pokeId),
                    id = trainer.get().id
                )
            )
        } else throw NotFoundException()
    }

    fun findTrainersPokemon(trainer: Optional<Trainer>) : List<Pokemon> {
        return if(trainer.isPresent){
            val listOfPokeID = trainer.get().capturedPokemon.split(" ")
            return findPokemonByIds(listOfPokeID)
        } else throw NotFoundException()
    }

    fun clearTrainersPokemon(trainer: Optional<Trainer>): Trainer {
        return if (!trainer.isPresent) {
            throw NotFoundException()
        } else {
            TrainerRepository.save(
                Trainer(
                    name = trainer.get().name,
                    email = trainer.get().email,
                    password = trainer.get().password,
                    capturedPokemon = "",
                    id = trainer.get().id
                )
            )
        }

    }

    private fun verifyNumber(toTest: String): String {
        var testList = toTest.split(" ","\n")
        testList = testList.toSet().toList()
        var returnString = ""
        testList.forEach{
            if(isNumber(it)){
                if(it.toInt() in 1..553){
                        returnString = "$returnString $it"
                }else{
                    println("Invalid Pokemon Number: $it")
                }
            }else{
                println("Invalid Pokemon Number: $it")
            }
        }
        return returnString.trim()
    }

    private fun isNumber(token : String) : Boolean{
        return if(token.isNullOrEmpty()){
            false
        } else token.all { Character.isDigit(it) }
    }

    private fun handleCapture(capturedPokemon: String, pokeId: String): String {
        var updatedCapturedPokemon = if(capturedPokemon.isEmpty()){
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

        updatedCapturedPokemon.toSet().toList().forEach{
            sortedCapturedPokemon.add(it.toInt())
        }

        sortedCapturedPokemon.sorted().forEach{
            returnString = "$returnString${it.toString()} "
        }
        return returnString.trim()
    }

}

