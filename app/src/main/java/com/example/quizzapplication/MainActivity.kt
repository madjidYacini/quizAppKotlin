package com.example.quizzapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_quiz.*
import kotlinx.android.synthetic.main.activity_main.*

const val EXTRA_NAME="com.example.NAME"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onClickLogin(view : View){

        val editText =findViewById<EditText>(R.id.username)
        val username = editText.text.toString()
        if(username.length > 0) {


            val intent = Intent(this, PlayAddQuiz::class.java).apply {
                putExtra(EXTRA_NAME, username)
            }
            startActivity(intent)

        }else{
            Toast.makeText(this, "mettez votre nom avant de vous connecter",Toast.LENGTH_SHORT).show()

        }

    }


}
