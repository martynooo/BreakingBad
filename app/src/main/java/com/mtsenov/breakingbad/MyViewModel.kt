package com.mtsenov.breakingbad

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mtsenov.breakingbad.model.SeriesChar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MyViewModel : ViewModel() {
    private val characters: MutableLiveData<List<SeriesChar>> by lazy {
        MutableLiveData<List<SeriesChar>>().also {
            loadCharacters()
        }
    }

    fun getCharacters(): LiveData<List<SeriesChar>> {
        return characters
    }

    private fun loadCharacters() {
        val service = APIClient.client?.create<APIInterface>()
        val call: Call<List<SeriesChar>>? = service?.getCharacters()

        call?.enqueue(object : Callback<List<SeriesChar>?> {
            override fun onResponse(
                call: Call<List<SeriesChar>?>?,
                response: Response<List<SeriesChar>?>
            ) {
                val checkFormulaResponse: List<SeriesChar>? = response.body()
                if (checkFormulaResponse != null) {
                    characters.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<SeriesChar>?>, t: Throwable) {
                call.cancel()
            }
        })
    }
}