package com.example.quizzapplication

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    var quizs =ArrayList<Quiz>();
    var numberOfGoodAnswer : Int = 0;
    var currentQuizIndex : Int= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        val username = intent.getStringExtra(EXTRA_NAME)
        val textView = findViewById<TextView>(R.id.username).apply{
            text= username
        }
        val context = this
        var db = DatabaseHandler(context)
        var data = db.readData()
        for (i in 0..(data.size - 1)) {
            var quizLoop = Quiz(data.get(i).question,data.get(i).answerOne,data.get(i).answerTwo, data.get(i).answerThree,data.get(i).correctAnswerNumber)
            quizs.add(quizLoop)
        }
      showQuiz(quizs.get(currentQuizIndex))
    }

    fun showQuiz(quiz: Quiz){
        questionNumber.setText("Question Numero: "+(currentQuizIndex+1))
        txtQuestion.setText(quiz.question);
        answerOne.setText(quiz.answerOne);
        answerTwo.setText(quiz.answerTwo);
        answerThree.setText(quiz.answerThree)
        score.setText("Score :" + numberOfGoodAnswer)
    }
    fun handleAnswer(answerID: Int){

        var quiz = quizs.get(currentQuizIndex)
        if(quiz.isCorrect(answerID)){
            numberOfGoodAnswer++;
            score.setText("Score :" + numberOfGoodAnswer)
            Toast.makeText(this, "good answer, you have one more point !",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "wrong answer",Toast.LENGTH_SHORT).show()

        }
        currentQuizIndex++;
        if (currentQuizIndex >= quizs.size){
            score.setText("Score :" + numberOfGoodAnswer)
            var alert = AlertDialog.Builder(this)
            alert.setTitle("Partie Terminé")
            alert.setMessage("tu as eu : "+numberOfGoodAnswer+" bonne(s) reponse(s)")
            alert.setPositiveButton("OK"){
                dialog: DialogInterface?, which: Int ->
                finish()
            }
            alert.show();

        }else{
            showQuiz(quizs.get(currentQuizIndex))
        }

    }
    fun onClickAnswerOne(view: View){
        handleAnswer(1)

    }
    fun onClickAnswerTwo(view: View){
        handleAnswer(2)

    }
    fun onClickAnswerThree(view: View){
        handleAnswer(3)

    }
}
