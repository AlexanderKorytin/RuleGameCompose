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
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import com.example.rulegamecompose.R
import com.example.rulegamecompose.ui.models.ValueList
import com.example.rulegamecompose.ui.viewmodel.RuleViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RuleScreen(vm: RuleViewModel, live: LifecycleOwner) {
    var amountBet by remember {
        mutableStateOf(0f)
    }

    var bank by remember {
        mutableStateOf(0f)
    }
    var rotationValue by remember {
        mutableStateOf(0f)
    }
    var resultNumber by remember {
        mutableStateOf("0")
    }
    var color by remember {
        mutableStateOf(Color.Green)
    }
    var dialogShow = remember {
        mutableStateOf(false)
    }

    vm.getAmountBet().observe(live) {
        amountBet = it
    }
    vm.getBankSize().observe(live) {
        if (it <= 0) {

        }
        bank = it
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
                resultNumber = ValueList.list[index].valueNumber.toString()
                color = Color(ValueList.list[index].numColor)
                vm.setResultBank()
            }
        }
    )
    if (dialogShow.value) {
        BetDialog(dialogShow = dialogShow, bankSize = bank, viewModel = vm)
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        Arrangement.SpaceBetween,
        Alignment.CenterHorizontally,
    ) {
        ConstraintLayout(
            Modifier
                .fillMaxWidth()
                .padding(3.dp, bottom = 64.dp)
        ) {
            val (text1, button, text2) = createRefs()
            Text(
                modifier = Modifier.constrainAs(text1) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                },
                text = "current bet: ${String.format("%.2f", amountBet)}", style = TextStyle(
                    fontWeight = FontWeight.Bold, fontSize = 15.sp,
                    color = Color.White
                )
            )
            Button(
                onClick = {
                    dialogShow.value = true
                },
                modifier = Modifier.constrainAs(button) {
                    top.linkTo(parent.top, margin = 64.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                Text(text = "set bet", fontSize = 15.sp)
            }
            Text(
                modifier = Modifier.constrainAs(text2) {
                    top.linkTo(parent.top, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                },
                text = "your money: ${String.format("%.2f", bank)}", style = TextStyle(
                    fontWeight = FontWeight.Bold, fontSize = 15.sp,
                    color = Color.White,
                )
            )

        }
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