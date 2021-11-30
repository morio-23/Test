package com.dryad.test

import android.R.layout.simple_list_item_1
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.coroutines.*

class test : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val listView: ListView = findViewById(R.id.listView)

        //val progressBar = ProgressBar(this)
    }

    val dbHelper = SampDatabaseHelper(applicationContext,"DBSyllabus",null,1)

    override fun onResume() {
        super.onResume()
        progressBar.visibility = View.VISIBLE
        runBlocking {
            launch {
                doAll("syllabus.csv")
                val listItems: Array<String> = setItem(syllabuslist)
                val adapter = ArrayAdapter(applicationContext, simple_list_item_1, listItems)
                listView.adapter = adapter
                progressBar.visibility = View.GONE
            }
        }
    }

    var column: Array<String> = emptyArray<String>()

    var syllabuslist: Array<Syllabus> = emptyArray()

    suspend fun doAll(file: String): Unit = coroutineScope{
        val doing = async{readCSV(file)}
    }

     suspend fun readCSV(file: String){
        val imput = resources.assets.open(file)
         val lines = imput.bufferedReader().readLines()
        if(lines!=null) {
            for ((i, line) in lines.withIndex()) {
                if (i == 0) {
                    column = line.split(",").toTypedArray()
                } else {
                    Log.println(Log.ASSERT,"TAG", line)
                    val split = line.split(",").toMutableList()
                    Log.println(Log.ASSERT, "TAG", split[1])
                    fecthCSV(i,split)
                }
            }
        }
    }

    suspend fun fecthCSV(i: Int, line: List<String>){
        val syllabusData = Syllabus(
            primaryKey = i,
            classname = line[0],
            teacher = line[1],
            classcategory = line[2],
            classtype = line[3],
            coc = line[4],
            period = line[5],
            faculty = line[6],
            classregicode = line[7],
            grade = line[8],
            classnumcode = line[9],
            credit = line[10],
            latestupdate = line[11],
            officehours = line[12],
            rtadvice = line[13],
            objective = line[14],
            edugoals = line[15],
            goals = line[16],
            schedule = line[17],
            studyoutside = line[18],
            keywords = line[19],
            notice = line[20],
            evaluation = line[21],
            textbooks = line[22],
            related = line[23],
            link = line[24],
            notes = line[25]
        )
        syllabuslist += syllabusData
        insertData(syllabusData)

    }

    fun setItem(shelters: Array<Syllabus>): Array<String>{
        var items: Array<String> = emptyArray()
        shelters.forEach {
            items += """
                |授業名:{${it.classname}}
                |教員:{${it.teacher}}
                |授業コード{${it.classregicode}}""".trimMargin()
        }
        return items

    }

    fun insertData(syllabus: Syllabus){
        val cv = ContentValues()
        cv.put(DBContract.DBSyllabus.C_classname, syllabus.classname)
        cv.put(DBContract.DBSyllabus.C_teacher, syllabus.teacher)
        cv.put(DBContract.DBSyllabus.C_classcategory, syllabus.classcategory)
        cv.put(DBContract.DBSyllabus.C_classtype, syllabus.classtype)
        cv.put(DBContract.DBSyllabus.C_coc, syllabus.coc)
        cv.put(DBContract.DBSyllabus.C_period, syllabus.period)
        cv.put(DBContract.DBSyllabus.C_faculty, syllabus.faculty)
        cv.put(DBContract.DBSyllabus.C_classregicode, syllabus.classregicode)
        cv.put(DBContract.DBSyllabus.C_grade, syllabus.grade)
        cv.put(DBContract.DBSyllabus.C_classnumcode, syllabus.classnumcode)
        cv.put(DBContract.DBSyllabus.C_credit, syllabus.credit)
        cv.put(DBContract.DBSyllabus.C_latestupdate, syllabus.latestupdate)
        cv.put(DBContract.DBSyllabus.C_officehours, syllabus.officehours)
        cv.put(DBContract.DBSyllabus.C_rtadvice, syllabus.rtadvice)
        cv.put(DBContract.DBSyllabus.C_objective, syllabus.objective)
        cv.put(DBContract.DBSyllabus.C_edugoals, syllabus.edugoals)
        cv.put(DBContract.DBSyllabus.C_goals, syllabus.goals)
        cv.put(DBContract.DBSyllabus.C_schedule, syllabus.schedule)
        cv.put(DBContract.DBSyllabus.C_keywords, syllabus.keywords)
        cv.put(DBContract.DBSyllabus.C_notice, syllabus.notice)
        cv.put(DBContract.DBSyllabus.C_evaluation, syllabus.evaluation)
        cv.put(DBContract.DBSyllabus.C_textbooks, syllabus.textbooks)
        cv.put(DBContract.DBSyllabus.C_related, syllabus.related)
        cv.put(DBContract.DBSyllabus.C_link, syllabus.link)
        cv.put(DBContract.DBSyllabus.C_notes, syllabus.notes)

        dbHelper.insert(cv)

    }



}