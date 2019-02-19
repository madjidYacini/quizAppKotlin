package com.example.quizzapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME="MyDB"
val TABLE_NAME ="quiz"
val COL_QUESTION = "question"
val COL_ANSWERONE= "answerOne"
val COL_ANSWERTWO="answerTwo"
val COL_ANSWERTHREE ="answerThree"
val COL_GOOD_ANSWER = "goodAnswer"
val COL_ID = "id"
val TABLE_USER ="users"
val COL_USERNAME="username"
val COL_UID ="id"
val COL_PASSWORD="password"
val COL_HSCORE="highScore"
val COL_LSCORE = "lowScore"

class DatabaseHandler(var context : Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1) {

    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME +" (" +
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_QUESTION + " VARCHAR(256)," +
                COL_ANSWERONE +" VARCHAR(256)," +
                COL_ANSWERTWO+" VARCHAR(256),"+
                COL_ANSWERTHREE+" VARCHAR(256),"+
                COL_GOOD_ANSWER+" INTEGER)"
        val createTableUser =  "CREATE TABLE " + TABLE_USER +" (" +
                COL_UID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_USERNAME + " VARCHAR(256)," +
                COL_PASSWORD +" VARCHAR(256)," +
                COL_HSCORE+" INTEGER ,"+
                COL_LSCORE+" INTEGER)"

        if (db != null) {
            db.execSQL(createTableUser)
            db.execSQL(createTable)

        }

    }
    fun insertData(quiz: Quiz){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_QUESTION,quiz.question)
        cv.put(COL_ANSWERONE,quiz.answerOne)
        cv.put(COL_ANSWERTWO,quiz.answerTwo)
        cv.put(COL_ANSWERTHREE,quiz.answerThree)
        cv.put(COL_GOOD_ANSWER,quiz.correctAnswerNumber)

        var result = db.insert(TABLE_NAME,null,cv)
        if(result == -1.toLong())
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()

        else
            Toast.makeText(context," La question a été bien rajouté",Toast.LENGTH_SHORT).show()
    }
    fun insertUser(user:User){
     var db= this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_USERNAME,user.username)
        cv.put(COL_PASSWORD,user.password)
        cv.put(COL_HSCORE,user.highScore)
        cv.put(COL_LSCORE,user.lowScore)
        println(cv)


        var result = db.insert(TABLE_USER,null,cv)
        if(result == -1.toLong()) {
            println(result)
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()

        }else{
            Toast.makeText(context," Le USER est bien ajouté",Toast.LENGTH_SHORT).show()}
    }
    fun readData() : MutableList<Quiz> {
        var list: MutableList<Quiz> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var quiz = Quiz()
                quiz.question = result.getString(result.getColumnIndex(COL_QUESTION))
                quiz.answerOne = result.getString(result.getColumnIndex(COL_ANSWERONE))
                quiz.answerTwo = result.getString(result.getColumnIndex(COL_ANSWERTWO))
                quiz.answerThree = result.getString(result.getColumnIndex(COL_ANSWERTHREE))
                quiz.correctAnswerNumber = result.getString(result.getColumnIndex(COL_GOOD_ANSWER)).toInt()
                quiz.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                list.add(quiz)
            } while (result.moveToNext())
        }

        result.close()
        db.close()
        println(list)
        return list
    }
    fun deleteData(quiz: Quiz){
        val db = this.writableDatabase
        println(quiz.id.toString())
        db.delete(TABLE_NAME, COL_ID + " =? ", arrayOf(quiz.id.toString()))
        db.close()
    }
        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}