package rjornelas.course.instagram.add.view

import com.google.android.material.tabs.TabLayoutMediator
import rjornelas.course.instagram.R
import rjornelas.course.instagram.add.Add
import rjornelas.course.instagram.common.base.BaseFragment
import rjornelas.course.instagram.databinding.FragmentAddBinding

class AddFragment() : BaseFragment<FragmentAddBinding, Add.Presenter>(
    R.layout.fragment_add,
    FragmentAddBinding::bind
), Add.View {

    override lateinit var presenter: Add.Presenter


    override fun setupViews() {
        val tabLayout = binding?.addTab
        val viewPager = binding?.addViewpager
        val adapter = AddViewPagerAdapter(requireActivity())
        viewPager?.adapter = adapter

        if(tabLayout != null && viewPager != null){
            TabLayoutMediator(tabLayout, viewPager){tab, position ->
                tab.text = getString(adapter.tabs[position])
            }.attach()
        }
    }

    override fun setupPresenter() {
    }
}