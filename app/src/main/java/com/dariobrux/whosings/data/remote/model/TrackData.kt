package com.dariobrux.whosings.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 *
 * Created by Dario Bruzzese on 10/12/2020.
 *
 */

data class TrackPairData(

    @SerializedName("track")
    var track: TrackData?
)

data class TrackData(

    @SerializedName("track_id")
    var id: Long?,

    @SerializedName("track_name")
    var name: String?,

    @SerializedName("artist_name")
    var artistName: String?

)