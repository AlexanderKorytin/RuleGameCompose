package com.example.rulegamecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.rulegamecompose.ui.screens.RuleScreen
import com.example.rulegamecompose.ui.theme.Green60
import com.example.rulegamecompose.ui.theme.RuleGameComposeTheme
import com.example.rulegamecompose.ui.viewmodel.RuleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    val vm by viewModel<RuleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RuleGameComposeTheme {
                Surface(Modifier.fillMaxSize(), color = Green60) {
                    RuleScreen(vm)
                }

            }
        }
    }
}

