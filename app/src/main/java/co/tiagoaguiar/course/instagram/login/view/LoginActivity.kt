package co.tiagoaguiar.course.instagram.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import co.tiagoaguiar.course.instagram.R
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

    findViewById<Button>(R.id.btn_login).setOnClickListener{
      if(edtTextEmail.text.isNullOrEmpty()){
        findViewById<TextInputLayout>(R.id.login_edit_email_input).error = "Invalid email"
      }
      if(edtTextPassword.text.isNullOrEmpty()){
        findViewById<TextInputLayout>(R.id.login_edit_password_input).error = "Invalid password"
      }
    }
  }

  private val watcher = object : TextWatcher{
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
      findViewById<Button>(R.id.btn_login).isEnabled = p0.toString().isNotEmpty()
    }

    override fun afterTextChanged(p0: Editable?) {
    }
  }
}