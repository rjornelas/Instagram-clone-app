package rjornelas.course.instagram.register.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import rjornelas.course.instagram.R
import rjornelas.course.instagram.common.base.DependencyInjector
import rjornelas.course.instagram.common.util.TxtWatcher
import rjornelas.course.instagram.databinding.FragmentRegisterEmailBinding
import rjornelas.course.instagram.databinding.FragmentRegisterNamePasswordBinding
import rjornelas.course.instagram.register.RegisterEmail
import rjornelas.course.instagram.register.presentation.RegisterEmailPresenter

class RegisterNamePasswordFragment : Fragment(R.layout.fragment_register_name_password) {

    private var binding: FragmentRegisterNamePasswordBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterNamePasswordBinding.bind(view)

        val email = arguments?.getString(KEY_EMAIL)
        Log.i("Teste", email.toString())
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    companion object{
        const val KEY_EMAIL = "key_email"
    }
}