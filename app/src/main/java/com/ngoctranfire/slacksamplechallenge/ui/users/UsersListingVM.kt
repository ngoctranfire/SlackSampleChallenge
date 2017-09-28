package com.ngoctranfire.slacksamplechallenge.ui.users

import android.arch.lifecycle.ViewModel
import com.ngoctranfire.slacksamplechallenge.ui.users.viewstate.UserListingDisplayState
import io.reactivex.Flowable

/**
 * Created by ngoctranfire on 9/23/17.
 */
abstract class UsersListingVM: ViewModel() {
    abstract fun getUserListingDisplayState(): Flowable<UserListingDisplayState>
}

