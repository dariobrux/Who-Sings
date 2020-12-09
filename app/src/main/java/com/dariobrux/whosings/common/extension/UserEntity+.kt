package com.dariobrux.whosings.common.extension

import android.content.Context
import com.dariobrux.whosings.R
import com.dariobrux.whosings.data.database.model.UserEntity
import com.dariobrux.whosings.data.local.score.HeaderData
import com.dariobrux.whosings.data.local.score.ScoreData
import com.dariobrux.whosings.data.local.score.ScoreInfoData
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 *
 * Created by Dario Bruzzese on 9/12/2020.
 *
 * This file contains all the UserEntity related extended methods.
 *
 */

/**
 * Convert a list of [UserEntity] to a list of [ScoreData] to display
 * the [HeaderData] and the [ScoreInfoData].
 * @param context the application context
 */
fun List<UserEntity>.toScoreDataList(@ApplicationContext context: Context): List<ScoreData> {
    val header = HeaderData(
        left = context.getString(R.string.pos),
        center = context.getString(R.string.name),
        right = context.getString(R.string.score)
    )

    val result = mutableListOf<ScoreData>()

    val scores = this.mapIndexed { index, userEntity ->
        ScoreInfoData(
            left = "${index + 1}",
            center = userEntity.name,
            right = userEntity.score.toString()
        )
    }

    result.add(header)
    result.addAll(scores)

    return result
}