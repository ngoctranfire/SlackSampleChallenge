package com.ngoctranfire.slacksamplechallenge.di.module;

import android.app.Application;
import android.content.Context;

import com.ngoctranfire.slacksamplechallenge.di.qualifiers.AppQualifier;
import com.ngoctranfire.slacksamplechallenge.di.scopes.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ngoctranfire on 9/23/17.
 */
@AppScope
@Module
abstract public class AppModule {
    @Provides @AppScope @AppQualifier
    static Context provideAppContext(Application application) {
        return application;
    }
}
