package rjornelas.course.instagram.login.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import rjornelas.course.instagram.R
import rjornelas.course.instagram.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)


        setContentView(binding.root)

        binding.edtRegisterEmail.addTextChangedListener(watcher)
        binding.edtRegisterPassword.addTextChangedListener(watcher)

        binding.btnRegisterNext.setOnClickListener {

            binding.btnRegisterNext.showProgress(true)

            if (binding.edtRegisterEmail.text.isNullOrEmpty()) {
                binding.registerEditEmailInput.error = "Invalid email"
            }
            if (binding.edtRegisterPassword.text.isNullOrEmpty()) {
                binding.registerEditPasswordInput.error = "Invalid password"
            }

            Handler(Looper.getMainLooper()).postDelayed({
                binding.btnRegisterNext.showProgress(false)
            }, 2000)
        }
    }

    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.btnRegisterNext.isEnabled = p0.toString().isNotEmpty()
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }
}