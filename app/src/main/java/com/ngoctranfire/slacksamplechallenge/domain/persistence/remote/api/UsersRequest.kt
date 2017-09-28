package com.ngoctranfire.slacksamplechallenge.domain.persistence.remote.api

/**
 * Created by ngoctranfire on 9/24/17.
 */
data class UsersRequest(val cursor: String? = null, val includeLocale: Boolean = true,
                        val pageSize: Int = 200, val presence: Boolean = true)