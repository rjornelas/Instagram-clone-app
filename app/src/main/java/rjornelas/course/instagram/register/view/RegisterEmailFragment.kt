package rjornelas.course.instagram.register.view

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import rjornelas.course.instagram.R
import rjornelas.course.instagram.common.base.DependencyInjector
import rjornelas.course.instagram.common.util.TxtWatcher
import rjornelas.course.instagram.databinding.FragmentRegisterEmailBinding
import rjornelas.course.instagram.register.RegisterEmail
import rjornelas.course.instagram.register.presentation.RegisterEmailPresenter

class RegisterEmailFragment : Fragment(R.layout.fragment_register_email), RegisterEmail.View {
    private var binding: FragmentRegisterEmailBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null

    override lateinit var presenter: RegisterEmail.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterEmailBinding.bind(view)

        val repository = DependencyInjector.registerEmailRepository()
        presenter = RegisterEmailPresenter(this, repository)

        binding?.let {
            with(it) {

                when (resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
                    Configuration.UI_MODE_NIGHT_YES -> {
                        imgProfile.imageTintList = ColorStateList.valueOf(Color.WHITE)
                    }

                    Configuration.UI_MODE_NIGHT_NO -> {
                    }
                }

                registerTxtLogin.setOnClickListener {
                    activity?.finish()
                }

                btnRegisterNext.setOnClickListener {
                    presenter.create(
                        edtRegisterEmail.text.toString()
                    )
                }

                edtRegisterEmail.addTextChangedListener(watcher)
                edtRegisterEmail.addTextChangedListener(TxtWatcher {
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
        fragmentAttachListener?.goToNameAndPasswordScreen(email)
    }

    override fun displayEmailFailure(emailError: Int?) {
        binding?.registerEditEmailInput?.error = emailError?.let { getString(it) }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener) {
            fragmentAttachListener = context
        }
    }

    override fun onDestroy() {
        binding = null
        fragmentAttachListener = null
        presenter.onDestroy()
        super.onDestroy()
    }

    private val watcher = TxtWatcher {
        binding?.btnRegisterNext?.isEnabled =
            binding?.edtRegisterEmail?.text.toString().isNotEmpty()
    }
}