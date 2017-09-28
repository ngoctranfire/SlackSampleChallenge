package com.ngoctranfire.slacksamplechallenge.navigation

import android.app.Activity
import com.ngoctranfire.slacksamplechallenge.ui.profile.ProfilePopupDialogFragment
import com.ngoctranfire.slacksamplechallenge.ui.users.model.UserProfileDisplayModel

/**
 * Created by ngoctranfire on 9/23/17.
 */

class NavigationController: NavigationRouter {
    override fun openPopupProfileFragment(fromActivity: Activity, userProfileDisplayModel: UserProfileDisplayModel): ProfilePopupDialogFragment {
        val fragmentManager: android.app.FragmentManager = fromActivity.fragmentManager!!
        val popupFragment: ProfilePopupDialogFragment =  ProfilePopupDialogFragment.newInstance(userProfileDisplayModel)
        popupFragment.show(fragmentManager, "showing user")
        return popupFragment
    }

}
