package com.example.rulegamecompose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rulegamecompose.ui.models.ValueList
import com.example.rulegamecompose.ui.viewmodel.RuleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BetDialog(dialogShow: MutableState<Boolean>, bankSize: Float, viewModel: RuleViewModel) {
    if (dialogShow.value) {
        var amountBet by remember {
            mutableStateOf("0")
        }
        var zoneColorBetsChecked by remember {
            mutableStateOf(false)
        }
        var checkedRed by remember {
            mutableStateOf(false)
        }
        var checkedBlack by remember {
            mutableStateOf(false)
        }
        var checkedGreen by remember {
            mutableStateOf(false)
        }
        var zoneNumberBetsChecked by remember {
            mutableStateOf(false)
        }
        var betSetNumber by remember {
            mutableStateOf("")
        }
        var listNumber = mutableSetOf<Int>()


        AlertDialog(
            onDismissRequest = { dialogShow.value = false },
            confirmButton = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            if (amountBet.toFloat() > bankSize) amountBet = bankSize.toString()
                            viewModel.setAmountBet(amountBet.toFloat())
                            if (checkedRed) {
                                listNumber.addAll(ValueList.getListRed())
                            }
                            if (checkedBlack) {
                                listNumber.addAll(ValueList.getListBlack())
                            }
                            if (checkedGreen) {
                                listNumber.addAll(ValueList.getListGreen())
                            }
                            if (zoneNumberBetsChecked) {
                                val list = listOf(*betSetNumber.split(",").toTypedArray())
                                val set = mutableSetOf<Int>()
                                list.forEach {
                                    if (it.toInt() in 0..36) {
                                        set.add(it.toInt())
                                    }
                                }
                                listNumber.addAll(set)
                            }
                            viewModel.setListNumberBet(listNumber)
                            dialogShow.value = false
                        }
                    ) {
                        Text("set")
                    }
                }
            },
            title = {
                Text(
                    text = "Place a bet",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.SpaceBetween) {
                    TextField(
                        value = amountBet,
                        onValueChange = { amountBet = it.trimStart() },
                        singleLine = true,
                        placeholder = {
                            Text(text = "Enter amount of the bet")
                        },
                        label = {
                            Text(text = "amount of the bet:")
                        },
                        shape = RoundedCornerShape(8.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(3.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(text = "bet on color:")
                        Checkbox(checked = zoneColorBetsChecked, onCheckedChange = {
                            zoneColorBetsChecked = !zoneColorBetsChecked
                        })
                        Text(text = "bet on number:")
                        Checkbox(checked = zoneNumberBetsChecked, onCheckedChange = {
                            zoneNumberBetsChecked = !zoneNumberBetsChecked
                        })
                    }
                    if (zoneColorBetsChecked) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(3.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Text(text = "red:")
                            Checkbox(checked = checkedRed, onCheckedChange = {
                                checkedRed = !checkedRed
                            })
                            Text(text = "black:")
                            Checkbox(checked = checkedBlack, onCheckedChange = {
                                checkedBlack = !checkedBlack
                            })
                            Text(text = "green:")
                            Checkbox(checked = checkedGreen, onCheckedChange = {
                                checkedGreen = !checkedGreen
                            })
                        }
                    }
                    if (zoneNumberBetsChecked) {
                        TextField(
                            value = betSetNumber,
                            onValueChange = { betSetNumber = it },
                            singleLine = true,
                            placeholder = {
                                Text(text = "Enter number 0..36 separated by ',' ")
                            },
                            label = {
                                Text(text = "number")
                            },
                            shape = RoundedCornerShape(8.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }

                }

            },
        )
    }
}