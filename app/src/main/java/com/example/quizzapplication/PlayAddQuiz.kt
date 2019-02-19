package com.example.quizzapplication

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*


const val EXTRA_USER="com.example.NAME"
class PlayAddQuiz : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_add_quiz)
        val username = intent.getStringExtra(EXTRA_NAME)
        val textView = findViewById<TextView>(R.id.username).apply{
            text= username
        }

    }
    fun onClickPlay(view : View){
        var quizs =ArrayList<Quiz>();
        val context = this
        var db = DatabaseHandler(context)
        var data = db.readData()
        for (i in 0..(data.size - 1)) {
            var quizLoop = Quiz(data.get(i).question,data.get(i).answerOne,data.get(i).answerTwo, data.get(i).answerThree,data.get(i).correctAnswerNumber)
            quizs.add(quizLoop)
        }

        if(quizs.size ==0){
            var alert = AlertDialog.Builder(this)
            alert.setTitle("Info")
            alert.setMessage("Le questionnaire est vide veuillez rajouter au moins une question")
            alert.setPositiveButton("OK"){
                    dialog: DialogInterface?, which: Int ->
                finish()
                val intent = Intent(this,AddQuiz ::class.java)
                startActivity(intent)

            }
            alert.show();


        }else{
            val editText =findViewById<TextView>(R.id.username)
            val username = editText.text.toString()
            val intent = Intent(this,QuizActivity::class.java).apply {
                putExtra(EXTRA_USER,username)
            }
            startActivity(intent)
        }

    }
    fun onClickAdd(view : View){
        val intent = Intent(this,AddQuiz ::class.java)
        startActivity(intent)
    }

}
