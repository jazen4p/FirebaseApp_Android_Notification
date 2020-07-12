# Send Push Notification From Server Side (Spring boot) to client side (Android) with integration FCM

<b> Client Side (Android) </b>


As you already created a firebase project and added firebase configuration file in
your spring boot application so now its time to add android app to get started.
 ![alt text](
  https://github.com/FirozHasan007/AndroidPushNotification/blob/master/androidfirebasecreate.PNG
  )
1) Download and Move the google-services.json file you just downloaded into your Android app module root directory.

2) Add firebase sdk

3) MyFirebaseMessagingService : by this service class this app will be registered as a 
firebase user and generate unique device/user/token id. So in later time if you want to 
send notification to a specific user you use this specific token to send it. During token 
registration this onNewToken() is being called and if you want to store this token to 
database, you can make an API call inside this method.
When data is sent from server, onMessageReceived() method will be called, 
received the data and generates notification and perform further actions if required 
like opening a new activity etc.

Other than this class all the other classes are very basic and easy to understand.


In order to send push notification you will need a server side application.
For spring boot application you can refer to the following link : https://github.com/Firoz-Hasan/SpringBootPushNotification

