package com.dariobrux.whosings.common.extension

import com.dariobrux.whosings.data.database.model.ScoreEntity

/**
 * Get the max score of the user.
 * @return the max score.
 */
fun List<ScoreEntity>.getMaxScore(): Int {
    return this.maxOfOrNull {
        it.score
    } ?: 0
}