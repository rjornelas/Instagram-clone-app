package rjornelas.course.instagram.profile.view

import android.content.Context
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import rjornelas.course.instagram.R
import rjornelas.course.instagram.common.base.BaseFragment
import rjornelas.course.instagram.common.base.DependencyInjector
import rjornelas.course.instagram.common.model.Post
import rjornelas.course.instagram.common.model.User
import rjornelas.course.instagram.common.model.UserAuth
import rjornelas.course.instagram.databinding.FragmentProfileBinding
import rjornelas.course.instagram.main.LogoutListener
import rjornelas.course.instagram.profile.Profile
import rjornelas.course.instagram.profile.presentation.ProfilePresenter

class ProfileFragment : BaseFragment<FragmentProfileBinding, Profile.Presenter>(
    R.layout.fragment_profile,
    FragmentProfileBinding::bind
), Profile.View, BottomNavigationView.OnNavigationItemSelectedListener {

    private val adapter = PostAdapter()
    private var uuid: String? = null

    private var logoutListener: LogoutListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is LogoutListener) {
            logoutListener = context
        }
    }
    override fun showProgress(enabled: Boolean) {
        binding?.profileProgress?.visibility = if(enabled) View.VISIBLE else View.GONE
    }

    override fun setupPresenter() {
        val repository = DependencyInjector.profileRepository()
        presenter = ProfilePresenter(this, repository)
    }

    override fun setupViews() {
        uuid = arguments?.getString(KEY_USER_ID)

        binding?.profileRv?.layoutManager = GridLayoutManager(requireContext(), 3)
        binding?.profileRv?.adapter = adapter
        binding?.profileNavTabs?.setOnNavigationItemSelectedListener(this)

        binding?.profileBtnEditProfile?.setOnClickListener{
            if(it.tag == true){
                binding?.profileBtnEditProfile?.text = getString(R.string.follow)
                binding?.profileBtnEditProfile?.tag = false
                presenter.followUser(uuid, false)
            }else if(it.tag == false){
                binding?.profileBtnEditProfile?.text = getString(R.string.unfollow)
                binding?.profileBtnEditProfile?.tag = true
                presenter.followUser(uuid, true)
            }
        }

        presenter.fetchUserProfile(uuid)
    }

    override fun displayUserProfile(user: Pair<User, Boolean?>) {

        val (userAuth, following) = user

        binding?.profileTxtPostsCount?.text = userAuth.postCount.toString()
        binding?.profileTxtFollowersCount?.text = userAuth.followers.toString()
        binding?.profileTxtFollowingCount?.text = userAuth.following.toString()
        binding?.profileTxtUsername?.text = userAuth.name
        binding?.profileTxtBio?.text = "TODO"

        binding?.let {
            Glide.with(requireContext()).load(userAuth.photoUrl).into(it.profileImgIcon)
        }

        presenter.fetchUserPosts(uuid)

        binding?.profileBtnEditProfile?.text = when(following){
            null -> getString(R.string.edit_profile)
            true -> getString(R.string.unfollow)
            false -> getString(R.string.follow)
        }

        binding?.profileBtnEditProfile?.tag = following
    }

    override fun displayRequestFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun displayEmptyPosts() {
        binding?.txtProfileEmpty?.visibility = View.VISIBLE
        binding?.profileRv?.visibility = View.GONE
    }

    override fun displayFullPosts(posts: List<Post>) {
        binding?.txtProfileEmpty?.visibility = View.GONE
        binding?.profileRv?.visibility = View.VISIBLE
        adapter.items = posts
        adapter.notifyDataSetChanged()
    }

    override lateinit var presenter: Profile.Presenter

    override fun getMenu(): Int {
        return R.menu.menu_profile
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_profile_grid -> {
                binding?.profileRv?.layoutManager = GridLayoutManager(requireContext(), 3)
            }
            R.id.menu_profile_list -> {
                binding?.profileRv?.layoutManager = LinearLayoutManager(requireContext())
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_logout -> {
                logoutListener?.logout()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object{
        const val KEY_USER_ID = "key_user_id"
    }
}