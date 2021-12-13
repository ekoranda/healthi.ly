package com.cs506.healthily.view.activities

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.cs506.healthily.R

import android.os.Build

import android.app.*

import android.content.Intent

import android.content.BroadcastReceiver
import android.content.Context
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.cs506.healthily.data.repository.UserSettingsRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime



class AlarmReceiver : BroadcastReceiver() {

    private val user = Firebase.auth.currentUser?.uid
    private val database = Firebase.database.reference
    var stepMessage = ""

    override fun onReceive(context: Context, intent: Intent) {

        // get user's goal and
        database.child("Users").get().addOnSuccessListener {
            var stepGoal = it.child(user!!).child("stepGoal").value.toString().toInt()
            var yesterdaySteps = it.child(user!!).child("dailySteps/6").value.toString().toInt()


            val notificationIntent =
                Intent(context,  MainActivity::class.java) //on tap this activity will open
            val stackBuilder = TaskStackBuilder.create(context)
            stackBuilder.addParentStack(MainActivity::class.java)
            stackBuilder.addNextIntent(notificationIntent)
            val pendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
            ) //getting the pendingIntent
            val builder: Notification.Builder =
                Notification.Builder(context) //building the notification
            val notification = builder.setContentTitle("Healthi.ly")
                .setContentText(stepMessage)
                .setTicker("New Message Alert!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent).build()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                builder.setChannelId(AlarmReceiver.Companion.CHANNEL_ID)
            }
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            //below creating notification channel, because of androids latest update, O is Oreo
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    AlarmReceiver.Companion.CHANNEL_ID,
                    "NotificationDemo",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(channel)
            }
            notificationManager.notify(0, notification)



        }.addOnFailureListener {

        }


    }

    companion object {
        private const val CHANNEL_ID = "this.is.my.channelId" //you can add any id you want
    }
}
