package com.example.gar

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gar.navlrn.SetupNavGraph
import com.example.gar.ui.theme.GarTheme

class MainActivity : ComponentActivity() {

//    lateinit var navController : NavHostController

    private val NOTIFICATION_ID = 0
    private val CHANNEL_ID = "0"
    private val CHANNEL_NAME = "foo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createNotificationChannel()

        setContent {
            GarTheme {

//                for navigation
//                navController = rememberNavController()
//                SetupNavGraph(navController = navController)

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Main()
                }
            }
        }
    }

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID, CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                lightColor = Color.RED
                enableLights(true)
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    @Composable
    fun Main() {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            NotificationButton()
        }
    }

    @Composable
    fun NotificationButton(){
        Button(
            modifier = Modifier.size(width = 200.dp, height = 60.dp),
            onClick = {

                val intent = Intent(this, MainActivity::class.java)
                val pendingIntent = TaskStackBuilder.create(this).run{
                    addNextIntentWithParentStack(intent)
                    getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
                }

                val notification = NotificationCompat.Builder(this@MainActivity, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Hello, it's notification")
                    .setContentText("BLA-BLA-BLA, meh, it's content of this notification")
                    .setContentIntent(pendingIntent)
                    .setPriority(NotificationCompat.PRIORITY_HIGH).build()

                val notificationManager = NotificationManagerCompat.from(this@MainActivity)
                notificationManager.notify(NOTIFICATION_ID, notification)
            }
        ) {
            Text("Press for notification!")
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        GarTheme {
        }
    }


}



