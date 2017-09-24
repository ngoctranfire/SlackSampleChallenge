package com.ngoctranfire.slacksamplechallenge.app

import android.app.Activity
import android.app.Application
import android.os.StrictMode
import com.ngoctranfire.slacksamplechallenge.BuildConfig
import com.ngoctranfire.slacksamplechallenge.di.AppInjector
import com.ngoctranfire.slacksamplechallenge.util.log.LogTree
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by ngoctranfire on 9/23/17.
 */

class SlackChallengeApp: Application(), HasActivityInjector {
    @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        setupLogging()
        if (BuildConfig.DEBUG) {
            setupLeakCanary()
        }
        // Create Graph
        AppInjector.initialize(this)
        Timber.d("onCreate(), started MainApplication DAG")

    }

    private fun setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
        Timber.d("Finished Installing Leak Canary!")

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyDeath()
                .build())

        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyDeath()
                .build())

    }

    private fun setupLogging() {
        Timber.plant(LogTree())
    }

}
