package ru.orehovai.testiceborn.utils

import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.orehovai.testiceborn.model.Continent
import ru.orehovai.testiceborn.model.Country

interface RetrofitQuery {

    @GET("continent/{continent}/info")
    fun getContinent(@Path("continent") continent: String): Deferred<Response<List<Country>>>

    @GET("country/{country}/info")
    fun getCountry(@Path("country") country: String): Call<ResponseBody>

    @GET("carrier/{id}/networks")
    fun getOperator(@Path("id") id: String): Call<ResponseBody>

    @GET("ip/{ip_adr}")
    fun getIP(@Path("ip_adr") ipAdr: String): Call<ResponseBody>
}


