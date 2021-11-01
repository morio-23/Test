package com.dryad.test

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dryad.test.DBContract.DBTimetable


//参考：https://www.ma-chanblog.com/2021/01/android-db-text.html

// データベースをアプリから使用するために、 SQLiteOpenHelperを継承する
// SQLiteOpenHelperは、データベースやテーブルが存在する場合はそれを開き、存在しない場合は作成してくれる
class SampDatabaseHelper  // コンストラクタは必ず必要
    (context: Context?) :
    SQLiteOpenHelper(context, DBNAME, null, VERSION) {
    // データベース作成時にテーブルを作成
    override fun onCreate(db: SQLiteDatabase) {

        // テーブルを作成
        db.execSQL(
            "CREATE TABLE " + DBTimetable.TABLE_NAME +
                    " ( _id INTEGER PRIMARY KEY, " +
                    DBTimetable.COLUMN_NAME_CLASS + " TEXT default '', " +
                    DBTimetable.COLUMN_NAME_SYLLABUSID + " INTEGER default '', " +
                    DBTimetable.COLUMN_NAME_CLASSCODE + " INTEGER default ''," +
                    DBTimetable.COLUMN_NAME_ROOM + " TEXT default '', " +
                    DBTimetable.COLUMN_NAME_TEACHER + " TEXT default '', " +
                    ") "
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
    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + DBTimetable.TABLE_NAME)
        onCreate(db)
    }

    companion object {
        // データベースのバージョン
        // テーブルの内容などを変更したら、この数字を変更する
        private const val VERSION = 2

        // データベース名
        private const val DBNAME = "samp.db"
    }
}