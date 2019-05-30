package ru.orehovai.testiceborn

import ru.orehovai.testiceborn.model.Continent
import ru.orehovai.testiceborn.model.Country
import ru.orehovai.testiceborn.utils.BaseRepository
import ru.orehovai.testiceborn.utils.RetrofitQuery

class MovieRepository(private val api : RetrofitQuery) : BaseRepository() {

    suspend fun getCountry(cont: String) : MutableList<Country>?{

        //safeApiCall is defined in BaseRepository.kt (https://gist.github.com/navi25/67176730f5595b3f1fb5095062a92f15)
        val continentResponse = safeApiCall(
            call = {api.getContinent(cont).await()},
            errorMessage = "Error Fetching Popular Movies"
        )

        return continentResponse?.toMutableList()

    }

}