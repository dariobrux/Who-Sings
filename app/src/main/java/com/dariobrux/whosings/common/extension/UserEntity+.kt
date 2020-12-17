package com.dariobrux.whosings.common.extension

import android.content.Context
import com.dariobrux.whosings.R
import com.dariobrux.whosings.data.database.model.UserEntity
import com.dariobrux.whosings.data.local.score.ScoreHeader
import com.dariobrux.whosings.data.local.score.Score
import com.dariobrux.whosings.data.local.score.ScoreInfo
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 *
 * Created by Dario Bruzzese on 9/12/2020.
 *
 * This file contains all the UserEntity related extended methods.
 *
 */

/**
 * Convert a list of [UserEntity] to a list of [Score] to display
 * the [ScoreHeader] and the [ScoreInfo].
 * @param context the application context
 */
fun List<UserEntity>.toScoreDataList(@ApplicationContext context: Context): List<Score> {
    val header = ScoreHeader(
        left = context.getString(R.string.pos),
        center = context.getString(R.string.name),
        right = context.getString(R.string.score)
    )

    val result = mutableListOf<Score>()

    val scores = this.sortedByDescending {
        it.scores.getMaxScore()
    }.mapIndexed { index, userEntity ->
        ScoreInfo(
            left = "${index + 1}",
            center = userEntity.name,
            right = userEntity.scores.getMaxScore().toString()
        )
    }

    result.add(header)
    result.addAll(scores)

    return result
}