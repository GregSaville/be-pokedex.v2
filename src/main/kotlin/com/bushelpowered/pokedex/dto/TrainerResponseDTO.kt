package com.bushelpowered.pokedex.dto

import org.valiktor.validate

data class TrainerResponseDTO(
    val id: Long,
    val name : String,
    val email: String
)

