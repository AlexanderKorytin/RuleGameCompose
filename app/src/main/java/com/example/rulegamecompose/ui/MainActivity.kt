package com.example.rulegamecompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.rulegamecompose.ui.screens.RuleScreen
import com.example.rulegamecompose.ui.screens.finishDialog
import com.example.rulegamecompose.ui.theme.Green60
import com.example.rulegamecompose.ui.theme.RuleGameComposeTheme
import com.example.rulegamecompose.ui.viewmodel.RuleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val vm by viewModel<RuleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var show = remember {
                mutableStateOf(false)
            }
            vm.getBankSize().observe(this@MainActivity) {
                if (it <= 0) {
                    show.value = true
                }
            }
            RuleGameComposeTheme {
                Surface(Modifier.fillMaxSize(), color = Green60) {
                    finishDialog(dialogShow = show, viewModel = vm, context = this@MainActivity)
                    RuleScreen(vm, this)
                }

            }
        }
    }
}

