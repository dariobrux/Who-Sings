package com.dariobrux.whosings.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 *
 * Created by Dario Bruzzese on 10/12/2020.
 *
 */
data class SnippetData(

    @SerializedName("snippet_id")
    var id: Long?,

    @SerializedName("snippet_language")
    var language: String?,

    @SerializedName("snippet_body")
    var text : String?

)