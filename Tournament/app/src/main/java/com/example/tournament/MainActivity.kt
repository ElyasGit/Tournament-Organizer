package com.example.tournament

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tournamentorganizer.Tour
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        create_tournament.setOnClickListener {
            createTournament()



        }

    }

    private fun createTournament(){
        val name = tournament_name_text.text.toString()
        val desc = description_text.text.toString()
        val picture = " "
        val rules = " "
        val game = game_text.text.toString()
        var mode = ""
        val date = date_text.text.toString()
        val maxPlayers = max_players_text.text.toString()
        val prize = " "
        val streamLink = ""
        var status = ""
        if(public_status.isChecked)
             status = "Public"
        else
             status = "Private"

        if(single_mode.isChecked)
            mode = "Single-Elimination"
        else
            mode = "Points"
        if(name.isEmpty()||desc.isEmpty()||maxPlayers.isEmpty()||game.isEmpty()||date.isEmpty())
            Toast.makeText(applicationContext,"Enter Valid Data",Toast.LENGTH_SHORT).show()
        else {
            val ref = FirebaseDatabase.getInstance().reference

            val tourID = ref.push().key
            val tourna = Tour(
                name,
                tourID.toString(),
                date,
                desc,
                game,
                maxPlayers,
                mode,
                picture,
                prize,
                rules,
                status,
                streamLink
            )
            ref.child("Tournaments").child(tourID!!).setValue(tourna)
        }

    }

    private fun saveHero2(){
        println("2out")
        val ref = FirebaseDatabase.getInstance().getReference("Tournaments")
        val tournaments = ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                println(p0.toString())
            }

            override fun onDataChange(p0: DataSnapshot) {

                var lists: MutableList<Tour> = mutableListOf()

                if (p0!!.exists()) {

                    for (h in p0.children) {
                        val tou  = h.getValue(Tour::class.java)!!
                        lists.add(tou!!)

                    }

                    println(lists.get(2).picture)
                    println(lists.get(2).name)
                    println(lists.get(2).game)
                    println(lists.toString())
                    println("FirstFunction")
                }

            }
        })
    }

}
