package rjornelas.course.instagram.common

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyBoard(){
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    var view: View? = currentFocus
    if(view == null){
        view = View(this)
    }

    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}