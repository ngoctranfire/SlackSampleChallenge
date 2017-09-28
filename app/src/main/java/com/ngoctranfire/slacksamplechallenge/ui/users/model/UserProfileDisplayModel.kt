package com.ngoctranfire.slacksamplechallenge.ui.users.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by ngoctranfire on 9/25/17.
 */
data class UserProfileDisplayModel(val userId: String,
                                   val image: String,
                                   val realName: String,
                                   val displayName: String,
                                   val title: String?,
                                   val status: String,
                                   val fullImageUri: String,
                                   val timeZone: String,
                                   val email: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userId)
        parcel.writeString(image)
        parcel.writeString(realName)
        parcel.writeString(displayName)
        parcel.writeString(title)
        parcel.writeString(status)
        parcel.writeString(fullImageUri)
        parcel.writeString(timeZone)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserProfileDisplayModel> {
        override fun createFromParcel(parcel: Parcel): UserProfileDisplayModel {
            return UserProfileDisplayModel(parcel)
        }

        override fun newArray(size: Int): Array<UserProfileDisplayModel?> {
            return arrayOfNulls(size)
        }
    }
}