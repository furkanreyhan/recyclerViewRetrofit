package com.furkanreyhan.amphibians.remote

import com.furkanreyhan.amphibians.remote.model.AmphibiansItem
import retrofit2.Call
import retrofit2.http.GET

interface AmphibiansApi {
    //url'nin endpointi
    @GET("android-basics-kotlin-unit-4-pathway-2-project-api.json")
    fun getAmphibians(): Call<List<AmphibiansItem>>

}