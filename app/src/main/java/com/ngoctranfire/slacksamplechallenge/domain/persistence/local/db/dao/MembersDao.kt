package com.ngoctranfire.slacksamplechallenge.domain.persistence.local.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.ngoctranfire.slacksamplechallenge.domain.persistence.model.Member
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by ngoctranfire on 9/24/17.
 */
@Dao
interface MembersDao {
    @Query("SELECT * FROM members")
    fun getMembers(): Flowable<List<Member>>

    @Query("SELECT * FROM members WHERE id=:id")
    fun findMemberById(id: String): Single<Member>

    @Insert
    fun insert(member: Member)
}