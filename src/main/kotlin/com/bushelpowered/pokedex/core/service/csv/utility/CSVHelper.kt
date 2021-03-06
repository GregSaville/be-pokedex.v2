package com.bushelpowered.pokedex.core.service.csv.utility

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.springframework.stereotype.Component
import java.net.URL
import java.nio.charset.StandardCharsets

@Component
class CSVHelper {

    fun getCSVParser(): CSVParser {
        val url =
            URL("https://bitbucket.org/!api/2.0/snippets/myriadmobile/Rerr8E/96d04ea30f8e177149dd0c1c98271f1843b5f9b7/files/pokedex.csv")
        return CSVParser.parse(
            url,
            StandardCharsets.UTF_8, CSVFormat.EXCEL.withHeader()
        )
    }

    fun dataToList(data: String): List<String> {
        var returnString = data
        returnString = returnString.replace("[", "")
        returnString = returnString.replace("\"", "")
        returnString = returnString.replace(" ", "")
        returnString = returnString.replace("]", "")
        return returnString.split(",")
    }

}