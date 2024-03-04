package rjornelas.course.instagram.register.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import rjornelas.course.instagram.R
import rjornelas.course.instagram.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = RegisterEmailFragment()

        supportFragmentManager.beginTransaction().apply {
            add(R.id.register_fragment, fragment)
            commit()
        }
    }
}