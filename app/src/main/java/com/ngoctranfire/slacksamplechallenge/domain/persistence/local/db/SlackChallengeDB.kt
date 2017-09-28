package com.ngoctranfire.slacksamplechallenge.domain.persistence.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.ngoctranfire.slacksamplechallenge.domain.persistence.local.db.converters.DateTypeConverter
import com.ngoctranfire.slacksamplechallenge.domain.persistence.local.db.dao.MembersDao
import com.ngoctranfire.slacksamplechallenge.domain.persistence.model.Member

/**
 * Created by ngoctranfire on 9/24/17.
 */
@Database(entities = arrayOf(Member::class), version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class SlackChallengeDB : RoomDatabase() {
    abstract fun membersDao(): MembersDao
}