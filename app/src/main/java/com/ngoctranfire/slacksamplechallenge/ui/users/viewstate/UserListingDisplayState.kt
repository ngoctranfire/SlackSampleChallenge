package com.ngoctranfire.slacksamplechallenge.ui.users.viewstate

import com.ngoctranfire.slacksamplechallenge.ui.users.model.UserProfileDisplayModel

/**
 * Created by ngoctranfire on 9/25/17.
 */
data class UserListingDisplayState(val loadingInProgress: Boolean,
                                   val userProfileDisplayModels: List<UserProfileDisplayModel>?,
                                   val error: Throwable?) {
    companion object {
        fun DEFAULT(): UserListingDisplayState {
            return UserListingDisplayState(true, null, null)
        }
        fun SUCCESS(userProfileDisplayModels: List<UserProfileDisplayModel>): UserListingDisplayState {
            return UserListingDisplayState(false, userProfileDisplayModels, null)
        }
        fun ERROR(t: Throwable):UserListingDisplayState {
            return UserListingDisplayState(false, null, t)
        }
    }
}