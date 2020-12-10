package com.dariobrux.whosings.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 *
 * Created by Dario Bruzzese on 10/12/2020.
 *
 */
data class BodyData(

    @SerializedName("artist_list")
    var artistList: List<ArtistPairData>?,

    @SerializedName("track_list")
    var trackList: List<TrackPairData>?,

    @SerializedName("snippet")
    var snippet : SnippetData?

)