package com.example.studentsapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.model.Student
import com.example.studentsapp.ui.adapters.StudentAdapter

@Composable
fun StudentListScreen(students: List<Student>, onStudentClick: (Student) -> Unit, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val adapter = StudentAdapter(onStudentClick)
            RecyclerView(context).apply {
                layoutManager = LinearLayoutManager(context)
                this.adapter = adapter
                val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
                addItemDecoration(decoration)
            }
        },
        update = {
            (it.adapter as StudentAdapter).submitList(students.toList()) // Create a new list to trigger the DiffUtil
        }
    )
}
