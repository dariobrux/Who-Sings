package com.dariobrux.whosings.data.remote

import com.dariobrux.whosings.common.Resource
import retrofit2.Response

/**
 *
 * Created by Dario Bruzzese on 9/12/2020.
 *
 */
abstract class ApiResult {

    /**
     * Map the result of the [call] to a [Resource] object.
     * @param call the api service function to invoke and map the result.
     * @return the [Resource] object with the the response as content.
     */
    suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    /**
     * Map a message as a [Resource] error state.
     * @param message the message to convert.
     * @return the [Resource] object mapped with error status.
     */
    private fun <T> error(message: String): Resource<T> {
        return Resource.error("Network call has failed for a following reason: $message")
    }
}