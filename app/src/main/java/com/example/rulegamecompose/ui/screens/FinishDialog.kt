package com.example.rulegamecompose.ui.screens

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rulegamecompose.ui.MainActivity
import com.example.rulegamecompose.ui.viewmodel.RuleViewModel

@Composable
fun finishDialog(dialogShow: MutableState<Boolean>, viewModel: RuleViewModel, context: Context) {
    if (dialogShow.value) {
        AlertDialog(onDismissRequest = { }, confirmButton = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        viewModel.refreshMoney()
                        (context.getActivity() as MainActivity).finish()
                    }
                ) {
                    Text("set")
                }
            }
        },
            title = {
                Text(
                    text = "Your lose!!!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                )
            })
    }

}

fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}