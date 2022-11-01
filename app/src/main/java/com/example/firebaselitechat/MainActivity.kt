package com.example.firebaselitechat

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.firebaselitechat.databinding.ActivityMainBinding
import com.example.firebaselitechat.utils.LOGIN
import com.example.firebaselitechat.utils.PASSWORD
import com.example.firebaselitechat.utils.REPOSITORY
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signOut.setOnClickListener {
            signOut(this)
        }

//TODO ADD MESSAGE
//        val myRef = database.getReference("message")
//        binding.buttonSend.setOnClickListener {
//            myRef.setValue(binding.edMessage.text.toString())
//        }
//        onChangeListener(myRef)

    }

    private fun signOut(context: Context) {
        REPOSITORY.signOut()
        LOGIN = ""
        PASSWORD = ""
        openActivity(context, SignInActivity::class.java)
    }

    private fun onChangeListener(myRef: DatabaseReference) {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.apply {
                    rcView.append("\n")
                    rcView.append("Alexander: ${snapshot.value.toString()}")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("checkData", "Database read error!")
            }

        })
    }

    private fun <T> openActivity(context: Context, it: Class<T>) {
        val intent = Intent(context, it)
        startActivity(intent)
    }
}