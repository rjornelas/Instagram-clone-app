package rjornelas.course.instagram.main.view

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import rjornelas.course.instagram.R
import rjornelas.course.instagram.camera.view.CameraFragment
import rjornelas.course.instagram.common.replaceFragment
import rjornelas.course.instagram.databinding.ActivityMainBinding
import rjornelas.course.instagram.home.view.FragmentHome
import rjornelas.course.instagram.profile.view.ProfileFragment
import rjornelas.course.instagram.search.view.SearchFragment

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var homeFragment: Fragment
    private lateinit var searchFragment: Fragment
    private lateinit var cameraFragment: Fragment
    private lateinit var profileFragment: Fragment
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
            window.statusBarColor = ContextCompat.getColor(this, R.color.gray)
        }

        setSupportActionBar(binding.mainToolbar)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_insta_camera)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        homeFragment = FragmentHome()
        searchFragment = SearchFragment()
        cameraFragment = CameraFragment()
        profileFragment = ProfileFragment()

        binding.mainBottomNav.setOnNavigationItemSelectedListener(this)
        binding.mainBottomNav.selectedItemId = R.id.menu_bottom_home
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_bottom_home -> {
                if(currentFragment == homeFragment) return false
                currentFragment = homeFragment
            }

            R.id.menu_bottom_search -> {
                if(currentFragment == searchFragment) return false
                currentFragment = searchFragment
            }

            R.id.menu_bottom_add -> {
                if(currentFragment == cameraFragment) return false
                currentFragment = cameraFragment
            }

            R.id.menu_bottom_profile -> {
                if(currentFragment == profileFragment) return false
                currentFragment = profileFragment
            }
        }
        currentFragment?.let {
            replaceFragment(R.id.main_fragment, it)
        }
        return true
    }

}