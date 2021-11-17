package com.dryad.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.view.View
import android.widget.Button
import android.widget.EditText

@Suppress("FunctionName")
class MainActivity : AppCompatActivity() {

    val EXTRA_MESSAGE: String ="com.dryad.test.MESSAGE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun ButtonTapped(view: View){
        val intent = Intent(this, data_input::class.java)
        startActivities(arrayOf(intent))
    }


}