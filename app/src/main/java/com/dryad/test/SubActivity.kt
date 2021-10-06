package com.dryad.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
    }

    fun ButtonTapped(view: View){
        finish()
    }
}