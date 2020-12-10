package com.dariobrux.whosings.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 *
 * Created by Dario Bruzzese on 10/12/2020.
 *
 */
data class MessageData(

    @SerializedName("header")
    var header: HeaderData?,

    @SerializedName("body")
    var body: BodyData?,

)