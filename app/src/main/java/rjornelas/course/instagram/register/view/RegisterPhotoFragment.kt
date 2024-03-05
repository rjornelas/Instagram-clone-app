package rjornelas.course.instagram.register.view

import android.content.Context
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import rjornelas.course.instagram.R
import rjornelas.course.instagram.common.view.CropperImageFragment
import rjornelas.course.instagram.common.view.CustomerDialog
import rjornelas.course.instagram.databinding.FragmentRegisterPhotoBinding
import rjornelas.course.instagram.databinding.FragmentRegisterWelcomeBinding

class RegisterPhotoFragment : Fragment(R.layout.fragment_register_photo) {

    private var binding: FragmentRegisterPhotoBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener("cropKey"){
                requestKey, bundle -> val uri = bundle.getParcelable<Uri>(CropperImageFragment.KEY_URI)
            onCropImageResult(uri)
        }
    }

    private fun onCropImageResult(uri: Uri?) {
        if(uri != null){
            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val source = ImageDecoder.createSource(requireContext().contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            } else {
                MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
            }
            binding?.imgProfile?.setImageBitmap(bitmap)
        }
    }

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
                    openDialog()
                }
            }
        }
    }

    private fun openDialog() {
        val customerDialog = CustomerDialog(requireContext())
        customerDialog.setTitle(R.string.photo_register_title)
        customerDialog.addButton(R.string.photo, R.string.gallery) {
            when (it.id) {
                R.string.photo -> {
                    //open camera
                }

                R.string.gallery -> {
                    fragmentAttachListener?.goToGalleryScreen()
                }
            }
        }
        customerDialog.show()
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
