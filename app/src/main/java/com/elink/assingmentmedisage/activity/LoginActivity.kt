package com.elink.assingmentmedisage.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils.*
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.elink.assingmentmedisage.R
import com.elink.assingmentmedisage.utilies.SharedPref
import com.elink.assingmentmedisage.utilies.Utility
import com.elink.assingmentmedisage.MainActivity

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    // variable for email and password edit text.
    lateinit var editTextEmailID: EditText
    lateinit var editTextPassword: EditText

    // a variable for our button.
    lateinit var loginBtn: Button

    // variable for email and password.
    var email = ""
    var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        SharedPref.init(getApplicationContext());
        
        // our email and password edit text
        editTextEmailID = findViewById(R.id.idEdtEmail)
        editTextPassword = findViewById(R.id.idEdtPassword)

        // our login button with id.
        loginBtn = findViewById(R.id.btn_login)
        
        email = SharedPref.read(SharedPref.EMAIL_KEY, email).toString()//save string in shared preference.
        password = SharedPref.read(SharedPref.PWD_KEY, password).toString();//save int in shared preference.

        loginBtn.setOnClickListener(this)
        loginBtn.isEnabled = false
        loginBtn.setBackgroundColor(Color.GRAY)

        onTextChangeListener()
    }


    private fun onTextChangeListener() {

        editTextEmailID.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                if (inputTextValidation()) {
                    loginBtn.isEnabled = true
                    loginBtn.setBackgroundColor(ContextCompat.getColor(applicationContext!!,
                        R.color.teal_200
                    ))
                } else {
                    loginBtn.isEnabled = false
                    loginBtn.setBackgroundColor(Color.GRAY)

                }

            }

        })
        editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                if (inputTextValidation()) {
                    loginBtn.isEnabled = true
                    loginBtn.setBackgroundColor(ContextCompat.getColor(applicationContext!!,
                        R.color.teal_200
                    ))
                } else {
                    loginBtn.isEnabled = false
                    loginBtn.setBackgroundColor(Color.GRAY)

                }
            }

        })
    }
    // in this method start to next activity
    private fun nextToActivity() {
        SharedPref.write(SharedPref.EMAIL_KEY, editTextEmailID.text.toString());//save string in shared preference.
        SharedPref.write(SharedPref.PWD_KEY, editTextPassword.text.toString());//save int in shared preference.
        val i = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(i)
        finish()
    }
    // in this method we are checking validation.
    private fun validation(): Boolean {
        var isValid = false
        if (editTextEmailID.text.toString().isEmpty()) {
            Toast.makeText(applicationContext, getString(R.string.please_enter_email),
                    Toast.LENGTH_SHORT).show()
        } else if (!Utility.isValidString(editTextEmailID.text.toString())) {
            Toast.makeText(applicationContext, getString(R.string.please_enter_valid_email_address),
                    Toast.LENGTH_SHORT).show()
        } else if (editTextPassword.text.toString().isEmpty()) {
            Toast.makeText(applicationContext, getString(R.string.please_enter_password),
                    Toast.LENGTH_SHORT).show()
        } else if (editTextPassword.text.toString().length < 8) {
            Toast.makeText(applicationContext, getString(R.string.please_enter_valid_password),
                    Toast.LENGTH_SHORT).show()
        } else {
            isValid = true
        }
        return isValid
    }

    private fun inputTextValidation(): Boolean {
        var isValid = false
        if (editTextEmailID.text.toString().length > 0 && editTextPassword.text.toString().length > 0) {
            isValid = true
        }
        return isValid
    }


    // on below line we are calling on start method.
    override fun onStart() {
        super.onStart()
        email = SharedPref.read(SharedPref.EMAIL_KEY, email).toString()//save string in shared preference.
        password = SharedPref.read(SharedPref.PWD_KEY, password).toString();//save int in shared preference.

        // in this method we are checking if email and password are not empty.
        if (!email.equals("") && !password.equals("")) {
            val i = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login -> {
                    if (validation()) {
                        // starting our activity.
                        nextToActivity()
                    }
            }
        }
    }

}