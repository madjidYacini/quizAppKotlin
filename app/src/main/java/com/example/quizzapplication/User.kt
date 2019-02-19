package com.example.quizzapplication

class User {
    var username : String = ""
    var password : String = ""
    var highScore : Int = 0
    var lowScore : Int = 0

    constructor(username: String , password : String , highScore: Int, lowScore: Int){
        this.username= username
        this.password = password
        this.highScore= highScore
        this.lowScore= lowScore
    }
    constructor(username: String , password : String ){
        this.username= username
        this.password = password
    }
}