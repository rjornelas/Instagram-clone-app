package rjornelas.course.instagram.register.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import rjornelas.course.instagram.R
import rjornelas.course.instagram.common.base.DependencyInjector
import rjornelas.course.instagram.common.view.CropperImageFragment
import rjornelas.course.instagram.common.view.CustomerDialog
import rjornelas.course.instagram.databinding.FragmentRegisterPhotoBinding
import rjornelas.course.instagram.post.view.AddFragment
import rjornelas.course.instagram.register.RegisterPhoto
import rjornelas.course.instagram.register.presentation.RegisterPhotoPresenter

class RegisterPhotoFragment : Fragment(R.layout.fragment_register_photo), RegisterPhoto.View {

    private var binding: FragmentRegisterPhotoBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null
    override lateinit var presenter: RegisterPhoto.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener("cropKey") { _, bundle ->
            val uri = bundle.getParcelable<Uri>(CropperImageFragment.KEY_URI)
            onCropImageResult(uri)
        }
    }

    private fun onCropImageResult(uri: Uri?) {
        if (uri != null) {
            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val source = ImageDecoder.createSource(requireContext().contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            } else {
                MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
            }
            binding?.imgProfile?.setImageBitmap(bitmap)

            presenter.updateUser(uri)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterPhotoBinding.bind(view)

        val repository = DependencyInjector.registerEmailRepository()
        presenter = RegisterPhotoPresenter(this, repository)

        binding?.let { fragmentRegisterPhotoBinding ->
            with(fragmentRegisterPhotoBinding) {

                when (resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
                    Configuration.UI_MODE_NIGHT_YES -> {
                        imgProfile.imageTintList = ColorStateList.valueOf(Color.WHITE)
                    }

                    Configuration.UI_MODE_NIGHT_NO -> {
                    }
                }

                btnRegisterSkip.setOnClickListener {
                    fragmentAttachListener?.goToMainScreen()
                }

                btnRegisterNext.isEnabled = true
                btnRegisterNext.setOnClickListener {
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
                    if (allPermissionsGranted()) {
                        fragmentAttachListener?.goToCameraScreen()
                    } else {
                        getPermission.launch(REQUIRED_PERMISSION)
                    }
                    fragmentAttachListener?.goToCameraScreen()
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
        if (context is FragmentAttachListener) {
            fragmentAttachListener = context
        }
    }

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress(enabled: Boolean) {
        binding?.btnRegisterNext?.showProgress(enabled)
    }

    override fun onUpdateFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onUpdateSuccess() {
        fragmentAttachListener?.goToMainScreen()
    }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(requireContext(), REQUIRED_PERMISSION[0]) == PackageManager.PERMISSION_GRANTED


    private val getPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { granted ->
            if (allPermissionsGranted()) {
                fragmentAttachListener?.goToCameraScreen()
            } else {
                Toast.makeText(
                    requireContext(),
                    R.string.permission_camera_denied,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    companion object {
        private val REQUIRED_PERMISSION =
            arrayOf(Manifest.permission.CAMERA)
    }
}
