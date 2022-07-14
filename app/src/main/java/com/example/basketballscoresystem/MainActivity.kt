package com.example.basketballscoresystem

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var totalScoreA = 0
    private var totalScoreB = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        whatButton()
    }

    private fun whatButton() {
        val buttonClicked:List<View> = listOf(teamA_3points, teamA_2points, teamA_1point,
                                              teamB_3points, teamB_2points, teamB_1point,
                                              start_button, pause_button, reset_button,
                                              edit_teamA_name, edit_teamB_name)

        for (item in buttonClicked){
            item.setOnClickListener{doButton(it)}
        }
    }

    private fun doButton(view:View) {
        when (view.id){
            R.id.start_button -> startGame ()
            R.id.pause_button -> pauseGame ()

            R.id.teamA_1point -> addPointsA(1)
            R.id.teamA_2points -> addPointsA(2)
            R.id.teamA_3points -> addPointsA(3)

            R.id.teamB_1point -> addPointsB(1)
            R.id.teamB_2points -> addPointsB(2)
            R.id.teamB_3points -> addPointsB(3)

            R.id.edit_teamA_name -> changeNameA ()
            R.id.edit_teamB_name -> changeNameB ()

            R.id.reset_button -> resetAll()
        }
    }

    private fun startGame() {
        //timer.start()
        start_button.visibility = View.INVISIBLE
        pause_button.visibility = View.VISIBLE
        reset_button.visibility = View.VISIBLE
    }

    private fun pauseGame() {
        //timer.cancel()
        /*
        pause_button.setOnClickListener {
            timer.start()
        }*/
    }

    private fun addPointsA(points:Int) {
        totalScoreA += points
        teamA_score.text = totalScoreA.toString()
        teamA_score.setTextColor(Color.GREEN)
    }

    private fun addPointsB(points:Int) {
        totalScoreB += points
        teamB_score.text = totalScoreB.toString()
        teamB_score.setTextColor(Color.MAGENTA)
    }

    private fun resetAll() {
        winnerTeam ()
        totalScoreA = 0
        totalScoreB = 0
        teamA_score.text = totalScoreA.toString()
        //teamA_score.setTextColor(Color.WHITE)
        teamB_score.text = totalScoreB.toString()
        //teamB_score.setTextColor(Color.WHITE)

        //pôr tempo a 10 minutos e ativar botão start
        start_button.visibility = View.VISIBLE
        pause_button.visibility = View.INVISIBLE
        time_text.text = R.string.time_text.toString()
        }

    private fun winnerTeam() {
        var teamA = teamA_name.text.toString()
        var teamB = teamB_name.text.toString()
        if (totalScoreA > totalScoreB)
            Toast.makeText(this, " $teamA  is the winner!", Toast.LENGTH_SHORT).show()
        else if (totalScoreA < totalScoreB)
            Toast.makeText(this, "$teamB is the winner!", Toast.LENGTH_SHORT).show()
            else Toast.makeText(this, "That´s a tie!", Toast.LENGTH_SHORT).show()
    }

    private fun changeNameA() {
        edit_teamA_name.visibility = View.GONE //botão para editar
        teamA_name.visibility = View.INVISIBLE //nome "Team A"

        teamA_editable.visibility = View.VISIBLE //texto editável onde vai ser inserido o novo nome
        done_teamA_name.visibility = View.VISIBLE //botão para concluir

        //quando for carregado o botão de concluir
        done_teamA_name.setOnClickListener{
            var teamA = teamA_editable.text.toString()
            Toast.makeText(this, " name was edited to $teamA", Toast.LENGTH_SHORT).show()
            teamA_name.text = teamA_editable.text.toString() //novo nome é declarado
            teamA_editable.visibility = View.GONE //texto editável desaparece
            done_teamA_name.visibility = View.GONE
            teamA_name.visibility = View.VISIBLE //novo nome aparece
            hideKeyboard (teamA_editable)
        }
    }

    private fun changeNameB() {
        edit_teamB_name.visibility = View.GONE
        teamB_name.visibility = View.INVISIBLE

        teamB_editable.visibility = View.VISIBLE
        done_teamB_name.visibility = View.VISIBLE

        done_teamB_name.setOnClickListener{
            var teamB = teamB_editable.text.toString()
            Toast.makeText(this, "Team B name was edited to $teamB", Toast.LENGTH_SHORT).show()
            teamB_name.text = teamB_editable.text.toString()
            teamB_editable.visibility = View.GONE
            done_teamB_name.visibility = View.GONE
            teamB_name.visibility = View.VISIBLE
            hideKeyboard (teamB_editable)
        }
    }

    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}