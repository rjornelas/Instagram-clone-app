package rjornelas.course.instagram.login.view

import android.os.Bundle
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import rjornelas.course.instagram.common.view.util.TxtWatcher
import rjornelas.course.instagram.databinding.ActivityLoginBinding
import rjornelas.course.instagram.login.Login
import rjornelas.course.instagram.login.presentation.LoginPresenter

class LoginActivity : AppCompatActivity(), Login.View {

    private lateinit var binding: ActivityLoginBinding

    override lateinit var presenter: Login.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)


        setContentView(binding.root)

        presenter = LoginPresenter(this)

        with(binding) {
            edtRegisterEmail.addTextChangedListener(watcher)
            edtRegisterPassword.addTextChangedListener(watcher)

            edtRegisterEmail.addTextChangedListener(TxtWatcher{
                displayEmailFailure(null)
            })
            edtRegisterPassword.addTextChangedListener(TxtWatcher{
                displayPassFailure(null)
            })

            btnRegisterNext.setOnClickListener {
                presenter.login(
                    edtRegisterEmail.text.toString(),
                    edtRegisterPassword.text.toString()
                )
            }
        }
    }

    private val watcher = TxtWatcher {
        binding.btnRegisterNext.isEnabled = binding.edtRegisterEmail.text.toString()
            .isNotEmpty() && binding.edtRegisterPassword.text.toString().isNotEmpty()
    }

    override fun showProgress(enabled: Boolean) {
        binding.btnRegisterNext.showProgress(true)
    }

    override fun displayEmailFailure(emailError: Int?) {
        binding.registerEditEmailInput.error = emailError?.let { getString(it) }
    }

    override fun displayPassFailure(passwordError: Int?) {
        binding.registerEditPasswordInput.error = passwordError?.let { getString(it) }
    }

    override fun onUserAuthenticated() {
    }

    override fun onUserUnauthorized() {
    }
}