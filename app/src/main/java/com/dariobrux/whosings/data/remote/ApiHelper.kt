package com.dariobrux.whosings.data.remote

import com.dariobrux.whosings.data.remote.model.ObjectData
import javax.inject.Inject

/**
 *
 * Created by Dario Bruzzese on 9/12/2020.
 *
 * This class get the results from the api service and map the result object
 * to an useful object with the status.
 *
 */

class ApiHelper @Inject constructor(private val service: ApiService) : ApiResult() {

    /**
     * Get the [ObjectData] with the data to download.
     * @param page the number of the current page to download
     * @param size maximum number of pages to retrieve.
     * @param country the country language.
     * @param apikey the apikey related to the application.
     * @return the [ObjectData] mapped inside a response into a resource.
     */
    suspend fun getChartArtists(page: Int, size: Int, country: String, apikey: String) = getResult {
        service.getChartArtists(page, size, country, apikey)
    }

//    /**
//     * Get the [PokemonInfo] containing info of a single Pokemon.
//     * @param url the url to call to get the info.
//     * @return the [PokemonInfo] object mapped into an async response.
//     */
//    suspend fun getPokemonInfo(@Url url: String) = getResult {
//        service.getPokemonInfo(url)
//    }

}