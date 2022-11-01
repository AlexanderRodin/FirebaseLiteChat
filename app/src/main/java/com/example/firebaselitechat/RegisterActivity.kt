package com.example.firebaselitechat

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.firebaselitechat.databinding.ActivityRegisterBinding
import com.example.firebaselitechat.firebase.Firebase
import com.example.firebaselitechat.utils.LOGIN
import com.example.firebaselitechat.utils.PASSWORD
import com.example.firebaselitechat.utils.REPOSITORY
import com.example.firebaselitechat.utils.TAG


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.signInButtonRegisterActivity.setOnClickListener {
            if (binding.emailRegisterActivity.text.toString() != "" ||
                binding.emailRegisterActivity.text.toString() != ""
            ) {
                LOGIN = binding.emailRegisterActivity.text.toString()
                PASSWORD = binding.passwordRegisterActivity.text.toString()
                userCreated(this)
            } else {
                Log.d(TAG, "Enter your username and password!")
            }
        }
    }

    private fun userCreated(context: Context) {
        REPOSITORY = Firebase()
        REPOSITORY.createNewUser(
            {
                Log.d(TAG, "Create user")
                openActivity(context, MainActivity::class.java)
            },
            { Log.d("checkData", "Error: $it") }
        )
    }

    private fun <T> openActivity(context: Context, it: Class<T>) {
        val intent = Intent(context, it)
        startActivity(intent)
    }
}