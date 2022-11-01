package com.example.firebaselitechat.firebase

interface FirebaseRepository {
    fun signOut(){}
    fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit){}
    fun createNewUser(onSuccess: () -> Unit, onFail: (String) -> Unit){}
}