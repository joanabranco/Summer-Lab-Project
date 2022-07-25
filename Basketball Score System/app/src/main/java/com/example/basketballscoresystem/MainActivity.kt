package com.example.basketballscoresystem

import android.content.Context
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

    private lateinit var timer : CountDownTimer
    private var startMilliSeconds : Long = 600000
    private var timeIsRunning : Boolean = false
    private var timeLeft : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        whatButton()
    }

    private fun whatButton() {
        val buttonClicked:List<View> = listOf(teamA_3points, teamA_2points, teamA_1point,
                                              teamB_3points, teamB_2points, teamB_1point,
                                              start_pause_button, reset_button,
                                              edit_teamA_name, edit_teamB_name)

        for (item in buttonClicked){
            item.setOnClickListener{doButton(it)}
        }
    }

    private fun doButton(view:View) {
        when (view.id){
            R.id.start_pause_button -> startGame ()

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
        if (timeIsRunning) {
            pauseTimer()
        }
        else {
            startTimer()
        }
    }

    private fun startTimer() {
        timer = object : CountDownTimer (startMilliSeconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                updateTimeText()
            }

            override fun onFinish() {
                timeIsRunning = false
                start_pause_button.text = "Start"
                start_pause_button.visibility = View.INVISIBLE
                reset_button.visibility = View.VISIBLE
                resetAll()
            }
        }.start()
        timeIsRunning = true
        start_pause_button.text = "Pause"
        reset_button.visibility = View.INVISIBLE
    }

    private fun pauseTimer() {
        startMilliSeconds = timeLeft
        timeIsRunning = false
        timer.cancel()
        start_pause_button.text = "Resume"
        reset_button.visibility = View.VISIBLE
    }

    private fun resetTimer() {
        timeLeft = 600000
        updateTimeText()
        reset_button.visibility = View.INVISIBLE
    }

    private fun updateTimeText() {
        val minutes = (timeLeft / 1000) / 60
        val seconds = (timeLeft / 1000) % 60

        val timeFormat = "$minutes : $seconds"
        time_text.text = timeFormat
    }

    private fun addPointsA(points:Int) {
        totalScoreA += points
        teamA_score.text = totalScoreA.toString()
    }

    private fun addPointsB(points:Int) {
        totalScoreB += points
        teamB_score.text = totalScoreB.toString()
    }

    private fun resetAll() {
        winnerTeam ()
        totalScoreA = 0
        totalScoreB = 0
        teamA_score.text = totalScoreA.toString()
        teamB_score.text = totalScoreB.toString()

        resetTimer()
    }

    private fun winnerTeam() {
        if (totalScoreA > totalScoreB)
            Toast.makeText(this, "${teamA_name.text}  is the winner!", Toast.LENGTH_SHORT).show()
        else if (totalScoreA < totalScoreB)
            Toast.makeText(this, "${teamB_name.text} is the winner!", Toast.LENGTH_SHORT).show()
            else Toast.makeText(this, "That´s a tie!", Toast.LENGTH_SHORT).show()
    }

    private fun changeNameA() {
        edit_teamA_name.visibility = View.GONE
        teamA_name.visibility = View.INVISIBLE

        teamA_editable.visibility = View.VISIBLE
        done_teamA_name.visibility = View.VISIBLE

        //quando for carregado o botão de concluir
        done_teamA_name.setOnClickListener{
            var teamA = teamA_editable.text.toString()
            Toast.makeText(this, " Team A name was edited to $teamA", Toast.LENGTH_SHORT).show()
            teamA_name.text = teamA_editable.text.toString()
            teamA_editable.visibility = View.GONE
            done_teamA_name.visibility = View.GONE
            teamA_name.visibility = View.VISIBLE
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