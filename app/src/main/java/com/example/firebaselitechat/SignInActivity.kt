package com.example.firebaselitechat

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.firebaselitechat.databinding.ActivitySignInBinding
import com.example.firebaselitechat.utils.LOGIN
import com.example.firebaselitechat.utils.PASSWORD
import com.example.firebaselitechat.utils.REPOSITORY


class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignInActivity.setOnClickListener {
            LOGIN = binding.emailSignInActivity.text.toString()
            PASSWORD = binding.passwordSignInActivity.text.toString()
            if (LOGIN != "" || PASSWORD != "") {
                initDatabase(this)
            } else Log.d(com.example.firebaselitechat.utils.TAG, "Enter your username or password!")
        }

        binding.createNewUser.setOnClickListener {
            openActivity(this, RegisterActivity::class.java)
        }

    }

    private fun initDatabase(context: Context) {
        REPOSITORY = com.example.firebaselitechat.firebase.Firebase()
        REPOSITORY.connectToDatabase(
            {
                Log.d(com.example.firebaselitechat.utils.TAG, "Sign In")
                openActivity(context, MainActivity::class.java)
            },
            {
                Log.d("checkData", "Error: $it")
            }
        )
    }

    private fun <T> openActivity(context: Context, it: Class<T>) {
        val intent = Intent(context, it)
        startActivity(intent)
    }
}






