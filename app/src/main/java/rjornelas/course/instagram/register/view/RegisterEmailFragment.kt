package rjornelas.course.instagram.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import rjornelas.course.instagram.R
import rjornelas.course.instagram.databinding.FragmentRegisterEmailBinding
import rjornelas.course.instagram.register.RegisterEmail

class RegisterEmailFragment : Fragment(R.layout.fragment_register_email), RegisterEmail.View {
    private var binding: FragmentRegisterEmailBinding? = null

    override lateinit var presenter: RegisterEmail.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterEmailBinding.bind(view)
    }

    override fun displayEmailFailure(emailError: Int?){}

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }
}