package com.dariobrux.whosings.data.remote.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

/**
 *
 * Created by Dario Bruzzese on 10/12/2020.
 *
 */
data class HeaderData(

    @SerializedName("status_code")
    var statusCode: Int?,

    @SerializedName("execute_time")
    var executeTime: Double?

)