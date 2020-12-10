package com.dariobrux.whosings.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 *
 * Created by Dario Bruzzese on 10/12/2020.
 *
 */
data class ObjectData(

    @SerializedName("message")
    var message : MessageData? = null
)