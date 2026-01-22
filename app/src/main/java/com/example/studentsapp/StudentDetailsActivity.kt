package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentsapp.model.Model

class StudentDetailsActivity : AppCompatActivity() {

    private var studentPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        studentPosition = intent.getIntExtra("student_pos", -1)
        if (studentPosition == -1) {
            finish()
            return
        }

        setContent {
            StudentDetailsScreen(
                studentPosition = studentPosition,
                onEdit = {
                    val intent = Intent(this, EditStudentActivity::class.java)
                    intent.putExtra("student_pos", studentPosition)
                    startActivity(intent)
                }
            )
        }
    }
     override fun onResume() {
        super.onResume()
        if (studentPosition != -1) {
            setContent {
            StudentDetailsScreen(
                studentPosition = studentPosition,
                onEdit = {
                    val intent = Intent(this, EditStudentActivity::class.java)
                    intent.putExtra("student_pos", studentPosition)
                    startActivity(intent)
                }
            )
        }
        }
    }
}

@Composable
fun StudentDetailsScreen(studentPosition: Int, onEdit: () -> Unit) {
    val student = Model.students.getOrNull(studentPosition)

    if (student == null) {
        // Handle student not found case if necessary
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Student Details") })
        }
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
            Text(text = "Name: ${student.name}", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "ID: ${student.id}", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Phone: ${student.phone}", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Address: ${student.address}", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = student.isChecked,
                    onCheckedChange = null,
                    enabled = false
                )
                Text(text = "Checked", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onEdit) {
                Text("Edit")
            }
        }
    }
}
