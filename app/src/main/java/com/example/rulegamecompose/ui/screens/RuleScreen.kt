package com.example.rulegamecompose.ui.screens

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rulegamecompose.R
import com.example.rulegamecompose.ui.models.ValueList
import com.example.rulegamecompose.ui.viewmodel.RuleViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

@Composable
fun RuleScreen(vm: RuleViewModel) {

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
            CoroutineScope(Dispatchers.Main).launch {
                var index = 0
                val job = async(Dispatchers.Default) {
                    vm.getCurrentIndex().collect {
                         index = it
                    }
                }
                job.await()
                resultNumber = ValueList.list[index].value.toString()
                color = Color(ValueList.list[index].numColor)
            }
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
                vm.twistRule(angle)
                CoroutineScope(Dispatchers.Default).launch {
                    vm.getCurrentNumberState().collect {
                        rotationValue = it
                    }
                }.onJoin
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