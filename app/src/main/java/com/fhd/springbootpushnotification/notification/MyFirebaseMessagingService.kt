package com.fhd.springbootpushnotification.notification

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import com.fhd.springbootpushnotification.activity.MessageShowActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.json.JSONException

class MyFirebaseMessagingService : FirebaseMessagingService() {

    val TAG = "MessagingService"
    private var notificationUtils: NotificationUtils? = null

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.e("FCM Token", token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {

            try {

                Log.e(TAG, "Data Payload: " + remoteMessage?.data?.get("title"))
                Log.e(TAG, "Data Payload: " + remoteMessage?.data?.get("message"))
                Log.e(TAG, "Data Payload: " + remoteMessage?.data?.get("timestamp"))
                Log.e(TAG, "Data Payload: " + remoteMessage?.data?.get("image"))
                Log.e(TAG, "Data Payload: " + remoteMessage?.data?.get("article_data"))
                handleCustomDataMessage(
                    remoteMessage?.data?.get("image").toString(),
                    remoteMessage?.data?.get("message").toString(),
                    remoteMessage?.data?.get("title").toString(),
                    remoteMessage?.data?.get("timestamp").toString(),
                    remoteMessage?.data?.get("article_data").toString()
                )

            } catch (e: Exception) {
                Log.e(TAG, "Exception onMessageReceived: " + e.message)
            }
        }
        else {
            Log.e(TAG, "From: " + remoteMessage.notification?.title)
            Log.e(TAG, "From: " + remoteMessage?.notification?.body)
            handleCustomDataMessage(
                "",
                title = remoteMessage.notification?.title!!,
                message = remoteMessage.notification?.body!!,
                timestamp = "",
                articleData = ""

            )
        }
    }

    private fun handleCustomDataMessage(imageUrl : String, title: String, message: String,
                                        timestamp: String, articleData : String) {

        try {

            //Send notification data to MessageShowActivity class for showing
            val resultIntent = Intent(applicationContext, MessageShowActivity::class.java)
            resultIntent.putExtra("title", title)
            resultIntent.putExtra("timestamp", timestamp)
            resultIntent.putExtra("article_data", articleData)
            resultIntent.putExtra("image", imageUrl)
            resultIntent.putExtra("message", message)

            // check for image attachment
            if (TextUtils.isEmpty(imageUrl)) {
                showNotificationMessage(applicationContext, title, message, timestamp, resultIntent)
            } else {
                // image is present, show notification with image
                showNotificationMessageWithBigImage(
                    applicationContext,
                    title,
                    message,
                    timestamp,
                    resultIntent,
                    imageUrl
                )
            }

        } catch (e: JSONException) {
            Log.e(TAG, "Json Exception: " + e.message)
        } catch (e: Exception) {
            Log.e(TAG, "Exception: " + e.message)
        }

    }

    /**
     * Showing notification with text only
     */
    private fun showNotificationMessage(
        context: Context,
        title: String,
        message: String,
        timeStamp: String,
        intent: Intent
    ) {
        notificationUtils = NotificationUtils(context)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        notificationUtils?.showNotificationMessage(title, message, timeStamp, intent)
    }

    /**
     * Showing notification with text and image
     */
    private fun showNotificationMessageWithBigImage(
        context: Context,
        title: String,
        message: String,
        timeStamp: String,
        intent: Intent,
        imageUrl: String
    ) {
        notificationUtils = NotificationUtils(context)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        notificationUtils?.showNotificationMessage(title, message, timeStamp, intent, imageUrl)
    }
}