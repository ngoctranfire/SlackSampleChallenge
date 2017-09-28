package com.ngoctranfire.slacksamplechallenge.ui.users.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.ngoctranfire.slacksamplechallenge.R
import com.ngoctranfire.slacksamplechallenge.ui.users.model.UserProfileDisplayModel
import kotlinx.android.synthetic.main.user_row.view.*
import jp.wasabeef.glide.transformations.RoundedCornersTransformation



/**
 * Created by ngoctranfire on 9/25/17.
 */
class UsersListingAdapter(private val listener: UserPressListener) : RecyclerView.Adapter<UserModelViewHolder>() {

    private var userProfileDisplayModels: List<UserProfileDisplayModel>
    private val viewTypeMap: SparseArrayCompat<UserProfileDisplayModel>

    init {
        userProfileDisplayModels = ArrayList()
        viewTypeMap = SparseArrayCompat()
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UserModelViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.user_row, parent, false)
        return UserModelViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserModelViewHolder, position: Int) {
        holder.bind(this.userProfileDisplayModels[position], listener)
    }

    override fun getItemCount(): Int {
        return userProfileDisplayModels.size
    }

    fun updateUserDisplayModels(userProfileDisplayModels: List<UserProfileDisplayModel>) {
        this.userProfileDisplayModels = userProfileDisplayModels
        notifyDataSetChanged()
    }

}

interface UserPressListener {
    fun onUserClicked(userProfileDisplayModel: UserProfileDisplayModel)
}

class UserModelViewHolder(perUserView: View): RecyclerView.ViewHolder(perUserView) {
    fun bind(userProfileDisplayModel: UserProfileDisplayModel, pressListener: UserPressListener) {
        itemView.setOnClickListener({ pressListener.onUserClicked(userProfileDisplayModel) })
        val requestOptions: RequestOptions = RequestOptions()
                .transforms(RoundedCornersTransformation(itemView.context.resources.getDimensionPixelSize(R.dimen.user_image_padding),
                        itemView.context.resources.getDimensionPixelSize(R.dimen.user_image_margins), RoundedCornersTransformation.CornerType.ALL), CenterCrop())

        Glide.with(itemView)
                .load(userProfileDisplayModel.image)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(itemView.user_image)

        itemView.real_name.text = userProfileDisplayModel.realName
        itemView.display_name.text = userProfileDisplayModel.displayName
        itemView.status.text = userProfileDisplayModel.status

        if (userProfileDisplayModel.title == null) {
            itemView.user_title.visibility = View.GONE
        } else {
            itemView.user_title.text = userProfileDisplayModel.title
            itemView.user_title.visibility = View.VISIBLE
        }
    }
}