package com.dryad.test

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_test.*

class ViewData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("kokomadekitayo")
        setContentView(R.layout.activity_view_data)
        val intent = intent
        getdata = intent.getStringExtra("SEND_DATA").toString()
        val listView = ListView(this)
        setContentView(listView)
        println("kokomadekitayo")
    }

    lateinit var mCustomAdapter: CustomAdapter
    lateinit var mSyllabusList: ArrayList<Syllabus>

    var getdata = ""

    override fun onStart() {
        super.onStart()

        onShow()
    }

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

        cursor.moveToFirst()

        val syllabusData = Syllabus(
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

        mSyllabusList = arrayListOf(syllabusData)
        val listView = findViewById<ListView>(R.id.listView)

        // adapterを作成します
        mCustomAdapter = CustomAdapter(
            this, mSyllabusList
        )

        // adapterをlistViewに紐付けます。
        listView.adapter = mCustomAdapter


    }





}