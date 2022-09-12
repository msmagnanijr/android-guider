package com.travelblog

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.travelblog.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val preferences: BlogPreferences by lazy {
        BlogPreferences(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (preferences.isLoggedIn()) {
            startMainActivity()
            finish()
            return
        }

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener { onLoginClicked() }

        binding.textUsernameLayout.editText
            ?.addTextChangedListener(createTextWatcher(binding.textUsernameLayout))

        binding.textPasswordInput.editText
            ?.addTextChangedListener(createTextWatcher(binding.textPasswordInput))

    }

    private fun onLoginClicked() {
        val username: String = binding.textUsernameLayout.editText?.text.toString()
        val password: String = binding.textPasswordInput.editText?.text.toString()
        if (username.isEmpty()) {
            binding.textUsernameLayout.error = "Por favor preencha o usuário"
        } else if (password.isEmpty()) {
            binding.textPasswordInput.error = "Por favor preencha a senha"
        } else if (username != "admin" && password != "admin") {
            showErrorDialog()
        } else {
            performLogin()
        }
    }

    private fun performLogin() {
        preferences.setLoggedIn(true)
        binding.textUsernameLayout.isEnabled = false
        binding.textPasswordInput.isEnabled = false
        binding.loginButton.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE

        Handler(Looper.getMainLooper()).postDelayed({
            startMainActivity()
        }, 2000)
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun showErrorDialog() {
        AlertDialog.Builder(this)
            .setTitle("Tente Novamente")
            .setMessage("Usuário ou Senha está incorreto. Por favor tente novamente!")
            .setPositiveButton("OK") { dialog, which -> dialog.dismiss() }
            .show()
    }

    private fun createTextWatcher(textPasswordInput: TextInputLayout): TextWatcher? {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence,
                                           start: Int, count: Int, after: Int) {
                // not needed
            }

            override fun onTextChanged(s: CharSequence,
                                       start: Int, before: Int, count: Int) {
                textPasswordInput.error = null
            }

            override fun afterTextChanged(s: Editable) {
                // not needed
            }
        }
    }
}