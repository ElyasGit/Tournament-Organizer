package com.example.tournamentorganizer

class Tour( val name: String,val ID: String, val date : String, val description : String,val game : String, val maxPlayers : String,val mode : String, val picture:String, val prize:String,val rules:String, val status:String,val streamLink:String  ){

    constructor(): this("", "","","","","","","","","","", ""){

    }


}