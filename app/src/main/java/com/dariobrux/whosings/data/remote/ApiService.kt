package com.dariobrux.whosings.data.remote

import com.dariobrux.whosings.data.remote.model.BodyData
import com.dariobrux.whosings.data.remote.model.ObjectData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 * Created by Dario Bruzzese on 22/10/2020.
 *
 * Interface for Retrofit that contains the declaration of the API to invoke.
 *
 */

interface ApiService {

    /**
     * Get the [ObjectData] with the data to download.
     * @param page the number of the current page to download
     * @param size maximum number of pages to retrieve.
     * @param country the country language.
     * @param apikey the apikey related to the application.
     * @return the [ObjectData] mapped into a response.
     */
    @GET("chart.artists.get")
    suspend fun getChartArtists(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("country") country: String,
        @Query("apikey") apikey: String
    ): Response<ObjectData>

//    /**
//     * Get the [PokemonInfo] containing info of a single Pokemon.
//     * @param url the url to call to get the info.
//     * @return the [PokemonInfo] object mapped into an async response.
//     */
//    @GET
//    suspend fun getPokemonInfo(@Url url: String): Response<PokemonInfo>
}