package com.bushelpowered.pokedex.core.ingress.csv

interface CsvUseCase : LoadCSV

fun interface LoadCSV {
    fun loadCsv()
}