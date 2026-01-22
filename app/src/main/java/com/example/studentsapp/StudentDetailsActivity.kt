package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.model.Model

class StudentDetailsActivity : AppCompatActivity() {

    private var studentPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        studentPosition = intent.getIntExtra("student_pos", -1)
        if (studentPosition == -1) {
            finish()
            return
        }

        val editButton: Button = findViewById(R.id.student_details_edit_button)

        editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("student_pos", studentPosition)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        if (studentPosition != -1) {
            val student = Model.students.getOrNull(studentPosition)
            if (student == null) {
                finish()
                return
            }
            val nameTextView: TextView = findViewById(R.id.student_details_name)
            val idTextView: TextView = findViewById(R.id.student_details_id)
            val phoneTextView: TextView = findViewById(R.id.student_details_phone)
            val addressTextView: TextView = findViewById(R.id.student_details_address)
            val checkedCheckBox: CheckBox = findViewById(R.id.student_details_checked)

            nameTextView.text = "Name: ${student.name}"
            idTextView.text = "ID: ${student.id}"
            phoneTextView.text = "Phone: ${student.phone}"
            addressTextView.text = "Address: ${student.address}"
            checkedCheckBox.isChecked = student.isChecked
        }
    }
}
