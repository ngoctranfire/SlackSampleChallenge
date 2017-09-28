package com.ngoctranfire.slacksamplechallenge.di.components;

import android.app.Application;

import com.ngoctranfire.slacksamplechallenge.app.SlackChallengeApp;
import com.ngoctranfire.slacksamplechallenge.di.module.ActivityBuilderModule;
import com.ngoctranfire.slacksamplechallenge.di.module.NetworkModule;
import com.ngoctranfire.slacksamplechallenge.di.module.RepositoryModule;
import com.ngoctranfire.slacksamplechallenge.di.multibinding.ViewModelModule;
import com.ngoctranfire.slacksamplechallenge.di.scopes.AppScope;
import com.ngoctranfire.slacksamplechallenge.di.module.AppModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by ngoctranfire on 9/23/17.
 */
@AppScope
@Component(modules={
        AndroidInjectionModule.class,
        ActivityBuilderModule.class,
        AppModule.class,
        ViewModelModule.class,
        NetworkModule.class,
        RepositoryModule.class
})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance Builder app(Application app);
        AppComponent build();
    }
    void inject(SlackChallengeApp app);
}
