package com.fhd.springbootpushnotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseMessaging.getInstance().subscribeToTopic("JavaSampleApproach")
                .addOnCompleteListener { task ->
                    var msg = "Successfull"

                    if (!task.isSuccessful) {
                        msg = "failed"
                    }
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                }

    }
}
