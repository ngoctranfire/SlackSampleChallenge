package com.ngoctranfire.slacksamplechallenge.domain.persistence.model

import com.google.gson.annotations.SerializedName

/**
 * Created by ngoctranfire on 9/23/17.
 */
data class UsersResult(
        val ok: Boolean,
        val members: List<Member>,
        @SerializedName("cache_ts") private val cacheTs: Long,
        @SerializedName("response_metadata") private val responseMetaData: ResponseMetaData
)
