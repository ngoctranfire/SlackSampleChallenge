package com.ngoctranfire.slacksamplechallenge.di.qualifiers

/**
 * Created by ngoctranfire on 9/23/17.
 */
import javax.inject.Qualifier

import kotlin.annotation.AnnotationRetention
import kotlin.annotation.Retention

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DefaultCacheSize