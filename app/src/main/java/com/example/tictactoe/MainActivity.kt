package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() ,View.OnClickListener{
    lateinit var board:Array<Array<Button>>
    var boardValue = Array(3){IntArray(3)}
    var Player = true
    var count = 0
    lateinit var displayText:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button1 = findViewById<Button>(R.id.button1)
        val button2= findViewById<Button>(R.id.button2)
        val button3= findViewById<Button>(R.id.button3)
        val button4= findViewById<Button>(R.id.button4)
        val button5= findViewById<Button>(R.id.button5)
        val button6= findViewById<Button>(R.id.button6)
        val button7= findViewById<Button>(R.id.button7)
        val button8= findViewById<Button>(R.id.button8)
        val button9= findViewById<Button>(R.id.button9)
        displayText = findViewById(R.id.displayTv)
        board = arrayOf(
            arrayOf(button1,button2,button3), arrayOf(button4,button5,button6), arrayOf(button7,button8,button9),
        )
        for(i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }

        val resetButton = findViewById<Button>(R.id.resetButton)
        resetButton.setOnClickListener(View.OnClickListener {
            initializeBoard()
        })
    }

    private fun initializeBoard(){
        count = 0
        Player = true
        for (i in 0..2){
            for(j in 0..2){
                boardValue[i][j]=-1
                board[i][j].isEnabled = true
                board[i][j].text = ""
            }
        }
    }




    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.button1->{
                    updateButton(row=0,col=0,player=Player)
                }
                R.id.button2->{
                    updateButton(row=0,col=1,player=Player)
                }
                R.id.button3->{
                    updateButton(row=0,col=2,player=Player)
                }
                R.id.button4->{
                    updateButton(row=1,col=0,player=Player)
                }
                R.id.button5->{
                    updateButton(row=1,col=1,player=Player)
                }
                R.id.button6->{
                    updateButton(row=1,col=2,player=Player)
                }
                R.id.button7->{
                    updateButton(row=2,col=0,player=Player)
                }
                R.id.button8->{
                    updateButton(row=2,col=1,player=Player)
                }
                R.id.button9->{
                    updateButton(row=2,col=2,player=Player)
                }

            }
            count++
            if(count == 9){
                displayUpdate("Game Over")
            }
            Player = !Player
            if(Player) displayUpdate("Player X Turn")
            else displayUpdate("Player O Turn")
            checkWinner()
        }
    }

    private fun displayUpdate(s: String) {
            displayText.text = s;
            if(s.contains("Winner")){
                for(i in board){
                    for(button in i){
                        button.isEnabled = false
                    }
                }
            }
    }

    private fun updateButton(row: Int, col: Int, player: Boolean) {

        var text = if(player)"X" else "O"
        var value = if(player)1 else 0
        board[row][col].isEnabled = false
        board[row][col].text = text
        boardValue[row][col] = value
    }
    private  fun checkWinner(){
        for( i in 0..2){
            if(boardValue[i][0] == boardValue[i][1] && boardValue[i][1] == boardValue[i][2]){
                if(boardValue[i][0] == 1){
                    displayUpdate("Player X is Winner")
                }else if(boardValue[i][0] == 0){
                    displayUpdate("Player O is Winner")
                }
            }
        }
        for( i in 0..2){
            if(boardValue[0][i] == boardValue[1][i] && boardValue[1][i] == boardValue[2][i]){
                if(boardValue[0][i] == 1){
                    displayUpdate("Player X is Winner")
                }else if(boardValue[0][i] == 0){
                    displayUpdate("Player O is Winner")
                }
            }
        }
        if(boardValue[0][0] == boardValue[1][1] && boardValue[1][1] == boardValue[2][2]){
            if(boardValue[0][0] == 1){
                displayUpdate("Player X is Winner")
            }else if(boardValue[0][0] == 0){
                displayUpdate("Player O is Winner")
            }
        }

        if(boardValue[0][2] == boardValue[1][1] && boardValue[1][1] == boardValue[2][0]){
            if(boardValue[0][2] == 1){
                displayUpdate("Player X is Winner")
            }else if(boardValue[0][2] == 0){
                displayUpdate("Player O is Winner")
            }
        }
    }
}