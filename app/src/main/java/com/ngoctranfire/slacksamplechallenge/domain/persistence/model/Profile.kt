package com.ngoctranfire.slacksamplechallenge.domain.persistence.model

import com.google.gson.annotations.SerializedName

/**
 * Created by ngoctranfire on 9/23/17.
 */
data class Profile(
        val email: String?,
        val avatar_hash: String?,
        val status_text: String?,
        val status_emoji: String?,
        @SerializedName("real_name")
        val profile_real_name: String?,
        val display_name: String,
        val real_name_normalized: String?,
        val display_name_normalized: String?,
        val image_48: String?,
        val image_72: String,
        val image_192: String,
        val image_512: String?,
        val team: String?,
        val title: String?
)