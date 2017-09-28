package com.ngoctranfire.slacksamplechallenge.domain.repository

import com.ngoctranfire.slacksamplechallenge.domain.persistence.model.Member
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by ngoctranfire on 9/24/17.
 */
interface UsersDataSource {
    fun getMembers(cursor: String? = null, includeLocale: Boolean = true,
                   pageSize: Int = 200, includePresence: Boolean = true): Single<List<Member>>

    fun getMember(id: String): Single<Member>
}