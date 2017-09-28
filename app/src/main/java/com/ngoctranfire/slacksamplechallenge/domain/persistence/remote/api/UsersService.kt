package com.ngoctranfire.slacksamplechallenge.domain.persistence.remote.api

import com.ngoctranfire.slacksamplechallenge.domain.persistence.model.UsersResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ngoctranfire on 9/23/17.
 */

interface UsersService {
    @GET("users.list")
    fun getUsers(@Query(UsersQueryKeys.CURSOR) cursor: String? = null,
                 @Query(UsersQueryKeys.INCLUDE_LOCALE) includeLocale: Boolean,
                 @Query(UsersQueryKeys.LIMIT) pageSize: Int = 50,
                 @Query(UsersQueryKeys.PRESENCE) includePresence: Boolean): Single<UsersResult>
}

object UsersQueryKeys {
    const val TOKEN = "token"
    const val CURSOR = "cursor"
    const val INCLUDE_LOCALE = "include_locale"
    const val LIMIT = "limit"
    const val PRESENCE = "presence"
}
