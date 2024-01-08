package com.example.rulegamecompose.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rulegamecompose.R
import com.example.rulegamecompose.models.ValueList
import kotlin.math.roundToInt

@Preview(showBackground = true)
@Composable
fun RuleScreen() {

    var rotationValue by remember {
        mutableStateOf(0f)
    }
    var resultNumber by remember {
        mutableStateOf("0")
    }
    var color by remember {
        mutableStateOf(Color.Green)
    }
    val angle by animateFloatAsState(targetValue = rotationValue, label = "",
        animationSpec = tween(durationMillis = 2000),
        finishedListener = {
            val index = (360f - (it % 360)) / (360f / ValueList.list.size)
            resultNumber = ValueList.list[index.roundToInt()].value.toString()
            color = Color(ValueList.list[index.roundToInt()].numColor)
        }
    )
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        Arrangement.SpaceBetween,
        Alignment.CenterHorizontally,
    ) {
        Text(
            text = resultNumber, style = TextStyle(
                fontWeight = FontWeight.Bold, fontSize = 45.sp,
                color = color
            )
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.rule),
                contentDescription = "rule",
                Modifier
                    .fillMaxSize()
                    .rotate(angle)
            )
            Image(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "arrow",
                Modifier.fillMaxSize()
            )
        }
        Button(
            onClick = {
                rotationValue = (720..3600).random().toFloat() + angle
            },
            Modifier
                .fillMaxWidth()
                .padding(10.dp, bottom = 24.dp),
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text(
                text = "Start",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )

        }
    }
}