package com.dryad.test

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_test.*
import android.widget.ArrayAdapter as ArrayAdapter

class ViewData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_data)
        val intent = intent
        getdata = intent.getStringExtra("SEND_DATA").toString()
        val listView = ListView(this)
        setContentView(listView)
    }

    var getdata = ""

    override fun onResume() {
        super.onResume()

        onShow()
    }

    @SuppressLint("Range")
    fun onShow() {
        val dbHelper = SampDatabaseHelper(applicationContext, "DBSyllabus", null, 1)
        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM " + DBContract.DBSyllabus.TABLE_NAME + " WHERE " + DBContract.DBSyllabus.C_classnumcode + "= ?",
            arrayOf(getdata)
        )
        val syllabusData = Syllabus(
            primaryKey = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.PRIMARYKEY)),
            classname = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_classnumcode)),
            teacher = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_teacher)),
            classcategory = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_classcategory)),
            classtype = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_classtype)),
            coc = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_coc)),
            period = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_period)),
            faculty = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_faculty)),
            classregicode = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_classregicode)),
            grade = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_grade)),
            classnumcode = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_classnumcode)),
            credit = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_credit)),
            latestupdate = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_latestupdate)),
            officehours = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_officehours)),
            rtadvice = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_rtadvice)),
            objective = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_objective)),
            edugoals = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_edugoals)),
            goals = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_goals)),
            schedule = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_schedule)),
            studyoutside = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_studyoutside)),
            keywords = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_keywords)),
            notice = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_notice)),
            evaluation = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_evaluation)),
            textbooks = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_textbooks)),
            related = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_related)),
            link = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_link)),
            notes = cursor.getString(cursor.getColumnIndex(DBContract.DBSyllabus.C_notes))
        )

        // adapterを作成します
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, syllabusData)

        // adapterをlistViewに紐付けます。
        listView.adapter = adapter


    }





}