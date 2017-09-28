package com.ngoctranfire.slacksamplechallenge.navigation

import android.app.Activity
import com.ngoctranfire.slacksamplechallenge.ui.profile.ProfilePopupDialogFragment
import com.ngoctranfire.slacksamplechallenge.ui.users.model.UserProfileDisplayModel

/**
 * Created by ngoctranfire on 9/23/17.
 */

interface NavigationRouter {
    fun openPopupProfileFragment(fromActivity: Activity, userProfileDisplayModel: UserProfileDisplayModel): ProfilePopupDialogFragment
}
