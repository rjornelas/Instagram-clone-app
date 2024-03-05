package rjornelas.course.instagram.register.view

interface FragmentAttachListener {

    fun goToNameAndPasswordScreen(email: String)
    fun goToWelcomeScreen(name: String)
    fun goToUploadPhotoScreen()
    fun goToMainScreen()
}