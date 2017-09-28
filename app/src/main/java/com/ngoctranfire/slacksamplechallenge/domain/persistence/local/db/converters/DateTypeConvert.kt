package com.ngoctranfire.slacksamplechallenge.domain.persistence.local.db.converters

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * Created by ngoctranfire on 9/24/17.
 */
class DateTypeConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun toDate(value: Long?): Date? = if (value == null) null else Date(value)

        @TypeConverter
        @JvmStatic
        fun toLong(value: Date?): Long? = value?.time
    }

}