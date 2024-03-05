package rjornelas.course.instagram.register.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import rjornelas.course.instagram.R
import rjornelas.course.instagram.databinding.FragmentRegisterWelcomeBinding

class RegisterWelcomeFragment : Fragment(R.layout.fragment_register_welcome) {

    private var binding: FragmentRegisterWelcomeBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterWelcomeBinding.bind(view)

        val name = arguments?.getString(KEY_NAME) ?: throw IllegalStateException("Name not found")

        binding?.let {
            with(it) {
                txtRegisterWelcome.text = getString(R.string.welcome_to_instagram, name)
                btnRegisterNext.isEnabled = true

                btnRegisterNext.setOnClickListener{
                    fragmentAttachListener?.goToUploadPhotoScreen()
                }
            }
        }
    }

    companion object {
        const val KEY_NAME = "key_name"
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener) {
            fragmentAttachListener = context
        }
    }
}
