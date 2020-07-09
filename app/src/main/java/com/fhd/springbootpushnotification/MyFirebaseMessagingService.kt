package com.fhd.springbootpushnotification

import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.FirebaseMessagingService


class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        if (p0!!.notification != null) {
            val title = p0.notification!!.title
            val body = p0.notification!!.body

            NotificationsHelper(applicationContext).createNofitication( title!!, body!!)
        }
    }
}