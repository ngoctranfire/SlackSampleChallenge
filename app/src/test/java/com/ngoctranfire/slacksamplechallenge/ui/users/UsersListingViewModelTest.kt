package com.ngoctranfire.slacksamplechallenge.ui.users

import com.ngoctranfire.slacksamplechallenge.domain.persistence.model.Member
import com.ngoctranfire.slacksamplechallenge.domain.persistence.model.Profile
import com.ngoctranfire.slacksamplechallenge.domain.repository.UsersDataSource
import com.ngoctranfire.slacksamplechallenge.ui.users.model.UserProfileDisplayModel
import com.ngoctranfire.slacksamplechallenge.ui.users.viewstate.UserListingDisplayState
import io.reactivex.Single
import io.reactivex.subscribers.TestSubscriber
import junit.framework.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

/**
 * Created by ngoctranfire on 9/28/17.
 */
class UsersListingViewModelTest {

    private lateinit var mockRepo: UsersDataSource
    private var usersListingViewModel: UsersListingViewModel? = null

    @Before
    fun setup() {
        mockRepo = Mockito.mock(UsersDataSource::class.java)
    }

    @After
    fun tearDown() {
        usersListingViewModel = null
    }


    @Test
    fun testUserDisplayStateSuccess() {
        val profile = Profile("email", "avatar_hash", "status_text",
        "status_emoji", "profile_real_name", "display_name",
        "real_name_normalized", "display_name_normalized",
                "image_48", "image_72", "image_192",
                "image_512", "team", "title")
        val member = Member("id", "teamId", false,
                "color", "name", "real_name",
                "tz", "tz_label", profile, false, false, false,
                false, false, false, 1232L,
                true, true, "presence", "locale")
        val members: List<Member> = listOf(member)
        `when`(mockRepo.getMembers()).thenReturn(Single.just(members))

        usersListingViewModel  = UsersListingViewModel(mockRepo)

        val testSubscriber: TestSubscriber<UserListingDisplayState> = TestSubscriber()

        usersListingViewModel!!.getUserListingDisplayState()
                .subscribe(testSubscriber)

        testSubscriber.awaitCount(1)

        assertTrue(testSubscriber.events[0].size == 1)
        val userDisplayViewModel: UserProfileDisplayModel = UserProfileDisplayModel(member.id,
                member.profile.image_72, member.real_name, member.profile.display_name,
                member.profile.title, member.presence!!, member.profile.image_512!!,
                member.tz_label, member.profile.email!!)

        val displayState: UserListingDisplayState = testSubscriber.events[0][0] as UserListingDisplayState
        assertEquals("userDisplayViewModels should be equal", userDisplayViewModel, displayState.userProfileDisplayModels!![0])
        assertNull("should not have errors", displayState.error)
        assertEquals("should not be loading", false, displayState.loadingInProgress)

    }

    @Test
    fun testUserDisplayError() {
        val throwable = Throwable("Some Error")
        `when`(mockRepo.getMembers()).thenReturn(Single.error(throwable))
        usersListingViewModel  = UsersListingViewModel(mockRepo)

        val testSubscriber: TestSubscriber<UserListingDisplayState> = TestSubscriber()

        usersListingViewModel!!.getUserListingDisplayState()
                .subscribe(testSubscriber)
        testSubscriber.awaitCount(1)

        val displayState: UserListingDisplayState = testSubscriber.events[0][0] as UserListingDisplayState
        assertNull("UserDisplayModels should be null", displayState.userProfileDisplayModels)
        assertEquals("Should be same throwable thrown", throwable, displayState.error)
        assertFalse(displayState.loadingInProgress)
    }

}
