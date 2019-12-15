package com.provatsoft.smsreceiveapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestSmsPermission()
        val numberTextView =
            findViewById<View>(R.id.numberTextView) as TextView
        val messageTextView =
            findViewById<View>(R.id.messageTextView) as TextView

        val bundle = intent.extras
        if (bundle != null) {
            val number = bundle.getString("number")
            val message = bundle.getString("message")
            numberTextView.text = number
            messageTextView.text = message
        }
    }


    private fun requestSmsPermission() {
        val permission: String = Manifest.permission.RECEIVE_SMS
        val grant = ContextCompat.checkSelfPermission(this, permission)
        if (grant != PackageManager.PERMISSION_GRANTED) {
            val permissionList = arrayOfNulls<String>(1)
            permissionList[0] = permission
            ActivityCompat.requestPermissions(this, permissionList, 1)
        }
    }
}
