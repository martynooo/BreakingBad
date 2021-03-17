package com.mtsenov.breakingbad

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


internal object APIClient {
    private const val BASE_URL = "https://breakingbadapi.com/api/"
    private var retrofit: Retrofit? = null

    var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    val client: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }

            return retrofit
        }
}