package com.ngoctranfire.slacksamplechallenge.ui.users

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.ngoctranfire.slacksamplechallenge.R
import com.ngoctranfire.slacksamplechallenge.ext.into
import com.ngoctranfire.slacksamplechallenge.navigation.NavigationRouter
import com.ngoctranfire.slacksamplechallenge.ui.base.BaseActivity
import com.ngoctranfire.slacksamplechallenge.ui.decorations.DividerItemDecoration
import com.ngoctranfire.slacksamplechallenge.ui.decorations.SpaceItemDecoration
import com.ngoctranfire.slacksamplechallenge.ui.users.adapter.UserPressListener
import com.ngoctranfire.slacksamplechallenge.ui.users.adapter.UsersListingAdapter
import com.ngoctranfire.slacksamplechallenge.ui.users.model.UserProfileDisplayModel
import com.ngoctranfire.slacksamplechallenge.ui.viewmodel.ViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_users_listing.*
import javax.inject.Inject



/**
 * Created by ngoctranfire on 9/23/17.
 */
class UsersListingActivity : BaseActivity(), UserPressListener{
    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var router: NavigationRouter

    lateinit var userVM: UsersListingVM
    private lateinit var adapter: UsersListingAdapter

    private val bin: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_listing)
        setupRecyclerView()
        bindVM()
    }

    private fun setupRecyclerView() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        users_list_recycler_view.layoutManager = layoutManager
        adapter = UsersListingAdapter(this)
        users_list_recycler_view.adapter = adapter
        users_list_recycler_view.addItemDecoration(DividerItemDecoration(this))
        val space = resources.getDimensionPixelSize(R.dimen.user_row_spacing)
        users_list_recycler_view.addItemDecoration(SpaceItemDecoration(0, space, 0, space))
    }

    private fun bindVM() {
        userVM = ViewModelProviders.of(this, viewModelFactory).get(UsersListingViewModel::class.java)

        userVM.getUserListingDisplayState()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onNext = { state ->
                    if (state.error != null) {
                        Toast.makeText(this, R.string.error_fetch, Toast.LENGTH_LONG).show()
                    }
                    state.userProfileDisplayModels?.apply {
                        adapter.updateUserDisplayModels(state.userProfileDisplayModels)
                    }
                    progress_bar.visibility = if (state.loadingInProgress) View.VISIBLE else View.GONE
                }).into(bin)
    }

    override fun onUserClicked(userProfileDisplayModel: UserProfileDisplayModel) {
        router.openPopupProfileFragment(this, userProfileDisplayModel)
    }


    override fun onDestroy() {
        super.onDestroy()
        bin.dispose()
    }
}