package rjornelas.course.instagram.register.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import rjornelas.course.instagram.R
import rjornelas.course.instagram.common.base.DependencyInjector
import rjornelas.course.instagram.common.util.TxtWatcher
import rjornelas.course.instagram.databinding.FragmentRegisterNamePasswordBinding
import rjornelas.course.instagram.register.RegisterNameAndPassword
import rjornelas.course.instagram.register.presentation.RegisterNameAndPasswordPresenter
import java.lang.IllegalStateException

class RegisterNamePasswordFragment : Fragment(R.layout.fragment_register_name_password), RegisterNameAndPassword.View {

    private var binding: FragmentRegisterNamePasswordBinding? = null

    override lateinit var presenter: RegisterNameAndPassword.Presenter

    private var fragmentAttachListener: FragmentAttachListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterNamePasswordBinding.bind(view)

        val repository = DependencyInjector.registerEmailRepository()
        presenter = RegisterNameAndPasswordPresenter(this, repository)

        val email = arguments?.getString(KEY_EMAIL) ?: throw IllegalStateException("Email not found")

        binding?.let {
            with(it) {

                registerTxtLogin.setOnClickListener{
                    activity?.finish()
                }

                btnRegisterNamePassNext.setOnClickListener{
                    presenter.create(
                        email,
                        edtRegisterName.text.toString(),
                        edtRegisterPassword.text.toString(),
                        edtRegisterConfirmPass.text.toString()
                    )
                }

                edtRegisterName.addTextChangedListener(watcher)
                edtRegisterPassword.addTextChangedListener(watcher)
                edtRegisterConfirmPass.addTextChangedListener(watcher)

                edtRegisterName.addTextChangedListener(TxtWatcher {
                    displayNameFailure(null)
                })
                edtRegisterPassword.addTextChangedListener(TxtWatcher {
                    displayNameFailure(null)
                })
                edtRegisterConfirmPass.addTextChangedListener(TxtWatcher {
                    displayNameFailure(null)
                })
            }
        }
    }

    companion object {
        const val KEY_EMAIL = "key_email"
    }

    override fun showProgress(enabled: Boolean) {
        binding?.btnRegisterNamePassNext?.showProgress(enabled)
    }

    override fun onCreateSuccess(name: String) {
        fragmentAttachListener?.goToWelcomeScreen(name)
    }

    override fun onCreateFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun displayNameFailure(nameError: Int?) {
        binding?.edtRegisterName?.error = nameError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        binding?.edtRegisterPassword?.error = passwordError?.let { getString(it) }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListener){
            fragmentAttachListener = context
        }
    }

    private val watcher = TxtWatcher {
        binding?.btnRegisterNamePassNext?.isEnabled = binding?.edtRegisterName?.text.toString()
            .isNotEmpty() && binding?.edtRegisterConfirmPass?.text.toString()
            .isNotEmpty() && binding?.edtRegisterPassword?.text.toString().isNotEmpty()
    }
}