package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class EditStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val studentPosition = intent.getIntExtra("student_pos", -1)

        if (studentPosition == -1) {
            finish()
            return
        }

        setContent {
            EditStudentScreen(
                studentPosition = studentPosition,
                onSave = { updatedStudent ->
                    Model.students[studentPosition] = updatedStudent
                    finish()
                },
                onDelete = {
                    Model.students.removeAt(studentPosition)
                    finishAffinity() // Finish all activities in the stack
                    startActivity(Intent(this, MainActivity::class.java)) // Restart the main activity
                },
                onCancel = { finish() }
            )
        }
    }
}

@Composable
fun EditStudentScreen(
    studentPosition: Int,
    onSave: (Student) -> Unit,
    onDelete: () -> Unit,
    onCancel: () -> Unit
) {
    val student = Model.students.getOrNull(studentPosition)
    if (student == null) {
        onCancel()
        return
    }

    var name by remember { mutableStateOf(student.name) }
    var id by remember { mutableStateOf(student.id) }
    var phone by remember { mutableStateOf(student.phone) }
    var address by remember { mutableStateOf(student.address) }
    var isChecked by remember { mutableStateOf(student.isChecked) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Edit Student") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_person_24),
                contentDescription = "Student Avatar",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = id, onValueChange = { id = it }, label = { Text("ID") })
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone") })
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = address, onValueChange = { address = it }, label = { Text("Address") })
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = isChecked, onCheckedChange = { isChecked = it })
                Text(text = "Checked")
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row {
                Button(onClick = onCancel) {
                    Text("Cancel")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = onDelete) {
                    Text("Delete")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {
                    val updatedStudent = Student(name, id, phone, address, isChecked)
                    onSave(updatedStudent)
                }) {
                    Text("Save")
                }
            }
        }
    }
}
