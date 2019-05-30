package ru.orehovai.testiceborn.model

import com.google.gson.annotations.SerializedName

data class Country (

    @SerializedName("name") val name : String,
    @SerializedName("is_used") val is_used : Boolean,
    @SerializedName("iso_code") val iso_code : String
)

data class Continent (
    val listCountry: ArrayList<Country>
)