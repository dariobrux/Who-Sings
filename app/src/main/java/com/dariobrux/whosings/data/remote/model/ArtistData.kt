package com.dariobrux.whosings.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 *
 * Created by Dario Bruzzese on 10/12/2020.
 *
 */

data class ArtistPairData(

    @SerializedName("artist")
    var artist: ArtistData?
)

data class ArtistData(

    @SerializedName("artist_id")
    var id: Long?,

    @SerializedName("artist_mbid")
    var mBId: String?,

    @SerializedName("artist_name")
    var name: String?,

    @SerializedName("artist_rating")
    var rating: Int?,

    @SerializedName("updated_time")
    var updatedTime: String?

)