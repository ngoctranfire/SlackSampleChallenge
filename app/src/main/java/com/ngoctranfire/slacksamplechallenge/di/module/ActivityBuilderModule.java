package com.ngoctranfire.slacksamplechallenge.di.module;

import com.ngoctranfire.slacksamplechallenge.di.module.activity.main.MainActivityModule;
import com.ngoctranfire.slacksamplechallenge.ui.users.UsersListingActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ngoctranfire on 9/23/17.
 */
@Module
abstract public class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract UsersListingActivity listingActivity();

}
