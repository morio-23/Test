package com.dryad.test

import android.provider.BaseColumns

// データベースのテーブル名・項目名を定義
class DBContract  // 誤ってインスタンス化しないようにコンストラクタをプライベート宣言

private constructor() {
    // テーブルの内容を定義
    object DBSyllabus : BaseColumns {
        // BaseColumns インターフェースを実装することで、内部クラスは_IDを継承できる
        const val TABLE_NAME = "Syllabus_tbl"
        const val C_classname = "classname"
        const val C_teacher = "teacher"
        const val C_classcategory = "classcategory"
        const val C_classtype = "classtype"
        const val C_coc = "coc"
        const val C_period = "period"
        const val C_faculty = "faculty"
        const val C_classregicode = "classregicode"
        const val C_grade = "grade"
        const val C_classnumcode = "classnumcode"
    }

    object DBTimetable : BaseColumns {
        const val TABLE_NAME ="Timetable_tbl"
        const val COLUMN_NAME_CLASS = "Class"
        const val COLUMN_NAME_SYLLABUSID = "SyllabusID"
        const val COLUMN_NAME_CLASSCODE = "ClassCode"
        const val COLUMN_NAME_ROOM = "Room"
        const val COLUMN_NAME_TEACHER = "Teacher"
    }

}