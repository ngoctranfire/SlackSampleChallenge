package com.ngoctranfire.slacksamplechallenge.di.module;

import android.app.Application;
import android.content.Context;

import com.ngoctranfire.slacksamplechallenge.di.qualifiers.AppQualifier;
import com.ngoctranfire.slacksamplechallenge.di.scopes.AppScope;
import com.ngoctranfire.slacksamplechallenge.executor.AppSchedulers;
import com.ngoctranfire.slacksamplechallenge.navigation.NavigationController;
import com.ngoctranfire.slacksamplechallenge.navigation.NavigationRouter;

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

    @Provides @AppScope
    static AppSchedulers provideAppSchedulers() {
        return new AppSchedulers();
    }

    @Provides @AppScope
    static NavigationRouter provideNavigationRouter() {
        return new NavigationController();
    }
}
