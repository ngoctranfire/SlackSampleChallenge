package com.ngoctranfire.slacksamplechallenge.executor

import com.ngoctranfire.slacksamplechallenge.di.scopes.AppScope
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by ngoctranfire on 9/23/17.
 */

@AppScope
class AppSchedulers constructor (
        private val diskIO: Executor = Executors.newSingleThreadExecutor(),
        private val networkIO: Executor = Executors.newFixedThreadPool(3)
) {

    fun diskScheduler(): Scheduler {
        return Schedulers.from(diskIO)
    }
    fun networkScheduler(): Scheduler {
        return Schedulers.from(networkIO)
    }

}

