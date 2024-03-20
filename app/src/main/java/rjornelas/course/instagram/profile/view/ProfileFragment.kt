package rjornelas.course.instagram.profile.view

import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import rjornelas.course.instagram.R
import rjornelas.course.instagram.common.base.BaseFragment
import rjornelas.course.instagram.common.base.DependencyInjector
import rjornelas.course.instagram.common.model.Post
import rjornelas.course.instagram.common.model.UserAuth
import rjornelas.course.instagram.databinding.FragmentProfileBinding
import rjornelas.course.instagram.profile.Profile
import rjornelas.course.instagram.profile.presentation.ProfilePresenter

class ProfileFragment : BaseFragment<FragmentProfileBinding, Profile.Presenter>(
    R.layout.fragment_profile,
    FragmentProfileBinding::bind
), Profile.View, BottomNavigationView.OnNavigationItemSelectedListener {

    private val adapter = PostAdapter()
    private var uuid: String? = null
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

        presenter.fetchUserProfile(uuid)
    }

    override fun displayUserProfile(user: Pair<UserAuth, Boolean?>) {

        val (userAuth, following) = user

        binding?.profileTxtPostsCount?.text = userAuth.postCount.toString()
        binding?.profileTxtFollowersCount?.text = userAuth.followersCount.toString()
        binding?.profileTxtFollowingCount?.text = userAuth.followingCount.toString()
        binding?.profileTxtUsername?.text = userAuth.name
        binding?.profileTxtBio?.text = "TODO"
        binding?.profileImgIcon?.setImageURI(userAuth.photoUri)
        presenter.fetchUserPosts(uuid)

        binding?.profileBtnEditProfile?.text = when(following){
            null -> getString(R.string.edit_profile)
            true -> getString(R.string.unfollow)
            false -> getString(R.string.follow)
        }
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

    companion object{
        const val KEY_USER_ID = "key_user_id"
    }
}