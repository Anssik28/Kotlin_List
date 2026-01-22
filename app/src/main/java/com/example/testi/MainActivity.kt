package com.example.testi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.testi.ui.HomeScreen
import com.example.testi.ui.theme.TestiTheme



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TestiTheme{
                HomeScreen()
            }
        }
    }
}