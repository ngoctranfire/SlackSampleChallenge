package com.ngoctranfire.slacksamplechallenge.domain.persistence.remote.network

import android.net.TrafficStats
import com.ngoctranfire.slacksamplechallenge.constants.NetworkConstants
import com.ngoctranfire.slacksamplechallenge.domain.persistence.remote.api.UsersQueryKeys
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by ngoctranfire on 9/23/17.
 */
class SlackServiceInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        val originalRequest = chain!!.request()
        val urlWithToken = originalRequest.url().newBuilder()
                .addEncodedQueryParameter(UsersQueryKeys.TOKEN, NetworkConstants.TOKEN)
                .build()

        TrafficStats.setThreadStatsTag(NetworkConstants.NETWORK_TAG)

        return chain.proceed(
                originalRequest
                .newBuilder()
                .url(urlWithToken)
                .build()
        )

    }

}