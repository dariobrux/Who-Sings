package com.dariobrux.whosings.common.manager

import android.os.CountDownTimer

/**
 *
 * Created by Dario Bruzzese on 17/12/2020.
 *
 * This Manager handles the CountDownTimer during the game.
 *
 */
class TimerManager(val duration: Long) {

    private var timer: CountDownTimer? = null
    private var isFinished = false

    fun init(listener: ITimerManagerListener) {
        timer = object : CountDownTimer(duration, 1000L) {

            /**
             * Callback fired on regular interval.
             * @param millisUntilFinished The amount of time until finished.
             */
            override fun onTick(millisUntilFinished: Long) {
                isFinished = false
                listener.onTimerRun(millisUntilFinished)
            }

            /**
             * Callback fired when the time is up.
             */
            override fun onFinish() {
                isFinished = true
                listener.onTimerFinish()
            }
        }
    }

    fun start() {
        isFinished = false
        timer?.start()
    }

    fun cancel() {
        isFinished = true
        timer?.cancel()
    }

    fun isFinished() = isFinished
}