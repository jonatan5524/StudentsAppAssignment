package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.studentsapp.model.Model
import com.example.studentsapp.ui.screens.StudentListScreen

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onResume() {
        super.onResume()
        setContent {
            App()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val context = LocalContext.current

    Scaffold(
        topBar = { TopAppBar(title = { Text("Students List") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { context.startActivity(Intent(context, NewStudentActivity::class.java)) }) {
                Icon(Icons.Filled.Add, contentDescription = "Add new student")
            }
        }
    ) { innerPadding ->
        StudentListScreen(
            students = Model.students,
            onStudentClick = { student ->
                val intent = Intent(context, StudentDetailsActivity::class.java)
                intent.putExtra("student_pos", Model.students.indexOf(student))
                context.startActivity(intent)
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}
