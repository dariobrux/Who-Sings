package com.dariobrux.whosings.common.manager

/**
 *
 * Created by Dario Bruzzese on 17/12/2020.
 *
 * This interface manages a CountDownTimer during the game.
 *
 */
interface ITimerManagerListener {

    /**
     * Callback fired on regular interval.
     * @param millis The amount of time until finished.
     */
    fun onTimerRun(millis: Long)

    /**
     * Callback fired when the time is up.
     */
    fun onTimerFinish()
}