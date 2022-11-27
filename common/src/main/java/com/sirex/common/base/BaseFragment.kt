package com.sirex.common.base

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract fun showLoading()

    abstract fun hideLoading()

    fun getRequestFailed(message: String?) {
        showMessage(message)
    }

    fun showMessage(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}