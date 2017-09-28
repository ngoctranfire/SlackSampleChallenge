package com.ngoctranfire.slacksamplechallenge.di.module.activity.main;

import android.content.Context;

import com.ngoctranfire.slacksamplechallenge.di.qualifiers.ActivityQualifier;
import com.ngoctranfire.slacksamplechallenge.ui.users.UsersListingActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ngoctranfire on 9/23/17.
 */
@Module
public class MainActivityModule {

    @Provides @ActivityQualifier
    public Context provideContext(UsersListingActivity usersListingActivity) {
        return usersListingActivity;
    }

}
