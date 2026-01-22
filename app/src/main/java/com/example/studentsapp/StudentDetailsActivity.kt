package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.studentsapp.model.Model
import com.example.studentsapp.ui.screens.StudentDetailsScreen

class StudentDetailsActivity : ComponentActivity() {

    private var studentPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        studentPosition = intent.getIntExtra("student_pos", -1)
        if (studentPosition == -1) {
            finish()
            return
        }
        val student = Model.students[studentPosition]

        setContent {
            StudentDetailsScreen(
                student = student,
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
            val student = Model.students[studentPosition]
            setContent {
                StudentDetailsScreen(
                    student = student,
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
