package com.ngoctranfire.slacksamplechallenge.ui.users

import com.ngoctranfire.slacksamplechallenge.domain.repository.UsersRepo
import com.ngoctranfire.slacksamplechallenge.ui.users.model.UserProfileDisplayModel
import com.ngoctranfire.slacksamplechallenge.ui.users.viewstate.UserListingDisplayState
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by ngoctranfire on 9/23/17.
 */

/**
 * Ensures that we are making sure only one
 */
class UsersListingViewModel @Inject constructor(
        private val usersRepo: UsersRepo
): UsersListingVM() {

    private val listingDisplayStateSubject: BehaviorSubject<UserListingDisplayState> = BehaviorSubject.createDefault(UserListingDisplayState.DEFAULT())

    private val listingDisplayStream: Flowable<UserListingDisplayState> by lazy {
        listingDisplayStateSubject.serialize().toFlowable(BackpressureStrategy.LATEST)
    }

    init {
        Timber.v("Instantiated the viewmodel for the activity. This should happen only once per activity instance")
        fetchTeamMembers()
    }

    override fun getUserListingDisplayState(): Flowable<UserListingDisplayState> {
        return listingDisplayStream
    }

    private fun fetchTeamMembers() {
        usersRepo.getMembers()
                .toFlowable()
                .flatMap { members ->
                    val userProfileDisplayModels: MutableList<UserProfileDisplayModel> = mutableListOf()
                    members.mapTo(userProfileDisplayModels) {
                        UserProfileDisplayModel(it.id, it.profile.image_72, it.real_name, it.profile.display_name,
                                it.profile.title,it.presence?:"",
                                it.profile.image_512?: it.profile.image_192,
                                it.tz_label, it.profile.email?:"")
                    }
                    Flowable.just(UserListingDisplayState.SUCCESS(userProfileDisplayModels))
                }
                .subscribe(listingDisplayStateSubject::onNext, {
                    error -> Timber.e(error, "Error trying to fetch team members")
                    listingDisplayStateSubject.onNext(UserListingDisplayState.ERROR(error))
                })
    }

}
