package rjornelas.course.instagram.register.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import rjornelas.course.instagram.R
import rjornelas.course.instagram.common.view.CustomerDialog
import rjornelas.course.instagram.databinding.FragmentRegisterPhotoBinding
import rjornelas.course.instagram.databinding.FragmentRegisterWelcomeBinding

class RegisterPhotoFragment : Fragment(R.layout.fragment_register_photo) {

    private var binding: FragmentRegisterPhotoBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterPhotoBinding.bind(view)

        binding?.let { fragmentRegisterPhotoBinding ->
            with(fragmentRegisterPhotoBinding){
                btnRegisterSkip.setOnClickListener{
                    fragmentAttachListener?.goToMainScreen()
                }

                btnRegisterNext.isEnabled = true
                btnRegisterNext.setOnClickListener{
                    val customerDialog = CustomerDialog(requireContext())
                    customerDialog.setTitle(R.string.photo_register_title)
                    customerDialog.addButton(R.string.photo, R.string.gallery) {
                        when (it.id) {
                            R.string.photo -> {
                                //open camera
                            }

                            R.string.gallery -> {
                                //open gallery
                            }
                        }
                    }
                    customerDialog.show()
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListener){
            fragmentAttachListener = context
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}
