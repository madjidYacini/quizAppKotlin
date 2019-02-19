package com.example.quizzapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_quiz.*

class AddQuiz : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_quiz)

        val context = this
        var db = DatabaseHandler(context)
        btn_insert.setOnClickListener({
            if (question.text.toString().length > 0 &&
                answerOne.text.toString().length > 0 &&
                answerTwo.text.toString().length > 0 &&
                answerThree.text.toString().length > 0 &&
                goodAnswer.text.toString().length > 0) {

                var quiz = Quiz(question.text.toString(),answerOne.text.toString(),answerTwo.text.toString(),answerThree.text.toString(), goodAnswer.text.toString().toInt())
                println(quiz)
                db.insertData(quiz)
            } else {
                Toast.makeText(context,"Please Fill All Data's",Toast.LENGTH_SHORT).show()
            }
        })
        var data = db.readData()
        btn_delete.setOnClickListener({
           var data = db.readData()
            db.deleteData(data.get(0));
            Toast.makeText(context,"first item deleted",Toast.LENGTH_SHORT).show()

        })
    }
}
