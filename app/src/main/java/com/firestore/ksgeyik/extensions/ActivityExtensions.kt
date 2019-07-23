package com.firestore.ksgeyik.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.DimenRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun <reified T : Any> AppCompatActivity.launchActivity(
        requestCode: Int = -1,
        options: Bundle? = null,
        noinline init: Intent.() -> Unit = {}) {

    val intent = newIntent<T>(this)
    intent.init()
    if (requestCode > -1)
        startActivityForResult(intent, requestCode, options)
    else
        startActivity(intent, options)
}

inline fun <reified T : Any> Context.launchActivity(
        options: Bundle? = null,
        noinline init: Intent.() -> Unit = {}) {

    val intent = newIntent<T>(this)
    intent.init()
    startActivity(intent, options)
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, @IdRes containerId: Int, tag: String = fragment.javaClass.simpleName) {
    val fragmentTransaction = supportFragmentManager.beginTransaction()
    fragmentTransaction.replace(containerId, fragment, tag)
    fragmentTransaction.commitAllowingStateLoss()
}

fun Context.dimension(@DimenRes id: Int): Int {
    return resources.getDimension(id).toInt()
}

inline fun AppCompatActivity.transection(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = supportFragmentManager.beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commitAllowingStateLoss()
}


inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}


inline fun <reified T : Any> newIntent(context: Context): Intent =
        Intent(context, T::class.java)

fun Fragment.hideSoftKeyboard() {
    if (activity != null)
        activity!!.hideSoftKeyboard()
}

fun Fragment.showSoftKeyboard(view: View) {
    activity?.showSoftKeyboard(view)
}

fun Activity.hideSoftKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}

fun Activity.showSoftKeyboard(view: View) {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    view.requestFocus()
    inputMethodManager.showSoftInput(view, 0)
}
