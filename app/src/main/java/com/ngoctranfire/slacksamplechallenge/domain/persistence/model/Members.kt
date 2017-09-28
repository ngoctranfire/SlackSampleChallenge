package com.ngoctranfire.slacksamplechallenge.domain.persistence.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by ngoctranfire on 9/23/17.
 */
@Entity(tableName = "members")
data class Member(
        @PrimaryKey
        val id: String,
        val team_id: String?,
        val deleted: Boolean?,
        val color: String?,
        val name: String,
        val real_name: String,
        val tz: String?,
        val tz_label: String,
        @Embedded val profile: Profile,
        val is_admin: Boolean?,
        val is_owner: Boolean?,
        val is_primary_owner: Boolean?,
        val is_restricted: Boolean?,
        val is_ultra_restricted: Boolean?,
        val is_bot: Boolean?,
        val updated: Long?,
        val is_app_user: Boolean?,
        val has_2fa: Boolean?,
        val presence: String?,
        val locale: String?
)