package com.dariobrux.whosings.common.extension

import com.dariobrux.whosings.data.local.game.Artist
import com.dariobrux.whosings.data.local.game.Snippet
import com.dariobrux.whosings.data.local.game.Track
import com.dariobrux.whosings.data.remote.model.BodyData
import com.dariobrux.whosings.data.remote.model.ObjectData
import com.dariobrux.whosings.data.remote.model.TrackData

/**
 *
 * Created by Dario Bruzzese on 10/12/2020.
 *
 * This file contains all the remote related extended methods.
 *
 */

/**
 * Convert a [BodyData] to a list of [Artist].
 * @return the new list of [Artist] or an empty list.
 */
fun ObjectData.toArtistList(): List<Artist> {
    return this.message?.body?.trackList?.map {
        Artist(
            name = it.track?.artistName ?: "",
            track = it.track?.toTrack()
        )
    } ?: emptyList()
}

/**
 * Convert a [TrackData] object to [Track] object.
 * @return the new [Track] object or null.
 */
fun TrackData?.toTrack(): Track? {
    this ?: return null
    return Track(
        id = this.id ?: 0,
        name = this.name ?: ""
    )
}

/**
 * Convert a [BodyData] to a [Snippet].
 * @return the new [Snippet].
 */
fun ObjectData.toSnippetLyrics(): Snippet {
    return Snippet(
        text = this.message?.body?.snippet?.text ?: ""
    )
}

/**
 * Get a random track.
 * @return the [Track] object or null.
 */
fun List<Artist>.toRandomTrack(): Track? {
    return this.shuffled().first().track
}