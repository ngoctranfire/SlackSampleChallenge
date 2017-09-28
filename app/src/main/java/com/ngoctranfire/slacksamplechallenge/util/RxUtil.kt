package com.ngoctranfire.slacksamplechallenge.util

import io.reactivex.FlowableTransformer
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by ngoctranfire on 9/24/17.
 */
object RxUtil {
    fun <T> applyBackgroundSchedulerToFlowable(scheduler: Scheduler): FlowableTransformer<T, T> {
        return FlowableTransformer { upstream ->
            upstream.subscribeOn(scheduler)
        }
    }

    fun <T> applyBackgroundSchedulerToSingle(scheduler: Scheduler): SingleTransformer<T, T> {
        return SingleTransformer { upstream ->
            upstream.subscribeOn(scheduler)
        }
    }
}