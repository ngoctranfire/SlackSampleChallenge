package com.ngoctranfire.slacksamplechallenge.ui.users

import com.ngoctranfire.slacksamplechallenge.domain.persistence.model.Member
import com.ngoctranfire.slacksamplechallenge.domain.persistence.model.Profile
import com.ngoctranfire.slacksamplechallenge.domain.repository.UsersRepo
import com.ngoctranfire.slacksamplechallenge.ui.users.viewstate.UserListingDisplayState
import io.reactivex.Single
import io.reactivex.subscribers.TestSubscriber
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.util.concurrent.TimeUnit

/**
 * Created by ngoctranfire on 9/28/17.
 */
class UsersListingViewModelTest {

    private lateinit var mockRepo: UsersRepo
    private var usersListingViewModel: UsersListingViewModel? = null

    @Before
    fun setup() {
        mockRepo = Mockito.mock(UsersRepo::class.java)
    }

    @After
    fun tearDown() {
        usersListingViewModel = null
    }


    @Test
    fun testUserDisplayStateSuccess() {
        val profile = Profile("email", "avatar_hash", "status_text",
        "profile_real_name", "display_name", "real_name_normalized",
        "display_name_normalized", "image_48", "image_72", "image_192", "image_512")
        val members: List<Member> = listOf(
                Member("id", "teamId", false,
                        "color", "name", "real_name",
                        "tz", "tz_label", profile, false, false, false,
                        false, false, false, 1232L,
                        true, true, "presence", "locale")
        )
        `when`(mockRepo.getMembers()).thenReturn(Single.just(members))

        usersListingViewModel  = UsersListingViewModel(mockRepo)

        val testSubscriber: TestSubscriber<UserListingDisplayState> = TestSubscriber()

        usersListingViewModel!!.getUserListingDisplayState()
                .subscribe(testSubscriber)

        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS)

        assertTrue(testSubscriber.events[0].size == 1)
        val displayState: UserListingDisplayState = testSubscriber.events[0][1] as UserListingDisplayState
    }


}
