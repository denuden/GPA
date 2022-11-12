package com.example.gpa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gpa.ui.theme.GPATheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GPATheme {
                var gpa by remember { mutableStateOf(0.0) }
                var totalUnits by remember { mutableStateOf(0) }
                var grade by remember { mutableStateOf("") }
                var units by remember { mutableStateOf("") }
                var avg by remember { mutableStateOf(0.0) }


                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Sum(gpa.toString(), totalUnits.toString())
                        Spacer(modifier = Modifier.padding(vertical = 8.dp))
                        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                            OutlinedTextField(
                                value = grade,
                                label = { Text("Grade") },
                                onValueChange = { grade = it },
                                modifier = Modifier.weight(1f),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                            OutlinedTextField(
                                value = units,
                                label = { Text("Number of Units") },
                                onValueChange = { units = it },
                                modifier = Modifier.weight(1f),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        }
                        Spacer(modifier = Modifier.padding(vertical = 8.dp))
                        Button(onClick = {
                            totalUnits += units.toInt()
                            gpa = gpa + (grade.toDouble() * units.toInt())
                        }) {
                            Text(text = "Calculate")
                        }
                        Spacer(modifier = Modifier.padding(vertical = 8.dp))
                        Button(onClick = {
                            avg = gpa / totalUnits
                        }) {
                            Text(text = "DIVIDE")
                        }
                        Text(
                            text = "Average : ${avg}",
                            modifier = Modifier.padding(top = 16.dp),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Black
                        )

                        Spacer(modifier = Modifier.padding(vertical = 16.dp))
                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                            onClick = {
                                gpa = 0.0
                                totalUnits = 0
                                grade = "0"
                                units = "0"
                                avg = 0.0
                            }) {
                            Text(text = "Clear", color = Color.White)
                        }
                    }


                }
            }


        }
    }
}

@Composable
fun Sum(sum: String, units: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Blue.copy(0.5f))
            .padding(16.dp)
    ) {
        var units = units.ifEmpty { '0' }
        Text(text = "Sum of grades x units : ${sum}", softWrap = true , modifier = Modifier.weight(1f).padding(end=8.dp), textAlign = TextAlign.Center)
        Text(text = "Added units : ${units}",softWrap = true, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)


    }
}
