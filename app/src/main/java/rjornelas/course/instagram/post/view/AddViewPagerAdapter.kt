package rjornelas.course.instagram.post.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import rjornelas.course.instagram.R

class AddViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    val tabs = arrayOf(R.string.photo, R.string.gallery)

    override fun getItemCount(): Int {
        return tabs.size
    }

    override fun createFragment(position: Int): Fragment {
        return when(tabs[position]){
            R.string.photo -> CameraFragment()
            R.string.gallery -> GalleryFragment()
            else -> throw IllegalArgumentException("Fragment not found")
        }
    }
}