package com.dariobrux.whosings.data.remote

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
     * @param chartName the name of the chart. For example "top"
     * @param page the number of page.
     * @param size maximum number of pages to retrieve.
     * @param country the country language.
     * @param hasLyrics flag if the track must contain lyrics. For example 1
     * @param apikey the apikey related to the application.
     * @return the [ObjectData] mapped into a response.
     */
    @GET("chart.tracks.get")
    suspend fun getChartTracks(
        @Query("chart_name") chartName: String,
        @Query("page") page: Int,
        @Query("page_size") size: Int,
        @Query("country") country: String,
        @Query("f_has_lyrics") hasLyrics: Int,
        @Query("apikey") apikey: String
    ): Response<ObjectData>

    /**
     * Get the snippet lyrics of a track.
     * @param trackId the id of the track.
     * @param apikey the apikey related to the application.
     * @return the [ObjectData] mapped inside a response into a resource.
     */
    @GET("track.snippet.get")
    suspend fun getSnippetLyrics(
        @Query("track_id") trackId: Long,
        @Query("apikey") apikey: String
    ): Response<ObjectData>
}