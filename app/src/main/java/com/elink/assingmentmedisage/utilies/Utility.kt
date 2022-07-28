package com.elink.assingmentmedisage.utilies

import android.R
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.regex.Pattern

/**
 * Created by Nitin Sabale
 * @since 27-07-2022. 16:59
 */
object Utility {

    private var progressBar: ProgressBar? = null


    @Suppress("DEPRECATION")
    fun Context.showErrorToast(message: String?) {

        try {
            val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)

            // set message color
            val textView = toast.view?.findViewById(android.R.id.message) as? TextView
            textView?.setTextColor(Color.WHITE)
            textView?.gravity = Gravity.CENTER

            // set background color
            toast.view?.setBackgroundColor(Color.RED)

            toast.setGravity(Gravity.TOP or Gravity.FILL_HORIZONTAL, 0, 0)

            toast.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    // show progressbar
    fun Context.showProgressBar() {
        try {
            val layout =
                (this as? Activity)?.findViewById<View>(android.R.id.content)?.rootView as? ViewGroup

            progressBar = ProgressBar(this, null, R.attr.progressBarStyleLarge)
            progressBar?.let { it1 ->
                it1.isIndeterminate = true

                val params = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
                )

                val rl = RelativeLayout(this)

                rl.gravity = Gravity.CENTER
                rl.addView(it1)

                layout?.addView(rl, params)

                it1.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // hide progressbar
    fun hideProgressBar() {
        try {
            progressBar?.let {
                it.visibility = View.GONE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // validate email pattern
    val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    // in this method we are checking email validation.
    fun isValidString(toString: String): Boolean {
        return EMAIL_ADDRESS_PATTERN.matcher(toString).matches()
    }
    fun showAlertMessage(context: Context, message: String, okClick: () -> Unit) {
        AlertDialog.Builder(context).setTitle(context. resources.getString(com.elink.assingmentmedisage.R.string.alert))
            .setMessage(message)
            .setPositiveButton(context.resources.getString(R.string.ok)) { dialogInterface, i ->
                dialogInterface.dismiss()
                okClick()
            }.create().show()
    }

}
