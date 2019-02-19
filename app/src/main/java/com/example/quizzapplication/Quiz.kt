package com.example.quizzapplication

class Quiz {
    var question:String=""
     var answerOne :String =""
     var answerTwo:String =""
     var answerThree :String=""
     var correctAnswerNumber :Int=0
    var id : Int = 0

    constructor(question:String,answerOne :String,answerTwo:String,answerThree :String,correctAnswerNumber :Int){
        this.question=question
        this.answerOne = answerOne
        this.answerTwo=answerTwo
        this.answerThree=answerThree
        this.correctAnswerNumber=correctAnswerNumber
    }

    constructor(){

    }

    fun isCorrect(answerNumber:Int):Boolean{
        if(answerNumber== correctAnswerNumber){
            return true
        }
        return false
    }

}