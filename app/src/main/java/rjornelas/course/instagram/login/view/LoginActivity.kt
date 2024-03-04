package rjornelas.course.instagram.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import rjornelas.course.instagram.common.base.DependencyInjector
import rjornelas.course.instagram.common.util.TxtWatcher
import rjornelas.course.instagram.databinding.ActivityLoginBinding
import rjornelas.course.instagram.login.Login
import rjornelas.course.instagram.login.presentation.LoginPresenter
import rjornelas.course.instagram.main.view.MainActivity
import rjornelas.course.instagram.register.view.RegisterActivity

class LoginActivity : AppCompatActivity(), Login.View {

    private lateinit var binding: ActivityLoginBinding

    override lateinit var presenter: Login.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)


        setContentView(binding.root)

        presenter = LoginPresenter(this, DependencyInjector.loginRepository())

        with(binding) {
            edtRegisterEmail.addTextChangedListener(watcher)
            edtRegisterPassword.addTextChangedListener(watcher)

            edtRegisterEmail.addTextChangedListener(TxtWatcher {
                displayEmailFailure(null)
            })
            edtRegisterPassword.addTextChangedListener(TxtWatcher {
                displayPassFailure(null)
            })

            btnRegisterNext.setOnClickListener {
                presenter.login(
                    edtRegisterEmail.text.toString(),
                    edtRegisterPassword.text.toString()
                )
            }

            registerTxtLogin.setOnClickListener{
                goToRegisterScreen()
            }
        }
    }

    private fun goToRegisterScreen() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private val watcher = TxtWatcher {
        binding.btnRegisterNext.isEnabled = binding.edtRegisterEmail.text.toString()
            .isNotEmpty() && binding.edtRegisterPassword.text.toString().isNotEmpty()
    }

    override fun showProgress(enabled: Boolean) {
        binding.btnRegisterNext.showProgress(enabled)
    }

    override fun displayEmailFailure(emailError: Int?) {
        binding.registerEditEmailInput.error = emailError?.let { getString(it) }
    }

    override fun displayPassFailure(passwordError: Int?) {
        binding.registerEditPasswordInput.error = passwordError?.let { getString(it) }
    }

    override fun onUserAuthenticated() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun onUserUnauthorized(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}