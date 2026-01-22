package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.model.Model
import com.example.studentsapp.ui.screens.EditStudentScreen

class EditStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val studentPosition = intent.getIntExtra("student_pos", -1)

        if (studentPosition == -1) {
            finish()
            return
        }

        val student = Model.students[studentPosition]

        setContent {
            EditStudentScreen(
                student = student,
                onSave = { updatedStudent ->
                    Model.students[studentPosition] = updatedStudent
                    finish()
                },
                onDelete = {
                    Model.students.removeAt(studentPosition)
                    // Go back to the main activity and clear the task stack
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                },
                onCancel = { finish() }
            )
        }
    }
}
