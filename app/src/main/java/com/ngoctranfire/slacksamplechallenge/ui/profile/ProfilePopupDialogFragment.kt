package com.ngoctranfire.slacksamplechallenge.ui.profile

import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ngoctranfire.slacksamplechallenge.R
import com.ngoctranfire.slacksamplechallenge.ui.users.model.UserProfileDisplayModel
import kotlinx.android.synthetic.main.fragment_user_profile.*
import timber.log.Timber

/**
 * Created by ngoctranfire on 9/27/17.
 */
class ProfilePopupDialogFragment: DialogFragment() {

    lateinit private var userProfileModel: UserProfileDisplayModel;

    companion object {
        const val KEY_USER_PROFILE_MODEL = "KEY_USER_PROFILE_MODEL"
        fun newInstance(userProfileDisplayModel: UserProfileDisplayModel): ProfilePopupDialogFragment {
            val fragment = ProfilePopupDialogFragment()
            val args = Bundle(1)
            args.putParcelable(KEY_USER_PROFILE_MODEL, userProfileDisplayModel)

            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments ?: throw IllegalStateException("Popup should be instantiated with correct userId")
        userProfileModel = args.getParcelable<UserProfileDisplayModel>(KEY_USER_PROFILE_MODEL)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.fragment_user_profile, container)
        Timber.i("PopupDialogFragment#onCreateView()")

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return view
    }


    private fun updateProfileView() {

        Glide.with(this)
                .load(userProfileModel.fullImageUri)
                .transition(DrawableTransitionOptions().crossFade())
                .into(profile_image)

        val naString = getString(R.string.not_available)

        real_name.text = userProfileModel.realName
        time_zone_value.text = userProfileModel.timeZone
        display_name_value.text = userProfileModel.displayName
        email_value.text = if (userProfileModel.email.isBlank()) naString else userProfileModel.email
        job_title_value.text = userProfileModel.title?: naString
    }

    override fun onResume() {
        super.onResume()
        val params = dialog.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog.window!!.attributes = params as android.view.WindowManager.LayoutParams
        updateProfileView()
    }
}