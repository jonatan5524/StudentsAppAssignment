package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class EditStudentActivity : AppCompatActivity() {

    private var studentPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        studentPosition = intent.getIntExtra("student_pos", -1)

        if (studentPosition == -1) {
            finish()
            return
        }

        val student = Model.students.getOrNull(studentPosition)
        if (student == null) {
            finish()
            return
        }

        val nameEditText: EditText = findViewById(R.id.student_edit_name)
        val idEditText: EditText = findViewById(R.id.student_edit_id)
        val phoneEditText: EditText = findViewById(R.id.student_edit_phone)
        val addressEditText: EditText = findViewById(R.id.student_edit_address)
        val checkedCheckBox: CheckBox = findViewById(R.id.student_edit_checked)
        val saveButton: Button = findViewById(R.id.student_edit_save_button)
        val deleteButton: Button = findViewById(R.id.student_edit_delete_button)
        val cancelButton: Button = findViewById(R.id.student_edit_cancel_button)

        nameEditText.setText(student.name)
        idEditText.setText(student.id)
        phoneEditText.setText(student.phone)
        addressEditText.setText(student.address)
        checkedCheckBox.isChecked = student.isChecked

        saveButton.setOnClickListener {
            val updatedStudent = Student(
                name = nameEditText.text.toString(),
                id = idEditText.text.toString(),
                phone = phoneEditText.text.toString(),
                address = addressEditText.text.toString(),
                isChecked = checkedCheckBox.isChecked
            )
            Model.students[studentPosition] = updatedStudent
            finish()
        }

        deleteButton.setOnClickListener {
            Model.students.removeAt(studentPosition)
            finishAffinity() // Finish all activities in the stack
            startActivity(Intent(this, MainActivity::class.java)) // Restart the main activity
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }
}
