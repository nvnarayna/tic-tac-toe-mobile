package com.example.tic_tac_toe.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TictactoeScreen() {
    val board = remember { Board() }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (board.winner.value != BoardValues.None) {
            Text(
                text = "Winner: ${board.winner.value.str}",
                textAlign = TextAlign.Center,
                fontSize = 50.sp,
                modifier = Modifier.padding(top = 20.dp)
            )
        } else {
            Text(
                text = "Turn: ${board.turn.value.str}",
                textAlign = TextAlign.Center,
                fontSize = 50.sp,
                modifier = Modifier.padding(top = 20.dp)
            )
        }
        Grid(board = board)
        Button(onClick = { board.resetBoard() }) {
            Text(
                text = "Reset",
                textAlign = TextAlign.Center,
                fontSize = 50.sp
            )
        }
    }
}

@Composable
fun Grid(board: Board) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(0.8f), contentAlignment = Alignment.Center
    ) {
        Column {
            board.board.forEach { row ->
                GridRow(row = row, board = board)
            }
        }
    }
}

@Composable
fun GridRow(row: List<MutableState<BoardValues>>, board: Board) {
    Row {
        row.forEach { cell ->
            Square(s = cell, onClick = board.clickHandler(cell))
        }
    }
}

@Composable
fun Square(s: MutableState<BoardValues>, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(100.dp)
            .border(width = 2.dp, color = Color.Black),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
    ) {
        Text(
            text = s.value.str,
            textAlign = TextAlign.Center,
            fontSize = 80.sp,
            color = Color.Black,
            modifier = Modifier.fillMaxSize()
        )
    }
}

