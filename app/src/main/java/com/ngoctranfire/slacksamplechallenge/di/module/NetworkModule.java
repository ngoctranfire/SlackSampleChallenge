package com.ngoctranfire.slacksamplechallenge.di.module;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ngoctranfire.slacksamplechallenge.BuildConfig;
import com.ngoctranfire.slacksamplechallenge.constants.NetworkConstants;
import com.ngoctranfire.slacksamplechallenge.di.qualifiers.DefaultCacheSize;
import com.ngoctranfire.slacksamplechallenge.di.scopes.AppScope;
import com.ngoctranfire.slacksamplechallenge.domain.persistence.remote.network.SlackServiceInterceptor;

import dagger.Module;
import dagger.Provides;
import io.reactivex.annotations.NonNull;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ngoctranfire on 9/23/17.
 */
@AppScope
@Module
public class NetworkModule {

    @Provides @AppScope @DefaultCacheSize
    static int provideCacheSize() {
        return 10 * 1024 * 1024;
    }

    @NonNull @Provides @AppScope
    static Gson gson() {
        return new GsonBuilder()
                .create();
    }

    @NonNull @Provides @AppScope
    static Cache provideDefaultHttpCache(@NonNull Application app, @DefaultCacheSize int cacheSize) {
        return new Cache(app.getCacheDir(), cacheSize);
    }

    @NonNull @Provides @AppScope
    static SlackServiceInterceptor slackServiceInterceptor() {
        return new SlackServiceInterceptor();
    }

    @NonNull @Provides @AppScope
    static OkHttpClient okHttpClient(Cache cache, SlackServiceInterceptor slackServiceInterceptor) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .addInterceptor(slackServiceInterceptor)
                .cache(cache);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addNetworkInterceptor(loggingInterceptor);
        }
        return httpClientBuilder.build();
    }

    @NonNull @Provides @AppScope
    static Retrofit retrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(NetworkConstants.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .validateEagerly(BuildConfig.DEBUG)
                .build();
    }
}
