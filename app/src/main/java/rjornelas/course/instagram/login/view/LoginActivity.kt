package rjornelas.course.instagram.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import rjornelas.course.instagram.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val edtTextEmail = findViewById<TextInputEditText>(R.id.edt_login_email)
        val edtTextPassword = findViewById<TextInputEditText>(R.id.edt_login_password)

        edtTextEmail.addTextChangedListener(watcher)
        edtTextPassword.addTextChangedListener(watcher)

        val buttonEnter = findViewById<LoadingButton>(R.id.btn_login)
        buttonEnter.setOnClickListener {

            buttonEnter.showProgress(true)

            if (edtTextEmail.text.isNullOrEmpty()) {
                findViewById<TextInputLayout>(R.id.login_edit_email_input).error = "Invalid email"
            }
            if (edtTextPassword.text.isNullOrEmpty()) {
                findViewById<TextInputLayout>(R.id.login_edit_password_input).error =
                    "Invalid password"
            }

            Handler(Looper.getMainLooper()).postDelayed({
                buttonEnter.showProgress(false)
            }, 2000)
        }
    }

    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            findViewById<LoadingButton>(R.id.btn_login).isEnabled = p0.toString().isNotEmpty()
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }
}