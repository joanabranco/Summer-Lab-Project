package com.example.basketballscoresystem

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var scoreTeamA = 0
    private var scoreTeamB = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        whatButton()
    }

    private fun whatButton() {
        val buttonClicked:List<View> = listOf(teamA_3points, teamA_2points, teamA_1point,
                                              teamB_3points, teamB_2points, teamB_1point,
                                              reset_button)

        for (item in buttonClicked){
            item.setOnClickListener{whatPoints(it)}
        }
    }

    private fun whatPoints(view:View) {
        when (view.id){
            R.id.teamA_1point -> addPointsA(1)
            R.id.teamA_2points -> addPointsA(2)
            R.id.teamA_3points -> addPointsA(3)

            R.id.teamB_1point -> addPointsB(1)
            R.id.teamB_2points -> addPointsB(2)
            R.id.teamB_3points -> addPointsB(3)

            R.id.reset_button -> clearPoints()
        }
    }

    private fun addPointsA(points:Int) {
        scoreTeamA += points
        teamA_score.text = scoreTeamA.toString()
    }

    private fun addPointsB(points:Int) {
        scoreTeamB += points
        teamB_score.text = scoreTeamB.toString()
    }

    private fun clearPoints() {
        winnerTeam ()
        scoreTeamA = 0
        scoreTeamB = 0
        teamA_score.text = scoreTeamA.toString()
        teamB_score.text = scoreTeamB.toString()
    }

    private fun winnerTeam() {
        if (scoreTeamA > scoreTeamB)
            Toast.makeText(this, "Team A is the winner", Toast.LENGTH_SHORT).show()
        else Toast.makeText(this, "Team B is the winner", Toast.LENGTH_SHORT).show()
    }
}