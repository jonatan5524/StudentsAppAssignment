package com.example.studentsapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.studentsapp.R
import com.example.studentsapp.model.Student

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditStudentScreen(
    student: Student,
    onSave: (Student) -> Unit,
    onDelete: () -> Unit,
    onCancel: () -> Unit
) {
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
                painter = painterResource(id = android.R.drawable.ic_menu_myplaces),
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
