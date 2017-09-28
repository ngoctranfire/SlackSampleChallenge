package com.ngoctranfire.slacksamplechallenge.di.multibinding;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.ngoctranfire.slacksamplechallenge.di.scopes.AppScope;
import com.ngoctranfire.slacksamplechallenge.ui.users.UsersListingViewModel;
import com.ngoctranfire.slacksamplechallenge.ui.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by ngoctranfire on 9/23/17.
 * This needs to be written in java, and so does the {@link ViewModelFactory}, otherwise it
 * will error out.
 */
@AppScope
@Module
abstract public class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UsersListingViewModel.class)
    abstract ViewModel bindUserListingViewModel(UsersListingViewModel usersListingViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);
}
