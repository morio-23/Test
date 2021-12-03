package com.dryad.test

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
//import com.dryad.test.DBContract.DBTimetable
import com.dryad.test.DBContract.DBSyllabus


//参考：https://www.ma-chanblog.com/2021/01/android-db-text.html

// データベースをアプリから使用するために、 SQLiteOpenHelperを継承する
// SQLiteOpenHelperは、データベースやテーブルが存在する場合はそれを開き、存在しない場合は作成してくれる
public class SampDatabaseHelper
    (context: Context, DBNAME:String, factory: SQLiteDatabase.CursorFactory?, VERSION: Int) :
    SQLiteOpenHelper(context, DBNAME, factory, VERSION) {
    // データベース作成時にテーブルを作成
    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL(
            "CREATE TABLE " + DBSyllabus.TABLE_NAME +
                    " ( _id INTEGER PRIMARY KEY, " +
                    DBSyllabus.TABLE_NAME + " TEXT default '', " +
                    DBSyllabus.C_classname + " TEXT default '', " +
                    DBSyllabus.C_teacher + " TEXT default '', " +
                    DBSyllabus.C_classcategory + " TEXT default '', " +
                    DBSyllabus.C_classtype + " TEXT default '', " +
                    DBSyllabus.C_coc + " TEXT default '', " +
                    DBSyllabus.C_period + " TEXT default '', " +
                    DBSyllabus.C_faculty + " TEXT default '', " +
                    DBSyllabus.C_classregicode + " TEXT default '', " +
                    DBSyllabus.C_grade + " TEXT default '', " +
                    DBSyllabus.C_classnumcode + " TEXT default '', " +
                    DBSyllabus.C_credit + " TEXT default '', " +
                    DBSyllabus.C_latestupdate + " TEXT default '', " +
                    DBSyllabus.C_officehours + " TEXT default '', " +
                    DBSyllabus.C_rtadvice + " TEXT default '', " +
                    DBSyllabus.C_objective + " TEXT default '', " +
                    DBSyllabus.C_edugoals + " TEXT default '', " +
                    DBSyllabus.C_goals + " TEXT default '', " +
                    DBSyllabus.C_schedule + " TEXT default '', " +
                    DBSyllabus.C_studyoutside + " TEXT default '', " +
                    DBSyllabus.C_keywords + " TEXT default '', " +
                    DBSyllabus.C_notice + " TEXT default '', " +
                    DBSyllabus.C_evaluation + " TEXT default '', " +
                    DBSyllabus.C_textbooks + " TEXT default '', " +
                    DBSyllabus.C_related + " TEXT default '', " +
                    DBSyllabus.C_link + " TEXT default '', " +
                    DBSyllabus.C_notes + " TEXT default '')"
        )

        // トリガーを作成
        /*db.execSQL(
            "CREATE TRIGGER trigger_samp_tbl_update AFTER UPDATE ON " + DBTimetable.TABLE_NAME +
                    " BEGIN " +
                    " UPDATE " + DBTimetable.TABLE_NAME + " SET up_date = DATETIME('now', 'localtime') WHERE rowid == NEW.rowid; " +
                    " END;"
        )*/
    }

    // データベースをバージョンアップした時、テーブルを削除してから再作成
    override fun onUpgrade(db: SQLiteDatabase, oldV: Int, newV: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + DBSyllabus.TABLE_NAME)
        onCreate(db)
    }

}