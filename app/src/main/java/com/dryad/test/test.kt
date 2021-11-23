package com.dryad.test

import android.R.layout.simple_list_item_1
import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import com.dryad.test.R.id.progressBar
import kotlinx.android.synthetic.main.activity_test.*
import java.io.File
import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class test : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val listView: ListView = findViewById(R.id.listView)
        val assetManager = resources.assets
        //val progressBar = ProgressBar(this)
    }

    override fun onResume() {
        super.onResume()
        progressBar.visibility = View.VISIBLE
        runBlocking {
            launch {
                val file = assetManager.open("Syllabus.csv")
                doAll(filepath)
                val listItems: Array<String> = setItem(syllabuslist)
                val adapter = ArrayAdapter(applicationContext, simple_list_item_1, listItems)
                listView.adapter = adapter
                progressBar.visibility = View.GONE
            }
        }
    }

    var column: Array<String> = emptyArray<String>()

    var syllabuslist: Array<Syllabus> = emptyArray()

    suspend fun doAll(file: File): Unit = coroutineScope{
        val doing = async{readCSV(file)}
    }

     suspend fun readCSV(file: File){
        val lines = file.readLines()
        if(lines!=null) {
            for ((i, line) in lines.withIndex()) {
                if (i == 0) {
                    column = line.split(",").toTypedArray()
                } else {
                    fecthCSV(i,line.split(",").toTypedArray())
                }
            }
        }
    }

    suspend fun fecthCSV(i: Int, line: Array<String>){
        val syllabusdata = Syllabus(
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
        syllabuslist += syllabusdata

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

    //val dbHelper = SampDatabaseHelper(applicationContext,"DBSyllabus",null,1)

    //val database = dbHelper.writableDatabase


}