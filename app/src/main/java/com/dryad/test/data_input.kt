package com.dryad.test

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dryad.test.databinding.ActivityDataInputBinding

class data_input : AppCompatActivity() {

    private lateinit var binding: ActivityDataInputBinding

        private val dbName: String = "TimetabelDB"
        private val tableName: String = "TimatableTable"
        private val dbVersion: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_input)
    }

    /*private  val _helper = SampDatabaseHelper(applicationContext,"Syllabus",null,1)

    fun databaseClose(){
        _helper.close()
    }

    override fun onDestroy() {
        databaseClose()
        super.onDestroy()
    }*/

    fun TourokuButtonTapped(view: View){
        insertData()
    }

    private fun insertData(){
        
    }



}