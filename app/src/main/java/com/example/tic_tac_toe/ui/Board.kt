package com.example.tic_tac_toe.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

enum class BoardValues(val str: String) {
    X("X"), O("O"), None(" ")
}

class Board {
    val board = listOf(
        listOf(
            mutableStateOf(BoardValues.None),
            mutableStateOf(BoardValues.None),
            mutableStateOf(BoardValues.None)
        ),
        listOf(
            mutableStateOf(BoardValues.None),
            mutableStateOf(BoardValues.None),
            mutableStateOf(BoardValues.None)
        ),
        listOf(
            mutableStateOf(BoardValues.None),
            mutableStateOf(BoardValues.None),
            mutableStateOf(BoardValues.None)
        )
    )
    val turn = mutableStateOf(BoardValues.X)
    val winner = mutableStateOf(BoardValues.None)
    fun checkWinner() {

        val squares = board.flatten().map { it.value }

        val lines = listOf(
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(0, 4, 8),
            listOf(2, 4, 6)
        )

        for (line in lines) {
            val (a, b, c) = line
            if (squares[a] != BoardValues.None && squares[a] == squares[b] && squares[a] == squares[c]) {
                winner.value = squares[a]
            }
        }
    }

    fun resetBoard() {
        for (row in board) {
            for (it in row) {
                it.value = BoardValues.None
            }
        }
        winner.value = BoardValues.None
        turn.value = BoardValues.X
    }

    private fun changeTurn() {
        if (turn.value == BoardValues.X) {
            turn.value = BoardValues.O
            machineTurn()
            return
        }
        turn.value = BoardValues.X

    }

    fun clickHandler(s: MutableState<BoardValues>): () -> Unit {
        return fun() {
            if (s.value != BoardValues.None) {
                return
            }
            if (winner.value != BoardValues.None) {
                return
            }
            s.value = turn.value
            checkWinner()
            changeTurn()
        }
    }

    private fun machineTurn() {
        if (winner.value != BoardValues.None) {
            return
        }
        while (true) {
            val ele = board[(0..2).random()][(0..2).random()]
            if (ele.value == BoardValues.None) {
                ele.value = BoardValues.O
                checkWinner()
                changeTurn()
                break
            }
        }
    }
}
