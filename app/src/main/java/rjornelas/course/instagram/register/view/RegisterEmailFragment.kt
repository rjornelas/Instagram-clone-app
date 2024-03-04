package rjornelas.course.instagram.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import rjornelas.course.instagram.R
import rjornelas.course.instagram.common.base.DependencyInjector
import rjornelas.course.instagram.common.util.TxtWatcher
import rjornelas.course.instagram.databinding.FragmentRegisterEmailBinding
import rjornelas.course.instagram.register.RegisterEmail
import rjornelas.course.instagram.register.presentation.RegisterEmailPresenter

class RegisterEmailFragment : Fragment(R.layout.fragment_register_email), RegisterEmail.View {
    private var binding: FragmentRegisterEmailBinding? = null

    override lateinit var presenter: RegisterEmail.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterEmailBinding.bind(view)

        val repository = DependencyInjector.registerEmailRepository()
        presenter = RegisterEmailPresenter(this, repository)

        binding?.let {
            with(it){
                registerTxtLogin.setOnClickListener{
                    activity?.finish()
                }

                btnRegisterNext.setOnClickListener{
                    presenter.create(
                        edtRegisterEmail.text.toString()
                    )
                }

                edtRegisterEmail.addTextChangedListener(watcher)
                edtRegisterEmail.addTextChangedListener(TxtWatcher{
                    displayEmailFailure(null)
                })
            }
        }
    }

    override fun showProgress(enabled: Boolean) {
        binding?.btnRegisterNext?.showProgress(enabled)
    }

    override fun onEmailFailure(message: String) {
        binding?.registerEditEmailInput?.error = message
    }

    override fun goToNameAndPasswordScreen(email: String) {
       //todo
    }

    override fun displayEmailFailure(emailError: Int?){
        binding?.registerEditEmailInput?.error = emailError?.let { getString(it) }
    }

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }

    private val watcher = TxtWatcher {
        binding?.btnRegisterNext?.isEnabled = binding?.edtRegisterEmail?.text.toString().isNotEmpty()
    }
}