package com.example.studentsapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

@Composable
fun NewStudentScreen(onSave: () -> Unit, onCancel: () -> Unit, modifier: Modifier = Modifier) {
    val name = remember { mutableStateOf("") }
    val id = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val address = remember { mutableStateOf("") }
    val isChecked = remember { mutableStateOf(false) }

    Column(modifier = modifier.padding(16.dp)) {
        TextField(value = name.value, onValueChange = { name.value = it }, label = { Text("Name") }, modifier = Modifier.fillMaxWidth())
        TextField(value = id.value, onValueChange = { id.value = it }, label = { Text("ID") }, modifier = Modifier.fillMaxWidth())
        TextField(value = phone.value, onValueChange = { phone.value = it }, label = { Text("Phone") }, modifier = Modifier.fillMaxWidth())
        TextField(value = address.value, onValueChange = { address.value = it }, label = { Text("Address") }, modifier = Modifier.fillMaxWidth())

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = isChecked.value, onCheckedChange = { isChecked.value = it })
            Text(text = "Checked")
        }

        Row {
            Button(onClick = { onCancel() }) {
                Text(text = "Cancel")
            }
            Button(onClick = { 
                val student = Student(name.value, id.value, phone.value, address.value, isChecked.value)
                Model.students.add(student)
                onSave()
             }) {
                Text(text = "Save")
            }
        }
    }
}
