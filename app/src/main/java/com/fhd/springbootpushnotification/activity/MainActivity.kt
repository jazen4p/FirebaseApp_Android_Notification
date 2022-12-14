package com.fhd.springbootpushnotification.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.fhd.springbootpushnotification.R
import com.fhd.springbootpushnotification.notification.Configuration.Companion.TOPIC_GLOBAL
import com.google.android.gms.tasks.OnCompleteListener
//import com.google.firebase.iid.internal.FirebaseInstanceIdInternal
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseMessaging.getInstance().token.addOnSuccessListener { result ->
            firebaseId.append(result)
            Log.d("tokenid", result)
        }

//        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//            if (!task.isSuccessful) {
//                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
//                return@OnCompleteListener
//            }
//
//            // Get new FCM registration token
//            val token = task.result
//
//            // Log and toast
//            val msg = getString(R.string.msg_token_fmt, token)
//            Log.d(TAG, msg)
//            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
//        })

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC_GLOBAL)
            .addOnCompleteListener { task ->

                if (task.isSuccessful)
                    Log.d(TAG, "Global topic subscription successful")
                else
                    Log.e(TAG, "Global topic subscription failed. Error: " + task.exception?.localizedMessage)
            }
    }
}
