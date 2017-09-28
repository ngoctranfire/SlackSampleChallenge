package com.ngoctranfire.slacksamplechallenge.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.ngoctranfire.slacksamplechallenge.R;
import com.ngoctranfire.slacksamplechallenge.di.scopes.AppScope;
import com.ngoctranfire.slacksamplechallenge.domain.persistence.local.db.SlackChallengeDB;
import com.ngoctranfire.slacksamplechallenge.domain.persistence.local.db.dao.MembersDao;
import com.ngoctranfire.slacksamplechallenge.domain.persistence.model.Member;
import com.ngoctranfire.slacksamplechallenge.domain.persistence.remote.api.UsersService;
import com.ngoctranfire.slacksamplechallenge.domain.repository.UsersDataSource;
import com.ngoctranfire.slacksamplechallenge.domain.repository.UsersRepo;
import com.nytimes.android.external.store3.base.Persister;
import com.nytimes.android.external.store3.base.impl.BarCode;
import com.nytimes.android.external.store3.base.impl.MemoryPolicy;
import com.nytimes.android.external.store3.base.impl.Store;
import com.nytimes.android.external.store3.base.impl.StoreBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import retrofit2.Retrofit;

/**
 * Created by ngoctranfire on 9/23/17.
 */
@AppScope
@Module(includes = RepositoryModule.Declarations.class)
public class RepositoryModule {

    @NonNull @Provides @AppScope
    static UsersService provideUsersService(Retrofit retrofit) {
        return retrofit.create(UsersService.class);
    }

    @NonNull @Provides @AppScope
    static SlackChallengeDB provideSlackChallengeDB(Application app) {
        return Room.databaseBuilder(app, SlackChallengeDB.class, app.getString(R.string.app_name)).build();
    }

    @NonNull @Provides @AppScope
    static MembersDao provideMembersDao(SlackChallengeDB slackChallengeDB) {
        return slackChallengeDB.membersDao();
    }

    @Module @AppScope
    public interface Declarations {
        @Binds @AppScope
        UsersDataSource provideUsersDataSource(UsersRepo usersRepo);
    }


}
