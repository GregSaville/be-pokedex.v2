package com.bushelpowered.pokedex.utilites

class ValidateHelper {

    fun isNumber(num: String) : Boolean {
        return try{
            num.toInt()
            true
        } catch(ex : Exception){
            false
        }
    }

}