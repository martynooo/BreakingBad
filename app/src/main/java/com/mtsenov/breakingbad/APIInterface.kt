package com.mtsenov.breakingbad


import com.mtsenov.breakingbad.model.SeriesChar
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @GET("characters")
    @Headers("Content-Type: application/json")
    fun getCharacters(): Call<List<SeriesChar>>

}