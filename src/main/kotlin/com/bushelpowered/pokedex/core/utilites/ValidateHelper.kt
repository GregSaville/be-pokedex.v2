package com.bushelpowered.pokedex.core.utilites

import org.springframework.stereotype.Component

@Component
class ValidateHelper {

    fun isNumber(num: String): Boolean {
        return try {
            num.toInt()
            true
        } catch (ex: Exception) {
            false
        }
    }

}