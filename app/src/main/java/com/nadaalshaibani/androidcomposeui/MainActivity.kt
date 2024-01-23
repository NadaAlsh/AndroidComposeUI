package com.nadaalshaibani.androidcomposeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nadaalshaibani.androidcomposeui.data.Question
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
    val questions = listOf(
        Question("Is it raining today?", false),
        Question("Will it be sunny tomorrow?", true),
        Question("Is it windy today?", true)
    )
    var isCorrect by remember { mutableStateOf(false) }
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var showCorrectAnswer by remember { mutableStateOf(false) }
    var showWrongAnswer by remember { mutableStateOf(false) }
    var showNextQuestion by remember { mutableStateOf(false) }
    var nextQuestionButton by remember { mutableStateOf(false) }
    var showAnswerOption by remember { mutableStateOf(true) }
    var userScore by remember { mutableStateOf(0) }
    var questionsFinished by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.padding(14.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = questions[currentQuestionIndex].question)
        if (showWrongAnswer)
            AnswerBox(isCorrect = false)

        if (showCorrectAnswer)
            AnswerBox(isCorrect = true)

        Text(text = "Your Score: $userScore")

        if (nextQuestionButton && !questionsFinished)
            Button(modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (currentQuestionIndex == (questions.size - 1)) {
                        currentQuestionIndex = 0
                        questionsFinished = true
                    }else
                        currentQuestionIndex++

                    nextQuestionButton = false
                    showAnswerOption = true
                    showCorrectAnswer = false
                    showWrongAnswer = false
                }){
                    Text(text = stringResource(R.string.next_question))
                }
                if (showAnswerOption)
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Button(modifier = Modifier.weight(1f),
                            onClick = {
                                var isAnswerCorrect = true == questions[currentQuestionIndex].answer
                                if (isAnswerCorrect) {
                                    showCorrectAnswer = true
                                    nextQuestionButton = true
                                    showAnswerOption = false
                                    userScore++
                                } else {
                                    showWrongAnswer = true
                                }
                            }) {
                            Text(text = stringResource(R.string.True))
                        }
                        Spacer(modifier = Modifier.width(10.dp))

                        Button(modifier = Modifier.weight(1f),
                            onClick = {
                                var isAnswerCorrect = false == questions[currentQuestionIndex].answer
                                if (isAnswerCorrect) {
                                    showCorrectAnswer = true
                                    nextQuestionButton = true
                                    showAnswerOption = false
                                    userScore++
                                } else {
                                    showWrongAnswer = true
                                }
                            }) {
                            Text(text = stringResource(R.string.False))
                        }
                    }

    }
}

@Composable
fun AnswerBox(isCorrect: Boolean) {
    Box(
        modifier = Modifier
            .size(200.dp)
            .fillMaxWidth()
            .height(50.dp)
            .padding(5.dp)
            .clip(CircleShape)
            .background(
                color = if (isCorrect) Color.Green.copy(alpha = 0.2f)
                else Color.Red.copy(alpha = 0.5f)
            )
    ) {
        if (isCorrect) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .align(Alignment.CenterEnd), text = stringResource(R.string.True)
            )
        } else {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .align(Alignment.CenterEnd), text = stringResource(R.string.False)
            )
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
@Composable
fun Greeting(s: String) {
    TODO("Not yet implemented")
}
