package com.ngoctranfire.slacksamplechallenge.domain.repository

import com.ngoctranfire.slacksamplechallenge.domain.persistence.local.db.dao.MembersDao
import com.ngoctranfire.slacksamplechallenge.domain.persistence.model.Member
import com.ngoctranfire.slacksamplechallenge.domain.persistence.remote.api.UsersRequest
import com.ngoctranfire.slacksamplechallenge.domain.persistence.remote.api.UsersService
import com.ngoctranfire.slacksamplechallenge.executor.AppSchedulers
import com.ngoctranfire.slacksamplechallenge.util.RxUtil
import com.nytimes.android.external.store3.base.Persister
import com.nytimes.android.external.store3.base.impl.MemoryPolicy
import com.nytimes.android.external.store3.base.impl.Store
import com.nytimes.android.external.store3.base.impl.StoreBuilder
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by ngoctranfire on 9/24/17.
 */

class UsersRepo @Inject constructor(
        private val usersService: UsersService,
        private val membersDao: MembersDao,
        private val appSchedulers: AppSchedulers
): UsersDataSource {
    private val usersStore: Store<List<Member>, UsersRequest> = StoreBuilder.key<UsersRequest, List<Member>>()
                .fetcher { (cursor, locale, pageSize, presence) ->
                    usersService.getUsers(cursor, locale, pageSize, presence)
                            .doOnSuccess { Timber.d("Fetching fresh data from the network for the list of users") }
                            .compose(RxUtil.applyBackgroundSchedulerToSingle(appSchedulers.networkScheduler()))
                            .map { (_, members) ->
                                Timber.v(" Network request for users returned following list = %s", members)
                                members
                            }
                }
                .persister(object : Persister<List<Member>, UsersRequest> {
                    override fun read(usersRequest: UsersRequest): Maybe<List<Member>> {
                        return membersDao.getMembers()
                                .compose(RxUtil.applyBackgroundSchedulerToFlowable(appSchedulers.diskScheduler()))
                                .firstElement()
                                .doOnSuccess { Timber.d(" Fetching users from \"members\" table instead of calling from the network") }
                                .flatMap { members ->
                                    if (members.isEmpty()) {
                                        Maybe.empty()
                                    } else {
                                        Maybe.just(members)
                                    }
                                }
                    }
                    override fun write(usersRequest: UsersRequest, members: List<Member>): Single<Boolean> {
                        return Single.just(members)
                                .compose(RxUtil.applyBackgroundSchedulerToSingle(appSchedulers.diskScheduler()))
                                .flatMap { members ->
                                    for (member in members) {
                                        membersDao.insert(member)
                                    }
                                    Single.just(true)
                                }
                                .doOnSuccess { Timber.d(" Added members to persisted database") }

                    }
                })
                .memoryPolicy(MemoryPolicy
                        .builder()
                        .setExpireAfterWrite(150)
                        .setExpireAfterTimeUnit(TimeUnit.DAYS)
                        .build())
                .open()

    override fun getMembers(cursor: String?, includeLocale: Boolean, pageSize: Int, includePresence: Boolean): Single<List<Member>> {
        return usersStore.get(UsersRequest(cursor, includeLocale, pageSize, includePresence))
    }

    override fun getMember(id: String): Single<Member>{
        return membersDao.findMemberById(id)
                .compose(RxUtil.applyBackgroundSchedulerToSingle(appSchedulers.diskScheduler()))
                .subscribeOn(Schedulers.io())
    }


}