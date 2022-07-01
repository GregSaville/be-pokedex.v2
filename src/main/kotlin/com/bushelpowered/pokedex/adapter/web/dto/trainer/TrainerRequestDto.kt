package com.bushelpowered.pokedex.adapter.web.dto.trainer

import org.valiktor.functions.hasSize
import org.valiktor.functions.isEmail
import org.valiktor.functions.isNotNull
import org.valiktor.validate

data class TrainerRequestDto(
    val name: String,
    val email: String,
    val password: String
) {
    init {
        validate(this) {
            validate(TrainerRequestDto::name).isNotNull().hasSize(min = 2)
            validate(TrainerRequestDto::email).isEmail()
            validate(TrainerRequestDto::password).hasSize(min = 6)
        }
    }
}