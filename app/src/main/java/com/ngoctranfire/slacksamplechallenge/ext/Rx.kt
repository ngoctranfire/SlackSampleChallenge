package com.ngoctranfire.slacksamplechallenge.ext

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by ngoctranfire on 9/24/17.
 */
fun Disposable.into(bin: CompositeDisposable) {
    bin.add(this)
}