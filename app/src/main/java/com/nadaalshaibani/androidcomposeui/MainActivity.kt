package com.nadaalshaibani.androidcomposeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nadaalshaibani.androidcomposeui.ui.theme.AndroidComposeUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComposeUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AndroidComposeUI(modifier = Modifier.background(Color.LightGray))

                }
            }
        }
    }
}

@Composable
fun AndroidComposeUI(modifier: Modifier = Modifier) {
    var isCorrect by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.padding(14.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Greeting(name = "Is it raining today?")
        AnswerBox(isCorrect = isCorrect)
        Row {
            Button(modifier = Modifier.weight(1f), onClick = { /*TODO*/ }) {
                Text(text = "True")
            }

            Spacer(modifier = Modifier.width(16.dp))
            Button(modifier = Modifier.weight(1f), onClick = { /*TODO*/ }) {
                Text(text = "False")
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Next Question")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier,
        fontSize = 36.sp
    )
}
@Composable
fun CorrectAnswerText(text: String){
    Text(text = text, style = MaterialTheme.typography.bodyMedium.copy(color = Color.Green))
}
@Composable
fun WrongAnswerText(text: String){
    Text(text = text, style = MaterialTheme.typography.bodyMedium.copy(color = Color.Red))
}
@Composable
fun AnswerBox(isCorrect: Boolean){
    Box(modifier = Modifier
        .fillMaxWidth(0.4f)
        .height(50.dp)
        .padding(16.dp)
        .clip(CircleShape)
        .background(color = if (isCorrect) Color.Green.copy(alpha = 0.2f)
        else Color.Red.copy(alpha = 0.2f))
    ){
        if (isCorrect){
            CorrectAnswerText(text = "Correct Answer")
        }else {
            WrongAnswerText(text = "Wrong Answer")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidComposeUITheme {
        Greeting("Android")
    }
}