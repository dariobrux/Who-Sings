package com.dariobrux.whosings.common.extension

import com.dariobrux.whosings.data.local.game.Artist
import com.dariobrux.whosings.data.remote.model.BodyData
import com.dariobrux.whosings.data.remote.model.ObjectData

/**
 *
 * Created by Dario Bruzzese on 10/12/2020.
 *
 * This file contains all the remote related extended methods.
 *
 */

/**
 * Convert a [BodyData] to a list of [Artist].
 * @return the new list of 3 [Artist] or an empty list.
 */
fun ObjectData.toArtistList(): List<Artist> {
    return this.message?.body?.artistList?.map {
        Artist(
            name = it.artist?.name ?: ""
        )
    }?.take(3) ?: emptyList()
}